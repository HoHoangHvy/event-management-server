package com.javasproject.eventmanagement.service;

import com.javasproject.eventmanagement.dto.request.NotificationCreationRequest;
import com.javasproject.eventmanagement.dto.request.NotificationCreationRequest;
import com.javasproject.eventmanagement.dto.response.NotificationResponse;
import com.javasproject.eventmanagement.entity.Notification;
import com.javasproject.eventmanagement.exception.AppException;
import com.javasproject.eventmanagement.exception.ErrorCode;
import com.javasproject.eventmanagement.mapper.NotificationMapper;
import com.javasproject.eventmanagement.repository.NotificationRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Service
public class NotificationService {
    NotificationRepository notificationRepository;
    NotificationMapper notificationMapper;

    public NotificationResponse createNotification(NotificationCreationRequest request) {
        Notification notification = new Notification();
        notification.setName(request.getName());
        notification.setType(request.getType());
        notification.setContent(request.getContent());
        notification.setDeleted(false);

        Notification savedNotification = notificationRepository.save(notification);
        return notificationMapper.toNotificationResponse(savedNotification);
    }

    public NotificationResponse updateNotification(String notificationId, NotificationCreationRequest request) {
        Notification notification = notificationRepository.findById(notificationId).orElseThrow(() -> new AppException(ErrorCode.NOTIFICATION_NOT_FOUND));

        if (request.getName() != null && !request.getName().isEmpty()) {
            notification.setName(request.getName());
        }
        if (request.getType() != null && !request.getType().isEmpty()) {
            notification.setType(request.getType());
        }
        if (request.getContent() != null && !request.getContent().isEmpty()) {
            notification.setContent(request.getContent());
        }
        if (request.getDeleted() != null) {
            notification.setDeleted(request.getDeleted());
        }

        return notificationMapper.toNotificationResponse(notificationRepository.save(notification));
    }

    public void deleteNotification(String notificationId) {
        Notification notification = notificationRepository.findById(notificationId).orElseThrow(() -> new AppException(ErrorCode.NOTIFICATION_NOT_FOUND));
        notification.setDeleted(true);
        notificationRepository.save(notification);
    }

    public List<NotificationResponse> getAllNotifications() {
        return notificationRepository.findAll().stream().map(notificationMapper::toNotificationResponse).collect(Collectors.toList());
    }

    public NotificationResponse getNotificationById(String id) {
        return notificationRepository.findById(id).map(notificationMapper::toNotificationResponse).orElseThrow(() -> new AppException(ErrorCode.NOTIFICATION_NOT_FOUND));
    }
}
