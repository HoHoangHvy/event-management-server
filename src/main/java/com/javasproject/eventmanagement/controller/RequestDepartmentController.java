package com.javasproject.eventmanagement.controller;

import com.javasproject.eventmanagement.dto.request.ApiResponse;
import com.javasproject.eventmanagement.dto.request.RequestDepartmentCreationRequest;
import com.javasproject.eventmanagement.dto.request.RequestDepartmentRequest;
import com.javasproject.eventmanagement.dto.response.ListResponse;
import com.javasproject.eventmanagement.dto.response.RequestDepartmentListResponse;
import com.javasproject.eventmanagement.dto.response.RequestDepartmentResponse;
import com.javasproject.eventmanagement.entity.RequestDepartment;
import com.javasproject.eventmanagement.service.RequestDepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class RequestDepartmentController {
    private final RequestDepartmentService requestDepartmentService;

    @PostMapping("/requestdepartments")
    public ApiResponse<RequestDepartmentResponse> createRequestDepartment(@RequestBody RequestDepartmentCreationRequest requestDepartment) {
        ApiResponse<RequestDepartmentResponse> apiResponse = new ApiResponse<>();
        apiResponse.setData(requestDepartmentService.createRequest(requestDepartment));
        apiResponse.setMessage("Successfully created the request department.");
        return apiResponse;
    }

    @GetMapping("/requestdepartments")
    public ApiResponse<List<RequestDepartmentResponse>> getAllRequestDepartments() {
        ApiResponse<List<RequestDepartmentResponse>> apiResponse = new ApiResponse<>();
        apiResponse.setData(requestDepartmentService.getALlRequestDepartment());
        apiResponse.setMessage("Successfully retrieved the list of request departments.");
        return apiResponse;
    }

    @GetMapping("/requestdepartments/{id}")
    public ResponseEntity<ApiResponse<RequestDepartmentResponse>> getRequestDepartmentById(@PathVariable("id") String id) {
        ApiResponse<RequestDepartmentResponse> apiResponse = new ApiResponse<>();
        apiResponse.setData(requestDepartmentService.getRequestDepartmentById(id));
        apiResponse.setMessage("Successfully retrieved the request department.");
        return ResponseEntity.ok(apiResponse);
    }

    @PutMapping("/requestdepartments/{id}")
    public ApiResponse<RequestDepartmentResponse> updateRequestDepartment(@PathVariable("id") String id, @RequestBody RequestDepartmentCreationRequest requestDepartment) {
        ApiResponse<RequestDepartmentResponse> apiResponse = new ApiResponse<>();
        apiResponse.setData(requestDepartmentService.updateRequest(id, requestDepartment));
        apiResponse.setMessage("Successfully updated the request department.");
        return apiResponse;
    }

    @DeleteMapping("/requestdepartments/{id}")
    public ApiResponse<String> deleteRequestDepartment(@PathVariable("id") String id) {
        ApiResponse<String> apiResponse = new ApiResponse<>();
        requestDepartmentService.deleteRequestDepartment(id);
        apiResponse.setData("Request department has been deleted.");
        return apiResponse;
    }
    @GetMapping("/related/requestdepartments")
    public ApiResponse<Map<String, Object>> getRelated(){
        ApiResponse<Map<String, Object>> apiResponse = new ApiResponse<>();
        Map<String, Object> listResponse = requestDepartmentService.getRelated();
        apiResponse.setData(listResponse); // Set empty Map
        apiResponse.setMessage("Successfully get the employee's list");
        return apiResponse;
    }
}
