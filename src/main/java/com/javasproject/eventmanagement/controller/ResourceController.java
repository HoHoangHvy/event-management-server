package com.javasproject.eventmanagement.controller;

import com.javasproject.eventmanagement.dto.request.ApiResponse;
import com.javasproject.eventmanagement.dto.request.ResourceCreationRequest;
import com.javasproject.eventmanagement.dto.response.ResourceResponse;
import com.javasproject.eventmanagement.dto.response.ListResponse;
import com.javasproject.eventmanagement.service.ResourceService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ResourceController {
    ResourceService resourceService;

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/resources")
    public ApiResponse<ResourceResponse> createResource(@RequestBody ResourceCreationRequest request) {
        ApiResponse<ResourceResponse> apiResponse = new ApiResponse<>();
        apiResponse.setData(resourceService.createResource(request));
        apiResponse.setMessage("Successfully created the resource.");
        return apiResponse;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/resources")
    public ApiResponse<ListResponse> getAllResources() {
        ApiResponse<ListResponse> apiResponse = new ApiResponse<>();
        var listResponse = new ListResponse<ResourceResponse>();
        List<ResourceResponse> resources = resourceService.getAllResources();
        listResponse.setListData(resources);
        apiResponse.setData(listResponse);
        apiResponse.setMessage("Successfully retrieved the resource list.");
        return apiResponse;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/resources/available")
    public ApiResponse<ListResponse<ResourceResponse>> getAllAvailableResources(
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {

        ApiResponse<ListResponse<ResourceResponse>> apiResponse = new ApiResponse<>();
        var listResponse = new ListResponse<ResourceResponse>();
        List<ResourceResponse> resources = resourceService.getAllAvailableResources(startDate, endDate);
        listResponse.setListData(resources);
        apiResponse.setData(listResponse);
        apiResponse.setMessage("Successfully retrieved the resource list.");
        return apiResponse;
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/resources/{resourceId}")
    public ResponseEntity<ApiResponse<ResourceResponse>> getResourceById(@PathVariable("resourceId") String resourceId) {
        ApiResponse<ResourceResponse> apiResponse = new ApiResponse<>();
        apiResponse.setData(resourceService.getResourceById(resourceId));
        apiResponse.setMessage("Successfully retrieved the resource.");
        return ResponseEntity.ok(apiResponse);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping("/resources/{resourceId}")
    public ApiResponse<ResourceResponse> updateResource(@PathVariable String resourceId, @RequestBody ResourceCreationRequest request) {
        ApiResponse<ResourceResponse> apiResponse = new ApiResponse<>();
        apiResponse.setData(resourceService.updateResource(resourceId, request));
        apiResponse.setMessage("Successfully updated the resource.");
        return apiResponse;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping("/resources/{resourceId}")
    public ApiResponse<String> deleteResource(@PathVariable String resourceId) {
        ApiResponse<String> apiResponse = new ApiResponse<>();
        resourceService.deleteResource(resourceId);
        apiResponse.setData("Resource has been deleted");
        apiResponse.setMessage("Successfully deleted the resource.");
        return apiResponse;
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/related/resources")
    public ApiResponse<Map<String, Object>> getRelated(){
        ApiResponse<Map<String, Object>> apiResponse = new ApiResponse<>();
        apiResponse.setData(new HashMap<>()); // Set empty Map
        apiResponse.setMessage("Successfully get the employee's list");
        return apiResponse;
    }
}
