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
public class EmployeeUpdateRequest {
    String phone;
    String empLevel;
    String gender;
    String status;
    LocalDateTime dob;
    LocalDateTime startDate;
    String email;
    String name;
    String departmentId;
    String roleId;
}
