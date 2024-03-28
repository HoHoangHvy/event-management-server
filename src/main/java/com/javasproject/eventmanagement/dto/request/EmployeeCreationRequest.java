package com.javasproject.eventmanagement.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults( level = AccessLevel.PRIVATE)
public class EmployeeCreationRequest {
    String name;
    String phone;
    String level;
    String gender;
    String status;
    LocalDate doB;
    LocalDate startDate;
}
