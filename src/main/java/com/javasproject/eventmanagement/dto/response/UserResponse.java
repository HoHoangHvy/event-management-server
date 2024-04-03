package com.javasproject.eventmanagement.dto.response;

import com.javasproject.eventmanagement.entity.Role;
import com.javasproject.eventmanagement.enums.RoleEnum;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponse {
    String id;
    String userName;
    String firstName;
    String lastName;
    LocalDate dateOfBirth;
    String status;
    RoleResponse role;
}
