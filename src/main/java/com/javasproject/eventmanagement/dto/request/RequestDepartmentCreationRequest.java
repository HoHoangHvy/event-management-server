package com.javasproject.eventmanagement.dto.request;


import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)

public class RequestDepartmentCreationRequest {
    String departmentId;
    String requestId;
    String note;
    String response;
    String status;
}
