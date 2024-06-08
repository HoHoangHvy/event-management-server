package com.javasproject.eventmanagement.service;

import com.javasproject.eventmanagement.dto.request.EventDetailRequest;
import com.javasproject.eventmanagement.entity.*;
import com.javasproject.eventmanagement.repository.EventDetailRepository;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;
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
    public String generateEventDetailCode(String eventName){
        return eventName + "-EVD" + (eventDetailRepository.count() + 1);
    }
    public EventDetails createEventDetailWithRelations(EventDetailRequest requestEventDetail, Event event){

        EventDetails eventDetails = new EventDetails();
        eventDetails.setName(generateEventDetailCode(event.getName()));
        eventDetails.setType(requestEventDetail.getType());
        eventDetails.setEvents(event);

        if(!requestEventDetail.getFacilityDetails().isEmpty() && requestEventDetail.getType().equals("Facility")) {
            Set<EventDetailFacility> eventDetailFacilities = requestEventDetail.getFacilityDetails().stream()
                    .map(facilityDetail -> {
                        EventDetailFacility eventDetailFacility = new EventDetailFacility();
                        eventDetailFacility.setFacility(facilityService.getObjectById(String.valueOf(facilityDetail.getValue())));
                        eventDetailFacility.setQuantity(facilityDetail.getQuantityValue());
                        eventDetailFacility.setBookingPrice(facilityService.getObjectById(String.valueOf(facilityDetail.getValue())).getPrice());
                        eventDetailFacility.setEventDetails(eventDetails);
                        return eventDetailFacility;
                    }).collect(Collectors.toSet());
            eventDetails.setPrice(requestEventDetail.getFacilityDetails().stream().map(facilityDetail -> {
                Facility facility = facilityService.getObjectById(String.valueOf(facilityDetail.getValue()));
                return facilityDetail.getQuantityValue() * facility.getPrice();
            }).reduce(0L, Long::sum));
            eventDetails.setCost(0);
            eventDetails.setFacilities(eventDetailFacilities);
        }
        if(!requestEventDetail.getDishDetails().isEmpty() && requestEventDetail.getType().equals("Dish")) {
            Set<EventDetailDish> eventDetailDishes = requestEventDetail.getDishDetails().stream()
                    .map(dishDetail -> {
                        EventDetailDish eventDetailDish = new EventDetailDish();
                        eventDetailDish.setDish(dishService.getObjectById(String.valueOf(dishDetail.getValue())));
                        eventDetailDish.setQuantity(dishDetail.getQuantityValue());
                        eventDetailDish.setBookingPrice(dishService.getObjectById(String.valueOf(dishDetail.getValue())).getPrice());
                        eventDetailDish.setEventDetails(eventDetails);
                        return eventDetailDish;
                    }).collect(Collectors.toSet());
            eventDetails.setPrice(requestEventDetail.getDishDetails().stream().map(dishDetail -> {
                Dish dish = dishService.getObjectById(String.valueOf(dishDetail.getValue()));
                return dishDetail.getQuantityValue() * dish.getPrice();
            }).reduce(0L, Long::sum));
            eventDetails.setCost(requestEventDetail.getDishDetails().stream().map(dishDetail -> {
                Dish dish = dishService.getObjectById(String.valueOf(dishDetail.getValue()));
                return dishDetail.getQuantityValue() * dish.getCost();
            }).reduce(0L, Long::sum));
            eventDetails.setEventDetailDishes(eventDetailDishes);
        }
        if(!requestEventDetail.getThirdpartyDetails().isEmpty() && requestEventDetail.getType().equals("ThirdParty")) {
            Set<EventDetailThirdParty> eventDetailThirdParties = requestEventDetail.getThirdpartyDetails().stream()
                    .map(thirdPartyDetail -> {
                        EventDetailThirdParty eventDetailThirdParty = new EventDetailThirdParty();
                        eventDetailThirdParty.setThirdParty(thirdPartyService.getObjectById(String.valueOf(thirdPartyDetail.getValue())));
                        eventDetailThirdParty.setQuantity(thirdPartyDetail.getQuantityValue());
                        eventDetailThirdParty.setBookingPrice(thirdPartyService.getObjectById(String.valueOf(thirdPartyDetail.getValue())).getPrice());
                        eventDetailThirdParty.setEventDetails(eventDetails);
                        return eventDetailThirdParty;
                    }).collect(Collectors.toSet());
            eventDetails.setPrice(requestEventDetail.getThirdpartyDetails().stream().map(thirdPartyDetail -> {
                ThirdParty thirdParty = thirdPartyService.getObjectById(String.valueOf(thirdPartyDetail.getValue()));
                return thirdPartyDetail.getQuantityValue() * thirdParty.getPrice();
            }).reduce(0L, Long::sum));
            eventDetails.setCost(requestEventDetail.getThirdpartyDetails().stream().map(thirdPartyDetail -> {
                ThirdParty thirdParty = thirdPartyService.getObjectById(String.valueOf(thirdPartyDetail.getValue()));
                return thirdPartyDetail.getQuantityValue() * thirdParty.getCost();
            }).reduce(0L, Long::sum));
            eventDetails.setThirdparties(eventDetailThirdParties);
        }

        return eventDetailRepository.save(eventDetails);
    }

    double calculateTotalAmount(String eventId) {
        return eventDetailRepository.calculateTotalAmount(eventId);
    }

}
