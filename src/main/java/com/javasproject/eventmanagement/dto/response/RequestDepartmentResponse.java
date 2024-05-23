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
public class RequestDepartmentResponse {
    String id;
    String requestId;
    String departmentId;
    String departmentName;
    LocalDateTime resolvedDate;
    String resolveStatus;
    String response;
    String note;
    Boolean deleted;
    String resolvedById;
    LocalDateTime dateEntered;
}
