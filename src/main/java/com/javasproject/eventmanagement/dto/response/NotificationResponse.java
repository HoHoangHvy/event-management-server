package com.javasproject.eventmanagement.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class NotificationResponse {
    String id;
    String name;
    String type;
    String content;
    String dateEntered;
    String parentId;
    String parentType;
    Boolean isRead;
    String newType = "";
    String userId;
}
