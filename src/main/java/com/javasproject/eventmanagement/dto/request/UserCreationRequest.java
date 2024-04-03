package com.javasproject.eventmanagement.dto.request;

import com.javasproject.eventmanagement.entity.Role;
import com.javasproject.eventmanagement.enums.RoleEnum;
import com.javasproject.eventmanagement.service.RoleService;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCreationRequest {
    @Size(min = 4, message = "USERNAME_INVALID_EXCEPTION")
    String userName;

    @Size (min = 8, message = "PASSWORD_INVALID_EXCEPTION")
    String password;
    String firstName;
    String lastName;
    LocalDate dateOfBirth;
    String status;
    String roleId;
}
