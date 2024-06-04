package com.javasproject.eventmanagement.controller;

import com.javasproject.eventmanagement.dto.request.ApiResponse;
import com.javasproject.eventmanagement.dto.request.FacilityCreationRequest;
import com.javasproject.eventmanagement.dto.response.FacilityResponse;
import com.javasproject.eventmanagement.dto.response.ListResponse;
import com.javasproject.eventmanagement.dto.response.ResourceResponse;
import com.javasproject.eventmanagement.service.FacilityService;
import lombok.RequiredArgsConstructor;
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
public class FacilityController {

    @Autowired
    private FacilityService facilityService;

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/facilities")
    public ApiResponse<FacilityResponse> createFacility(@RequestBody FacilityCreationRequest request) {
        ApiResponse<FacilityResponse> apiResponse = new ApiResponse<>();
        apiResponse.setData(facilityService.createFacility(request));
        apiResponse.setMessage("Successfully created the facility.");
        return apiResponse;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/facilities")
    public ApiResponse<ListResponse> getAllFacilities() {
        ApiResponse<ListResponse> apiResponse = new ApiResponse<>();
        var listResponse = new ListResponse<FacilityResponse>();
        List<FacilityResponse> facilities = facilityService.getAllFacilities();
        long totalData = facilityService.countAllFacilities();
        listResponse.setListData(facilities);
        listResponse.setTotalData(totalData);

        apiResponse.setData(listResponse);
        apiResponse.setMessage("Successfully retrieved the facility list.");
        return apiResponse;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/facilities/{facilityId}")
    public ResponseEntity<ApiResponse<FacilityResponse>> getFacilityById(@PathVariable("facilityId") String facilityId) {
        ApiResponse<FacilityResponse> apiResponse = new ApiResponse<>();
        apiResponse.setData(facilityService.getFacilityById(facilityId));
        apiResponse.setMessage("Successfully retrieved the facility.");
        return ResponseEntity.ok(apiResponse);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping("/facilities/{id}")
    public ApiResponse<FacilityResponse> updateFacility(@PathVariable String id, @RequestBody FacilityCreationRequest request) {
        ApiResponse<FacilityResponse> apiResponse = new ApiResponse<>();
        apiResponse.setData(facilityService.updateFacility(id, request));
        apiResponse.setMessage("Successfully updated the facility.");
        return apiResponse;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping("/facilities/{facilityId}")
    public ApiResponse<String> deleteFacility(@PathVariable String facilityId) {
        ApiResponse<String> apiResponse = new ApiResponse<>();
        apiResponse.setData("Facility has been deleted");
        facilityService.deleteFacility(facilityId);
        return apiResponse;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/related/facilities")
    public ApiResponse<Map<String, Object>> getRelated(){
        ApiResponse<Map<String, Object>> apiResponse = new ApiResponse<>();
        Map<String, Object> listResponse = new HashMap<>();
        apiResponse.setData(listResponse);
        apiResponse.setMessage("Successfully get the employee's list");
        return apiResponse;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/facilities/available")
    public ApiResponse<ListResponse<FacilityResponse>> getAllAvailableResources(
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {

        ApiResponse<ListResponse<FacilityResponse>> apiResponse = new ApiResponse<>();
        var listResponse = new ListResponse<FacilityResponse>();
        List<FacilityResponse> resources = facilityService.getAllAvailableFacility(startDate, endDate);
        listResponse.setListData(resources);
        apiResponse.setData(listResponse);
        apiResponse.setMessage("Successfully retrieved the resource list.");
        return apiResponse;
    }
}
