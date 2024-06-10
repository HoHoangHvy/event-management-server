package com.javasproject.eventmanagement.controller;

import com.javasproject.eventmanagement.dto.request.ApiResponse;
import com.javasproject.eventmanagement.dto.request.EventCreationRequest;
import com.javasproject.eventmanagement.dto.response.EmployeeResponse;
import com.javasproject.eventmanagement.dto.response.EventResponse;
import com.javasproject.eventmanagement.dto.response.ListResponse;
import com.javasproject.eventmanagement.entity.Event;
import com.javasproject.eventmanagement.service.EventService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/api")
public class EventController {
    EventService eventService;

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/events")
    public ApiResponse<ListResponse> getAllEvents() {
        ApiResponse<ListResponse> apiResponse = new ApiResponse<>();
        var listResponse = new ListResponse<EventResponse>();
        List<EventResponse> events = eventService.getEventList();
        long totalData = eventService.countAllEvent();
        listResponse.setListData(events);
        listResponse.setTotalData(totalData);

        apiResponse.setData(listResponse);
        apiResponse.setMessage("Successfully get the event's list");
        return apiResponse;
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/events-schedule")
    public ApiResponse<ListResponse> getEventsSchedule() {
        ApiResponse<ListResponse> apiResponse = new ApiResponse<>();
        var listResponse = new ListResponse<EventResponse>();
        List<EventResponse> events = eventService.getApprovedEvent();
        long totalData = eventService.countAllApprovedEvent();
        listResponse.setListData(events);
        listResponse.setTotalData(totalData);

        apiResponse.setData(listResponse);
        apiResponse.setMessage("Successfully get the event's list");
        return apiResponse;
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/events")
    public ApiResponse<EventResponse> createEvent(@RequestBody EventCreationRequest requestEvent) {
        return ApiResponse.<EventResponse>builder()
                .data(eventService.createEventWithEventDetails(requestEvent))
                .build();
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping("/events/{id}")
    public ApiResponse<Boolean> approveEvent(@PathVariable String id,@RequestBody EventCreationRequest requestEvent) {
        return ApiResponse.<Boolean>builder()
                .data(eventService.approveEvent(id))
                .build();
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/events/{id}")
    public ApiResponse<EventResponse> getEventById(@PathVariable String id) {
        return ApiResponse.<EventResponse>builder()
                .data(eventService.getById(id))
                .build();
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/related/events")
    public ApiResponse<Map<String, Object>> getRelated(){
        ApiResponse<Map<String, Object>> apiResponse = new ApiResponse<>();
        Map<String, Object> listResponse = eventService.getRelated();
        apiResponse.setData(listResponse);
        apiResponse.setMessage("Successfully get the employee's list");
        return apiResponse;
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/option/events")
    public ApiResponse<Map<String, EventResponse>> getOptions(){
        ApiResponse<Map<String, EventResponse>> apiResponse = new ApiResponse<>();
        Map<String, EventResponse> listResponse = eventService.getAllOptionsDetail();
        apiResponse.setData(listResponse);
        return apiResponse;
    }
}
