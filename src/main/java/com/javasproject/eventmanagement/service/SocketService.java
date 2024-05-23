package com.javasproject.eventmanagement.service;

import com.javasproject.eventmanagement.dto.response.NotificationResponse;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class SocketService {

    private final RestTemplate restTemplate;

    public SocketService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void pushMessage(NotificationResponse data, String key) {
        String url = "https://socket.dotb.cloud/send-event-phenikaa";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, Object> body = new HashMap<>();
        body.put("to", key);
        body.put("data", data);

        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(body, headers);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
        System.out.println(response.getBody());
    }
}
