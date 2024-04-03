package com.javasproject.eventmanagement.controller;

import com.javasproject.eventmanagement.dto.request.ApiResponse;
import com.javasproject.eventmanagement.entity.EventDetails;
import com.javasproject.eventmanagement.service.EventDetailService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/eventDetails")
public class EventDetailController {
    @Autowired
    private EventDetailService eventDetailService;
    @PostMapping
    public ApiResponse<EventDetails> createEventDetail(@RequestBody EventDetails eventDetails){
        return ApiResponse.<EventDetails>builder()
                .data(eventDetailService.upsert(eventDetails))
                .build();
    }

    @GetMapping("/{id}")
    public ApiResponse<EventDetails> getEventDetailById(@PathVariable String id){
        String message = "Successfully get the event detail";
        EventDetails eventDetails = eventDetailService.getById(id);
        if (eventDetails == null){
            message = "Not found this event detail";
        }
        return ApiResponse.<EventDetails>builder()
                .data(eventDetails)
                .message(message)
                .build();
    }

    @GetMapping
    public ApiResponse<List<EventDetails>> getEventDetailList(){
        String message = "Successfully get the event detail list";
        List<EventDetails> eventDetailsList = eventDetailService.getEventDetailList();
        if(eventDetailsList.isEmpty()){
            message = "Event detail list is empty";

        }
        return ApiResponse.<List<EventDetails>>builder()
                .data(eventDetailsList)
                .message(message)
                .build();
    }

    @PutMapping
    public ApiResponse<EventDetails> updateEventDetail(@RequestBody EventDetails eventDetails){
        return ApiResponse.<EventDetails>builder()
                .data(eventDetailService.upsert(eventDetails))
                .build();
    }

    @DeleteMapping("/{id}")
    public ApiResponse<String> deleteEventDetail(@PathVariable String id){
        String message = "Delete successfully";
        Boolean status = eventDetailService.deleteById(id);
        if (!status){
            message = "Delete fail! Not found this record";
        }
        return ApiResponse.<String>builder()
                .message(message)
                .build();
    }

}
