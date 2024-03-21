package com.javasproject.eventmanagement.controller;

import com.javasproject.eventmanagement.dto.request.ApiResponse;
import com.javasproject.eventmanagement.entity.EventDetail;
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
    public ApiResponse<EventDetail> createEventDetail(@RequestBody EventDetail eventDetail){
        return ApiResponse.<EventDetail>builder()
                .data(eventDetailService.upsert(eventDetail))
                .build();
    }

    @GetMapping("/{id}")
    public ApiResponse<EventDetail> getEventDetailById(@PathVariable String id){
        String message = "Successfully get the event detail";
        EventDetail eventDetail = eventDetailService.getById(id);
        if (eventDetail == null){
            message = "Not found this event detail";
        }
        return ApiResponse.<EventDetail>builder()
                .data(eventDetail)
                .message(message)
                .build();
    }

    @GetMapping
    public ApiResponse<List<EventDetail>> getEventDetailList(){
        String message = "Successfully get the event detail list";
        List<EventDetail> eventDetailList = eventDetailService.getEventDetailList();
        if(eventDetailList.isEmpty()){
            message = "Event detail list is empty";

        }
        return ApiResponse.<List<EventDetail>>builder()
                .data(eventDetailList)
                .message(message)
                .build();
    }

    @PutMapping
    public ApiResponse<EventDetail> updateEventDetail(@RequestBody EventDetail eventDetail){
        return ApiResponse.<EventDetail>builder()
                .data(eventDetailService.upsert(eventDetail))
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
