package com.javasproject.eventmanagement.dto.response;

import com.javasproject.eventmanagement.entity.Role;
import com.javasproject.eventmanagement.enums.RoleEnum;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponse {
    String id;
    String userName;
    String status;
    RoleResponse role;
    EmployeeResponse employee;
}
