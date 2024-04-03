package com.javasproject.eventmanagement.service;

import com.javasproject.eventmanagement.dto.request.EventCreationRequest;
import com.javasproject.eventmanagement.dto.request.EventDetailCreationRequest;
import com.javasproject.eventmanagement.entity.Event;
import com.javasproject.eventmanagement.entity.EventDetails;
import com.javasproject.eventmanagement.repository.EventRepository;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Transactional
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EventService {
    @Autowired
    EventRepository eventRepository;
    @Autowired
    EventDetailService eventDetailService;

    public Event upsert(Event event){
        return eventRepository.save(event);
    }
    public Event createEventWithEventDetails(EventCreationRequest eventCreationRequest){
        // Create and save the Event
        Event savedEvent = new Event();
        savedEvent.setName(eventCreationRequest.getName());
        savedEvent.setStartDate(eventCreationRequest.getStartDate());
        savedEvent.setEndDate(eventCreationRequest.getEndDate());
        savedEvent.setDescription(eventCreationRequest.getDescription());
        savedEvent.setStatus("Waiting For Approval");
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

        return savedEvent;
    }
    public Event getById(String id){
        Optional<Event> findById = eventRepository.findById(id);
        return findById.orElse(null);
    }

    public List<Event> getEventList() {
        return eventRepository.findAll();
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
}
