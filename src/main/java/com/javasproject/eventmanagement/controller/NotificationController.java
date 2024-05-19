package com.javasproject.eventmanagement.controller;

import com.javasproject.eventmanagement.dto.request.ApiResponse;
import com.javasproject.eventmanagement.dto.request.NotificationCreationRequest;
import com.javasproject.eventmanagement.dto.response.NotificationResponse;
import com.javasproject.eventmanagement.dto.response.ListResponse;
import com.javasproject.eventmanagement.service.NotificationService;
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
public class NotificationController {
    NotificationService notificationService;

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/notifications")
    public ApiResponse<NotificationResponse> createNotification(@RequestBody NotificationCreationRequest request) {
        ApiResponse<NotificationResponse> apiResponse = new ApiResponse<>();
        apiResponse.setData(notificationService.createNotification(request));
        apiResponse.setMessage("Successfully created the notification.");
        return apiResponse;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/notifications")
    public ApiResponse<ListResponse> getAllNotifications() {
        ApiResponse<ListResponse> apiResponse = new ApiResponse<>();
        var listResponse = new ListResponse<NotificationResponse>();
        List<NotificationResponse> notifications = notificationService.getAllNotifications();
        listResponse.setListData(notifications);
        apiResponse.setData(listResponse);
        apiResponse.setMessage("Successfully retrieved the notification list.");
        return apiResponse;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/notifications/{notificationId}")
    public ResponseEntity<ApiResponse<NotificationResponse>> getNotificationById(@PathVariable("notificationId") String notificationId) {
        ApiResponse<NotificationResponse> apiResponse = new ApiResponse<>();
        apiResponse.setData(notificationService.getNotificationById(notificationId));
        apiResponse.setMessage("Successfully retrieved the notification.");
        return ResponseEntity.ok(apiResponse);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping("/notifications/{notificationId}")
    public ApiResponse<NotificationResponse> updateNotification(@PathVariable String notificationId, @RequestBody NotificationCreationRequest request) {
        ApiResponse<NotificationResponse> apiResponse = new ApiResponse<>();
        apiResponse.setData(notificationService.updateNotification(notificationId, request));
        apiResponse.setMessage("Successfully updated the notification.");
        return apiResponse;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping("/notifications/{notificationId}")
    public ApiResponse<String> deleteNotification(@PathVariable String notificationId) {
        ApiResponse<String> apiResponse = new ApiResponse<>();
        notificationService.deleteNotification(notificationId);
        apiResponse.setData("Notification has been deleted");
        apiResponse.setMessage("Successfully deleted the notification.");
        return apiResponse;
    }
}
