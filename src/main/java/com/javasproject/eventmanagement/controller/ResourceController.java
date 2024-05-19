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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
}
