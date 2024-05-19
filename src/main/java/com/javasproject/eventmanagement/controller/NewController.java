package com.javasproject.eventmanagement.controller;

import com.javasproject.eventmanagement.dto.request.ApiResponse;
import com.javasproject.eventmanagement.dto.request.NewCreationRequest;
import com.javasproject.eventmanagement.dto.request.NewCreationRequest;
import com.javasproject.eventmanagement.dto.response.NewResponse;
import com.javasproject.eventmanagement.dto.response.ListResponse;
import com.javasproject.eventmanagement.service.NewService;
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
public class NewController {
    NewService newsService;

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/news")
    public ApiResponse<NewResponse> createNew(@RequestBody NewCreationRequest request) {
        ApiResponse<NewResponse> apiResponse = new ApiResponse<>();
        apiResponse.setData(newsService.createNew(request));
        apiResponse.setMessage("Successfully created the news.");
        return apiResponse;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/news")
    public ApiResponse<ListResponse> getAllNew() {
        ApiResponse<ListResponse> apiResponse = new ApiResponse<>();
        var listResponse = new ListResponse<NewResponse>();
        List<NewResponse> newsList = newsService.getAllNew();
        listResponse.setListData(newsList);
        apiResponse.setData(listResponse);
        apiResponse.setMessage("Successfully retrieved the news list.");
        return apiResponse;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/news/{newsId}")
    public ResponseEntity<ApiResponse<NewResponse>> getNewById(@PathVariable("newsId") String newsId) {
        ApiResponse<NewResponse> apiResponse = new ApiResponse<>();
        apiResponse.setData(newsService.getNewById(newsId));
        apiResponse.setMessage("Successfully retrieved the news.");
        return ResponseEntity.ok(apiResponse);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping("/news/{newsId}")
    public ApiResponse<NewResponse> updateNew(@PathVariable String newsId, @RequestBody NewCreationRequest request) {
        ApiResponse<NewResponse> apiResponse = new ApiResponse<>();
        apiResponse.setData(newsService.updateNew(newsId, request));
        apiResponse.setMessage("Successfully updated the news.");
        return apiResponse;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping("/news/{newsId}")
    public ApiResponse<String> deleteNew(@PathVariable String newsId) {
        ApiResponse<String> apiResponse = new ApiResponse<>();
        newsService.deleteNew(newsId);
        apiResponse.setData("New has been deleted");
        apiResponse.setMessage("Successfully deleted the news.");
        return apiResponse;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/related/news")
    public ApiResponse<Map<String, Object>> getRelated(){
        ApiResponse<Map<String, Object>> apiResponse = new ApiResponse<>();
        apiResponse.setData(new HashMap<>()); // Set empty Map
        apiResponse.setMessage("Successfully get the employee's list");
        return apiResponse;
    }
}
