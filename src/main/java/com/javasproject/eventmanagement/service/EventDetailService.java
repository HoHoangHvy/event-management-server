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

    public EventDetail upsert(EventDetail eventDetail){
        return eventDetailRepository.save(eventDetail);
    }

    public EventDetail getById(String id){
        Optional<EventDetail> findById = eventDetailRepository.findById(id);
        return findById.orElse(null);
    }

    public List<EventDetail> getEventDetailList() {
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
}
