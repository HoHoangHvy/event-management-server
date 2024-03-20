package com.javasproject.eventmanagement.service;

import com.javasproject.eventmanagement.entity.EventDetail;
import com.javasproject.eventmanagement.repository.EventDetailRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EventDetailService {
    @Autowired
    private EventDetailRepository eventDetailRepository;

    public String upsert(EventDetail eventDetail){
        eventDetailRepository.save(eventDetail);
        return "success";
    }

    public EventDetail getById(String id){
        Optional<EventDetail> findById = eventDetailRepository.findById(id);
        return findById.orElse(null);
    }

    public List<EventDetail> getEventDetailList() {
        return eventDetailRepository.findAll();
    }

    public String deleteById(String id){
        if(eventDetailRepository.existsById(id)){
            eventDetailRepository.deleteById(id);
            return "Delete Success";
        }
        else {
            return "No Record Found";
        }
    }
}
