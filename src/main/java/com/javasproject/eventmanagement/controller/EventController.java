package com.javasproject.eventmanagement.controller;

import com.javasproject.eventmanagement.dto.request.ApiResponse;
import com.javasproject.eventmanagement.dto.request.EventCreationRequest;
import com.javasproject.eventmanagement.entity.Event;
import com.javasproject.eventmanagement.service.EventService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/events")
public class EventController {
    EventService eventService;


    @GetMapping
    public ApiResponse<List<Event>> getAllEvents() {
        return ApiResponse.<List<Event>>builder()
                .data(eventService.getEventList())
                .build();
    }
    @PostMapping
    public ApiResponse<Event> createEvent(@RequestBody EventCreationRequest requestEvent) {
        return ApiResponse.<Event>builder()
                .data(eventService.createEventWithEventDetails(requestEvent))
                .build();
    }
    @GetMapping("/{id}")
    public ApiResponse<Event> getEventById(@PathVariable String id) {
        return ApiResponse.<Event>builder()
                .data(eventService.getById(id))
                .build();
    }
}
