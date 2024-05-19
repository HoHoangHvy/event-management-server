package com.javasproject.eventmanagement.service;

import com.javasproject.eventmanagement.dto.request.EventCreationRequest;
import com.javasproject.eventmanagement.dto.request.EventDetailCreationRequest;
import com.javasproject.eventmanagement.dto.response.EventResponse;
import com.javasproject.eventmanagement.dto.response.OptionResponse;
import com.javasproject.eventmanagement.entity.Employee;
import com.javasproject.eventmanagement.entity.Event;
import com.javasproject.eventmanagement.entity.EventDetails;
import com.javasproject.eventmanagement.mapper.EventMapper;
import com.javasproject.eventmanagement.repository.EventRepository;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

//    public EventResponse upsert(EventCreationRequest event){
//        return eventMapper.toEventResponse(eventRepository.save(event));
//    }
    public EventResponse createEventWithEventDetails(EventCreationRequest eventCreationRequest){
        // Create and save the Event
        Event savedEvent = new Event();
        savedEvent.setName(eventCreationRequest.getName());
        savedEvent.setStartDate(eventCreationRequest.getStartDate());
        savedEvent.setEndDate(eventCreationRequest.getEndDate());
        savedEvent.setDescription(eventCreationRequest.getDescription());
        savedEvent.setStatus("Draft");
        savedEvent.setCreatedBy(userService.getCurrentUser().getEmployee());
        savedEvent.setCustomer(customerService.getCustomerObjectById(eventCreationRequest.getCustomerId()));

        savedEvent = eventRepository.save(savedEvent);

        // Create and save the EventDetails with the saved Event
        Set<EventDetails> eventDetails = new HashSet<>();
        for(EventDetailCreationRequest eventDetail : eventCreationRequest.getEventDetails()){
            EventDetails savedEventDetail = eventDetailService.createEventDetailWithRelations(eventDetail, savedEvent);
            eventDetails.add(savedEventDetail);
        }

        // Update the Event with the saved EventDetails
        savedEvent.setEventDetails(eventDetails);
        savedEvent = eventRepository.save(savedEvent);

        return eventMapper.toEventResponse(savedEvent);
    }
    public EventResponse getById(String id){
        Optional<Event> findById = eventRepository.findById(id);
        return eventMapper.toEventResponse(findById.orElse(null));
    }

    public List<EventResponse> getEventList() {
        return eventRepository.findAll().stream().map(eventMapper::toEventResponse).collect(Collectors.toList());
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

    public Map<String, Object> getRelated(String employeeId) {
        List<OptionResponse> customerList = customerService.getAllOption();
        return Map.of("customerName", customerList);
    }
}
