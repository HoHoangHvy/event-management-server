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
public class RequestDepartmentListResponse {
    String id;
    String requestName;
    String departmentName;
    String departmentId;
    String resolvedDate;
    String resolveStatus;
    String response;
    String note;
    String resolveByName;
    String dateEntered;
}
