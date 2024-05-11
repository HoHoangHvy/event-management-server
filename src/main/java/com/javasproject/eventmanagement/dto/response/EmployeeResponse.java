package com.javasproject.eventmanagement.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EmployeeResponse {
    String id;
    String name;
    String phone;
    String empLevel;
    String gender;
    String status;
    String dob;
    String startDate;
    String email;
    String departmentName;
    String departmentId;
    String roleId;
}
