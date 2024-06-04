package com.javasproject.eventmanagement.controller;

import com.javasproject.eventmanagement.dto.request.ApiResponse;
import com.javasproject.eventmanagement.dto.request.ThirdPartyCreationRequest;
import com.javasproject.eventmanagement.dto.response.ThirdPartyResponse;
import com.javasproject.eventmanagement.dto.response.ListResponse;
import com.javasproject.eventmanagement.service.ThirdPartyService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/")
public class ThirdPartyController {

    @Autowired
    private ThirdPartyService thirdPartyService;

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/thirdparties")
    public ApiResponse<ThirdPartyResponse> createThirdParty(@RequestBody ThirdPartyCreationRequest request) {
        ApiResponse<ThirdPartyResponse> apiResponse = new ApiResponse<>();
        apiResponse.setData(thirdPartyService.createThirdParty(request));
        apiResponse.setMessage("Successfully created the third party.");
        return apiResponse;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/thirdparties")
    public ApiResponse<ListResponse> getAllThirdParties() {
        ApiResponse<ListResponse> apiResponse = new ApiResponse<>();
        var listResponse = new ListResponse<ThirdPartyResponse>();
        List<ThirdPartyResponse> thirdParties = thirdPartyService.getAllThirdParties();
        long totalData = thirdPartyService.countAllThirdParties();
        listResponse.setListData(thirdParties);
        listResponse.setTotalData(totalData);

        apiResponse.setData(listResponse);
        apiResponse.setMessage("Successfully retrieved the third party list.");
        return apiResponse;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/thirdparties/{thirdPartyId}")
    public ResponseEntity<ApiResponse<ThirdPartyResponse>> getThirdPartyById(@PathVariable("thirdPartyId") String thirdPartyId) {
        ApiResponse<ThirdPartyResponse> apiResponse = new ApiResponse<>();
        apiResponse.setData(thirdPartyService.getThirdPartyById(thirdPartyId));
        apiResponse.setMessage("Successfully retrieved the third party.");
        return ResponseEntity.ok(apiResponse);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping("/thirdparties/{id}")
    public ApiResponse<ThirdPartyResponse> updateThirdParty(@PathVariable String id, @RequestBody ThirdPartyCreationRequest request) {
        ApiResponse<ThirdPartyResponse> apiResponse = new ApiResponse<>();
        apiResponse.setData(thirdPartyService.updateThirdParty(id, request));
        apiResponse.setMessage("Successfully updated the third party.");
        return apiResponse;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping("/thirdparties/{thirdPartyId}")
    public ApiResponse<String> deleteThirdParty(@PathVariable String thirdPartyId) {
        ApiResponse<String> apiResponse = new ApiResponse<>();
        apiResponse.setData("Third Party has been deleted");
        thirdPartyService.deleteThirdParty(thirdPartyId);
        return apiResponse;
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/related/thirdparties")
    public ApiResponse<Map<String, Object>> getRelated(){
        ApiResponse<Map<String, Object>> apiResponse = new ApiResponse<>();
        Map<String, Object> listResponse = new HashMap<>();
        apiResponse.setData(listResponse);
        apiResponse.setMessage("Successfully get the employee's list");
        return apiResponse;
    }

}
