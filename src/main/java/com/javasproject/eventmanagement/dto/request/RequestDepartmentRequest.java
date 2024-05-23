package com.javasproject.eventmanagement.dto.request;

import com.javasproject.eventmanagement.entity.Department;
import com.javasproject.eventmanagement.entity.Request;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RequestDepartmentRequest {
    Department department;
    String note;
    Request request;
    String response;
    String status;
}
