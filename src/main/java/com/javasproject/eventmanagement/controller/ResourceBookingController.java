package com.javasproject.eventmanagement.controller;

import com.javasproject.eventmanagement.dto.request.ApiResponse;
import com.javasproject.eventmanagement.dto.request.ResourceBookingRequest;
import com.javasproject.eventmanagement.dto.response.ResourceBookingResponse;
import com.javasproject.eventmanagement.service.ResourceBookingService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/api/")
public class ResourceBookingController {

    ResourceBookingService resourceBookingService;

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/resource-bookings")
    public ApiResponse<ResourceBookingResponse> createResourceBooking(@RequestBody ResourceBookingRequest request) {
        ApiResponse<ResourceBookingResponse> apiResponse = new ApiResponse<>();
        apiResponse.setData(resourceBookingService.createResourceBooking(request));
        apiResponse.setMessage("Successfully created the resource booking.");
        return apiResponse;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/resource-bookings")
    public ApiResponse<List<ResourceBookingResponse>> getAllResourceBookings() {
        ApiResponse<List<ResourceBookingResponse>> apiResponse = new ApiResponse<>();
        apiResponse.setData(resourceBookingService.getAllResourceBookings());
        apiResponse.setMessage("Successfully retrieved all resource bookings.");
        return apiResponse;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/resource-bookings/{id}")
    public ApiResponse<ResourceBookingResponse> getResourceBookingById(@PathVariable String id) {
        ApiResponse<ResourceBookingResponse> apiResponse = new ApiResponse<>();
        apiResponse.setData(resourceBookingService.getResourceBookingById(id));
        apiResponse.setMessage("Successfully retrieved the resource booking.");
        return apiResponse;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping("/resource-bookings/{id}")
    public ApiResponse<ResourceBookingResponse> updateResourceBooking(@PathVariable String id, @RequestBody ResourceBookingRequest request) {
        ApiResponse<ResourceBookingResponse> apiResponse = new ApiResponse<>();
        apiResponse.setData(resourceBookingService.updateResourceBooking(id, request));
        apiResponse.setMessage("Successfully updated the resource booking.");
        return apiResponse;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping("/resource-bookings/{id}")
    public ApiResponse<String> deleteResourceBooking(@PathVariable String id) {
        ApiResponse<String> apiResponse = new ApiResponse<>();
        resourceBookingService.deleteResourceBooking(id);
        apiResponse.setData("Resource booking has been deleted.");
        apiResponse.setMessage("Successfully deleted the resource booking.");
        return apiResponse;
    }
}
