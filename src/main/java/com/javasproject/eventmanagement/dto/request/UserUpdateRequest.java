package com.javasproject.eventmanagement.dto.request;

import com.javasproject.eventmanagement.entity.Employee;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)

public class UserUpdateRequest {
    @Size(min = 4, message = "USERNAME_INVALID_EXCEPTION")
    String userName;
    String status;
    String roleId;
}
