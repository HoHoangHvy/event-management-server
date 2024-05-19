package com.javasproject.eventmanagement.service;

import com.javasproject.eventmanagement.dto.request.EventDetailCreationRequest;
import com.javasproject.eventmanagement.entity.*;
import com.javasproject.eventmanagement.repository.EventDetailRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Transactional
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EventDetailService {
    @Autowired
    private EventDetailRepository eventDetailRepository;
    FacilityService facilityService;
    DishService dishService;
    ThirdPartyService thirdPartyService;

    public EventDetails upsert(EventDetails eventDetails){
        return eventDetailRepository.save(eventDetails);
    }

    public EventDetails getById(String id){
        Optional<EventDetails> findById = eventDetailRepository.findById(id);
        return findById.orElse(null);
    }

    public List<EventDetails> getEventDetailList() {
        return eventDetailRepository.findAll();
    }

    public Boolean deleteById(String id){
        if(eventDetailRepository.existsById(id)){
            eventDetailRepository.deleteById(id);
            return true;
        }
        else {
            return false;
        }
    }

    public EventDetails createEventDetailWithRelations(EventDetailCreationRequest requestEventDetail, Event event){

        EventDetails eventDetails = new EventDetails();
        eventDetails.setName(requestEventDetail.getName());
        eventDetails.setType(requestEventDetail.getType());
        eventDetails.setPrice(requestEventDetail.getPrice());
        eventDetails.setCost(requestEventDetail.getCost());
        eventDetails.setEvents(event);

//        Set<Facility> facilities = requestEventDetail.getFacilities().stream()
//                .map(id -> {
//                    Facility facility = facilityService.getById(String.valueOf(id));
//                    if (facility == null) {
//                        throw new EntityNotFoundException("Facility with id " + id + " not found");
//                    }
//                    return facility;
//                }).collect(Collectors.toSet());
//        eventDetails.setFacilities(facilities);
//
//        Set<Dish> dishes = requestEventDetail.getDishes().stream()
//                .map(id -> {
//                    Dish dish = dishService.getById(String.valueOf(id));
//                    if (dish == null) {
//                        throw new EntityNotFoundException("Dish with id " + id + " not found");
//                    }
//                    return dish;
//                }).collect(Collectors.toSet());
//        eventDetails.setDishes(dishes);
//
//        Set<ThirdParty> thirdParties = requestEventDetail.getThirdparties().stream()
//                .map(id -> {
//                    ThirdParty thirdparty = thirdPartyService.getById(String.valueOf(id));
//                    if (thirdparty == null) {
//                        throw new EntityNotFoundException("ThirdParty with id " + id + " not found");
//                    }
//                    return thirdparty;
//                }).collect(Collectors.toSet());
//        eventDetails.setThirdparties(thirdParties);

        return eventDetailRepository.save(eventDetails);
    }

}
