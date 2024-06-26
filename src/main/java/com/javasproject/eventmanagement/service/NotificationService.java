package com.javasproject.eventmanagement.service;

import com.javasproject.eventmanagement.dto.request.NotificationCreationRequest;
import com.javasproject.eventmanagement.dto.request.NotificationCreationRequest;
import com.javasproject.eventmanagement.dto.response.NotificationResponse;
import com.javasproject.eventmanagement.entity.New;
import com.javasproject.eventmanagement.entity.Notification;
import com.javasproject.eventmanagement.exception.AppException;
import com.javasproject.eventmanagement.exception.ErrorCode;
import com.javasproject.eventmanagement.mapper.NotificationMapper;
import com.javasproject.eventmanagement.repository.NewRepository;
import com.javasproject.eventmanagement.repository.NotificationRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Service
public class NotificationService {
    NotificationRepository notificationRepository;
    NotificationMapper notificationMapper;
    EmployeeService employeeService;
    private final UserService userService;
    SocketService socketService;

    public NotificationResponse createNotification(NotificationCreationRequest request) {
        Notification notification = new Notification();
        notification.setName(request.getName());
        notification.setType(request.getType());
        notification.setContent(request.getContent());
        notification.setParentId(request.getParentId());
        notification.setParentType(request.getParentType());
        notification.setEmployee(employeeService.getEmployeeObjectById(request.getEmployeeId()));

        NotificationResponse notificationResponse = notificationMapper.toNotificationResponse(notificationRepository.save(notification));
        socketService.pushMessage(notificationResponse, "/notifications/HoHoangHvy/" + notification.getEmployee().getUser().getId());
        return notificationResponse;
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
        if (request.getIsRead() != null) {
            notification.setIsRead(request.getIsRead());
        }
        return notificationMapper.toNotificationResponse(notificationRepository.save(notification));
    }

    public void deleteNotification(String notificationId) {
        Notification notification = notificationRepository.findById(notificationId).orElseThrow(() -> new AppException(ErrorCode.NOTIFICATION_NOT_FOUND));
        notification.setDeleted(true);
        notificationRepository.save(notification);
    }

    public List<NotificationResponse> getAllNotifications() {
        return notificationRepository.findAllActiveByUserId(userService.getCurrentUser().getEmployee()).stream()
                .map(notificationMapper::toNotificationResponse)
                .peek(this::handleNotificationResponse).collect(Collectors.toList());
    }
    NewRepository newRepository;
    private void handleNotificationResponse(NotificationResponse notificationResponse) {
        if(notificationResponse.getParentType().equals("News")) {
            newRepository.findById(notificationResponse.getParentId())
                    .ifPresentOrElse(
                            newTypeEntity -> notificationResponse.setNewType(newTypeEntity.getType()),
                            () -> { throw new AppException(ErrorCode.USER_EXISTED); }
                    );
        }
    }
    public NotificationResponse getNotificationById(String id) {
        return notificationRepository.findById(id).map(notificationMapper::toNotificationResponse).orElseThrow(() -> new AppException(ErrorCode.NOTIFICATION_NOT_FOUND));
    }

    public List<NotificationResponse> getUnreadNoti() {
        return notificationRepository.findAllActiveUnreadByUserId(userService.getCurrentUser().getEmployee()).stream()
                .map(notificationMapper::toNotificationResponse)
                .peek(this::handleNotificationResponse).collect(Collectors.toList());
    }
}
