package com.javasproject.eventmanagement.mapper;

import com.javasproject.eventmanagement.dto.request.NotificationCreationRequest;
import com.javasproject.eventmanagement.dto.response.NotificationResponse;
import com.javasproject.eventmanagement.entity.Notification;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface NotificationMapper {
    Notification toNotification(NotificationCreationRequest request);
    @Mapping(target = "dateEntered", source = "date_entered", dateFormat = "yyyy-MM-dd HH:mm:ss")
    NotificationResponse toNotificationResponse(Notification notification);

}
