package com.javasproject.eventmanagement.controller;

import com.javasproject.eventmanagement.dto.request.ApiResponse;
import com.javasproject.eventmanagement.dto.request.RequestCreationRequest;
import com.javasproject.eventmanagement.dto.response.RequestResponse;
import com.javasproject.eventmanagement.dto.response.ListResponse;
import com.javasproject.eventmanagement.service.RequestService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RequestController {
    RequestService requestService;

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/requests")
    public ApiResponse<RequestResponse> createRequest(@RequestBody RequestCreationRequest request) {
        ApiResponse<RequestResponse> apiResponse = new ApiResponse<>();
        apiResponse.setData(requestService.createRequest(request));
        apiResponse.setMessage("Successfully created the request.");
        return apiResponse;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/requests")
    public ApiResponse<ListResponse> getAllRequests() {
        ApiResponse<ListResponse> apiResponse = new ApiResponse<>();
        var listResponse = new ListResponse<RequestResponse>();
        List<RequestResponse> requests = requestService.getAllRequests();
        listResponse.setListData(requests);
        apiResponse.setData(listResponse);
        apiResponse.setMessage("Successfully retrieved the request list.");
        return apiResponse;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/requests/{requestId}")
    public ResponseEntity<ApiResponse<RequestResponse>> getRequestById(@PathVariable("requestId") String requestId) {
        ApiResponse<RequestResponse> apiResponse = new ApiResponse<>();
        apiResponse.setData(requestService.getRequestById(requestId));
        apiResponse.setMessage("Successfully retrieved the request.");
        return ResponseEntity.ok(apiResponse);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping("/requests/{requestId}")
    public ApiResponse<RequestResponse> updateRequest(@PathVariable String requestId, @RequestBody RequestCreationRequest request) {
        ApiResponse<RequestResponse> apiResponse = new ApiResponse<>();
        apiResponse.setData(requestService.updateRequest(requestId, request));
        apiResponse.setMessage("Successfully updated the request.");
        return apiResponse;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping("/requests/{requestId}")
    public ApiResponse<String> deleteRequest(@PathVariable String requestId) {
        ApiResponse<String> apiResponse = new ApiResponse<>();
        requestService.deleteRequest(requestId);
        apiResponse.setData("Request has been deleted");
        apiResponse.setMessage("Successfully deleted the request.");
        return apiResponse;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/related/requests")
    public ApiResponse<Map<String, Object>> getRelated(){
        ApiResponse<Map<String, Object>> apiResponse = new ApiResponse<>();
        apiResponse.setData(new HashMap<>()); // Set empty Map
        apiResponse.setMessage("Successfully get the employee's list");
        return apiResponse;
    }
}
