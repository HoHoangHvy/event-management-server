package com.javasproject.eventmanagement.controller;

import com.javasproject.eventmanagement.dto.request.ApiResponse;
import com.javasproject.eventmanagement.dto.request.HallCreationRequest;
import com.javasproject.eventmanagement.dto.response.HallResponse;
import com.javasproject.eventmanagement.dto.response.ListResponse;
import com.javasproject.eventmanagement.service.HallService;
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
public class HallController {

    @Autowired
    private HallService hallService;

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/halls")
    public ApiResponse<HallResponse> createHall(@RequestBody HallCreationRequest request) {
        ApiResponse<HallResponse> apiResponse = new ApiResponse<>();
        apiResponse.setData(hallService.createHall(request));
        apiResponse.setMessage("Successfully created the hall.");
        return apiResponse;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/halls")
    public ApiResponse<ListResponse> getAllHalls() {
        ApiResponse<ListResponse> apiResponse = new ApiResponse<>();
        var listResponse = new ListResponse<HallResponse>();
        List<HallResponse> halls = hallService.getAllHalls();
        long totalData = hallService.countAllHalls();
        listResponse.setListData(halls);
        listResponse.setTotalData(totalData);

        apiResponse.setData(listResponse);
        apiResponse.setMessage("Successfully retrieved the hall list.");
        return apiResponse;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/halls/{hallId}")
    public ResponseEntity<ApiResponse<HallResponse>> getHallById(@PathVariable("hallId") String hallId) {
        ApiResponse<HallResponse> apiResponse = new ApiResponse<>();
        apiResponse.setData(hallService.getHallById(hallId));
        apiResponse.setMessage("Successfully retrieved the hall.");
        return ResponseEntity.ok(apiResponse);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping("/halls/{id}")
    public ApiResponse<HallResponse> updateHall(@PathVariable String id, @RequestBody HallCreationRequest request) {
        ApiResponse<HallResponse> apiResponse = new ApiResponse<>();
        apiResponse.setData(hallService.updateHall(id, request));
        apiResponse.setMessage("Successfully updated the hall.");
        return apiResponse;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping("/halls/{hallId}")
    public ApiResponse<String> deleteHall(@PathVariable String hallId) {
        ApiResponse<String> apiResponse = new ApiResponse<>();
        apiResponse.setData("Hall has been deleted");
        hallService.deleteHall(hallId);
        return apiResponse;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/related/halls")
    public ApiResponse<Map<String, Object>> getRelated(){
        ApiResponse<Map<String, Object>> apiResponse = new ApiResponse<>();
        Map<String, Object> listResponse = new HashMap<>();
        apiResponse.setData(listResponse);
        apiResponse.setMessage("Successfully get the employee's list");
        return apiResponse;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/halls/available")
    public ApiResponse<ListResponse<HallResponse>> getAllAvailableHalls(
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {

        ApiResponse<ListResponse<HallResponse>> apiResponse = new ApiResponse<>();
        var listResponse = new ListResponse<HallResponse>();
        List<HallResponse> halls = hallService.getAllAvailableHalls(startDate, endDate);
        listResponse.setListData(halls);
        apiResponse.setData(listResponse);
        apiResponse.setMessage("Successfully retrieved the resource list.");
        return apiResponse;
    }

}
