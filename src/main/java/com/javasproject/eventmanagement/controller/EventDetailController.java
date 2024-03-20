package com.javasproject.eventmanagement.controller;

import com.javasproject.eventmanagement.entity.Contract;
import com.javasproject.eventmanagement.entity.EventDetail;
import com.javasproject.eventmanagement.service.EventDetailService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/eventDetailPage")
public class EventDetailController {
    @Autowired
    private EventDetailService eventDetailService;
    @PostMapping("/eventDetail") public ResponseEntity<String> createEventDetail(@RequestBody EventDetail eventDetail){
        String status = eventDetailService.upsert(eventDetail);
        return new ResponseEntity<>(status, HttpStatus.CREATED);
    }

    @GetMapping("/eventDetail/{id}")
    public ResponseEntity<EventDetail> getEventDetail(@PathVariable String id){
        EventDetail eventDetail = eventDetailService.getById(id);
        return new ResponseEntity<>(eventDetail, HttpStatus.OK);
    }

    @GetMapping("/eventDetails")
    public ResponseEntity<List<EventDetail>> getEventDetailList(){
        List<EventDetail> allEventDetails = eventDetailService.getEventDetailList();
        return new ResponseEntity<>(allEventDetails, HttpStatus.OK);
    }

    @PutMapping("/eventDetail")
    public ResponseEntity<String> updateEventDetail(@RequestBody EventDetail eventDetail){
        String status = eventDetailService.upsert(eventDetail);
        return new ResponseEntity<>(status, HttpStatus.OK);
    }

    @DeleteMapping("/eventDetail/{id}")
    public ResponseEntity<String> deleteEventDetail(@PathVariable String id){
        String status = eventDetailService.deleteById(id);
        return new ResponseEntity<>(status, HttpStatus.OK);
    }


}
