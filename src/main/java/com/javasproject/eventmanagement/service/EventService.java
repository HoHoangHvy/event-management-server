package com.javasproject.eventmanagement.service;

import com.javasproject.eventmanagement.dto.request.EventCreationRequest;
import com.javasproject.eventmanagement.dto.request.EventDetailCreationRequest;
import com.javasproject.eventmanagement.dto.request.EventDetailRequest;
import com.javasproject.eventmanagement.dto.response.EventResponse;
import com.javasproject.eventmanagement.dto.response.OptionResponse;
import com.javasproject.eventmanagement.entity.Event;
import com.javasproject.eventmanagement.mapper.EventMapper;
import com.javasproject.eventmanagement.repository.EventRepository;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Transactional
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EventService {

    @Autowired
    EventRepository eventRepository;
    @Autowired
    EventDetailService eventDetailService;
    EventMapper eventMapper;
    UserService userService;
    CustomerService customerService;
    HallService hallService;
    public LocalDateTime convertTimeZone(LocalDateTime localDateTime){
        // Convert LocalDateTime to ZonedDateTime with UTC time zone
        ZonedDateTime utcDateTime = localDateTime.atZone(ZoneId.of("UTC"));

        // Convert UTC time to GMT+7 (Asia/Ho_Chi_Minh) time zone
        ZonedDateTime gmtPlus7DateTime = utcDateTime.withZoneSameInstant(ZoneId.of("Asia/Ho_Chi_Minh"));
        return gmtPlus7DateTime.toLocalDateTime();
    }
    public String generateEventCode(){
        return "EVT" + (eventRepository.count() + 1);
    }
    public EventResponse createEventWithEventDetails(EventCreationRequest eventCreationRequest){
        // Create and save the Event
        Event savedEvent = new Event();
        savedEvent.setName(generateEventCode());
        savedEvent.setStartDate(convertTimeZone(eventCreationRequest.getStartDate()));
        savedEvent.setEndDate(convertTimeZone(eventCreationRequest.getEndDate()));
        savedEvent.setDescription(eventCreationRequest.getDescription());
        savedEvent.setStatus("Draft");
        savedEvent.setCreatedBy(userService.getCurrentUser().getEmployee());
        savedEvent.setCustomer(customerService.getCustomerObjectById(eventCreationRequest.getCustomerId()));
        savedEvent.setHall(hallService.getHallObjectById(eventCreationRequest.getHallId()));
        savedEvent.setType(eventCreationRequest.getType());

        savedEvent = eventRepository.save(savedEvent);

        //Handle for Dish
        EventDetailRequest eventDetailRequest = new EventDetailRequest();
        eventDetailRequest.setType("Dish");
        eventDetailRequest.setDishDetails(formatEventDetail(eventCreationRequest.getDishDetails()));
        eventDetailRequest.setFacilityDetails(new HashSet<>());
        eventDetailRequest.setThirdpartyDetails(new HashSet<>());
        eventDetailService.createEventDetailWithRelations(eventDetailRequest, savedEvent);
        //Handle for Facility
        EventDetailRequest eventDetailRequest2 = new EventDetailRequest();
        eventDetailRequest2.setType("Facility");
        eventDetailRequest2.setDishDetails(new HashSet<>());
        eventDetailRequest2.setFacilityDetails(formatEventDetail(eventCreationRequest.getFacilityDetails()));
        eventDetailRequest2.setThirdpartyDetails(new HashSet<>());
        eventDetailService.createEventDetailWithRelations(eventDetailRequest2, savedEvent);
        //Handle for Thirdparty
        EventDetailRequest eventDetailRequest3 = new EventDetailRequest();
        eventDetailRequest3.setType("ThirdParty");
        eventDetailRequest3.setDishDetails(new HashSet<>());
        eventDetailRequest3.setFacilityDetails(new HashSet<>());
        eventDetailRequest3.setThirdpartyDetails(formatEventDetail(eventCreationRequest.getThirdpartyDetails()));
        eventDetailService.createEventDetailWithRelations(eventDetailRequest3, savedEvent);

        return eventMapper.toEventResponse(savedEvent);
    }
    public Set<EventDetailCreationRequest> formatEventDetail(Set<EventDetailCreationRequest> eventDetailCreationRequest) {
        // Convert the set to a list
        List<EventDetailCreationRequest> list = new ArrayList<>(eventDetailCreationRequest);

        // Create a map to store the sum of quantities for each value
        Map<String, Long> valueQuantityMap = new HashMap<>();

        // Iterate over the list and update the map
        for (EventDetailCreationRequest item : list) {
            String value = item.getValue();
            long quantityValue = item.getQuantityValue();

            if (valueQuantityMap.containsKey(value)) {
                long sum = valueQuantityMap.get(value) + quantityValue;
                valueQuantityMap.put(value, sum);
            } else {
                valueQuantityMap.put(value, quantityValue);
            }
        }

        // Create a new set to store the unique items with summed quantities
        Set<EventDetailCreationRequest> result = new HashSet<>();
        for (Map.Entry<String, Long> entry : valueQuantityMap.entrySet()) {
            EventDetailCreationRequest item = new EventDetailCreationRequest();
            item.setValue(entry.getKey());
            item.setQuantityValue(entry.getValue());
            result.add(item);
        }

        return result;
    }
    public EventResponse getById(String id){
        Optional<Event> findById = eventRepository.findById(id);
        return eventMapper.toEventResponse(findById.orElse(null));
    }

    public List<EventResponse> getEventList() {
        return eventRepository.findAllByDeletedFalse().stream().map(eventMapper::toEventResponse).collect(Collectors.toList());
    }

    public Boolean deleteById(String id){
        if(eventRepository.existsById(id)){
            eventRepository.deleteById(id);
            return true;
        }
        else {
            return false;
        }
    }

    public long countAllEvent(){
        return eventRepository.count();
    }
    public long countAllApprovedEvent(){
        return eventRepository.countAllApprovedEvent();
    }
    public List<EventResponse> getApprovedEvent() {
        return eventRepository.findEventSchedule().stream().map(eventMapper::toEventResponse).collect(Collectors.toList());
    }
    public Map<String, Object> getRelated() {
        List<OptionResponse> customerList = customerService.getAllOption();
        return Map.of("customerName", customerList);
    }
}
