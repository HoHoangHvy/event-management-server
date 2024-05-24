package com.javasproject.eventmanagement.dto.request;


import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ResourceBookingRequest {
    String resourceId;
    LocalDateTime startDate;
    LocalDateTime endDate;
    int quantity;
    String status = "Wait for approval";
    String reason;
}
