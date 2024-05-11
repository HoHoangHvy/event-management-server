package com.javasproject.eventmanagement.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults( level = AccessLevel.PRIVATE)
public class EmployeeCreationRequest {
    String name;
    String phone;
    String empLevel;
    String gender;
    String status;
    LocalDate doB;
    LocalDate startDate;
    String email;
    String roleId;
    String departmentId;
}
