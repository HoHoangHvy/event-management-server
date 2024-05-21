package com.javasproject.eventmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NotificationRestController {

    @Autowired
    private SimpMessagingTemplate template;

    @GetMapping("/notify")
    public String notify(@RequestParam String message) {
        template.convertAndSend("/topic/notifications", message);
        return "Notification sent";
    }
}