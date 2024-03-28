package com.javasproject.eventmanagement.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CustomerUpdateRequest {
    String phone;
    String email;
    String type;
    LocalDate dob;
}
