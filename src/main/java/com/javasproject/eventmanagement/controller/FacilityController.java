package com.javasproject.eventmanagement.controller;

import com.javasproject.eventmanagement.dto.request.ApiResponse;
import com.javasproject.eventmanagement.entity.Facility;
import com.javasproject.eventmanagement.service.FacilityService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/facilities")
public class FacilityController {
    @Autowired
    FacilityService facilityService;
    @PostMapping
    public ApiResponse<Facility> createFacility(@RequestBody Facility facility){
        return ApiResponse.<Facility>builder()
                .data(facilityService.upsert(facility))
                .build();
    }
    @GetMapping("/{id}")
    public ApiResponse<Facility> getFacilityById(@PathVariable String id){
        String message = "Successfully get the facility";
        Facility facility = facilityService.getById(id);
        if (facility == null){
            message = "Not found this facility";
        }
        return ApiResponse.<Facility>builder()
                .data(facility)
                .message(message)
                .build();
    }
    @GetMapping
    public ApiResponse<List<Facility>> getAllFacilities(){
        String message = "Successfully get the facility list";
        List<Facility> facilityList = facilityService.getFacilityList();
        if(facilityList.isEmpty()){
            message = "Facility list is empty";

        }
        return ApiResponse.<List<Facility>>builder()
                .data(facilityList)
                .message(message)
                .build();
    }
    @PutMapping
    public ApiResponse<Facility> updateFacility(@RequestBody  Facility facility){
        return ApiResponse.<Facility>builder()
                .data(facilityService.upsert(facility))
                .build();
    }
    @DeleteMapping("/{id}")
    public ApiResponse<String> deleteFacility(@PathVariable String id){
        String message = "Delete successfully";
        Boolean status = facilityService.deleteById(id);
        if (!status){
            message = "Delete fail! Not found this record";
        }
        return ApiResponse.<String>builder()
                .message(message)
                .build();
    }
}
