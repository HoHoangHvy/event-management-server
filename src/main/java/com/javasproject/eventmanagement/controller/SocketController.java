package com.javasproject.eventmanagement.controller;

import com.javasproject.eventmanagement.dto.response.NotificationResponse;
import com.javasproject.eventmanagement.entity.Notification;
import com.javasproject.eventmanagement.service.SocketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class SocketController {

    @Autowired
    private SocketService socketService;

    @PostMapping("/send-event")
    public void sendEvent(@RequestBody Map<String, Object> data) {
        NotificationResponse notificationResponse = new NotificationResponse();
        socketService.pushMessage(notificationResponse, "/notifications/HoHoangHvy/04056dc8-5636-4fd5-8915-0a811f210966");
    }
}
