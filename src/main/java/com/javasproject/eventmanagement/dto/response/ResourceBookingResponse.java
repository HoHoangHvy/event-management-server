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
public class ResourceBookingResponse {
    String id;
    String startDate;
    String endDate;
    String reason;
    int quantity;
    String status;
    String dateEntered;
    String employeeName;
    String employeeId;
    String resourceName;
    String resourceId;
}
