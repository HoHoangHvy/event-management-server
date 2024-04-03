package com.javasproject.eventmanagement.configuration;

import com.javasproject.eventmanagement.dto.request.RoleCreationRequest;
import com.javasproject.eventmanagement.entity.Role;
import com.javasproject.eventmanagement.entity.User;
import com.javasproject.eventmanagement.repository.UserRepository;
import com.javasproject.eventmanagement.service.RoleService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Configuration
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class ApplicationInitConfig {

    PasswordEncoder passwordEncoder;
    RoleService roleService;
    @Bean
    ApplicationRunner applicationRunner(UserRepository userRepository) {
        return args -> {
            if(userRepository.findByUserName("admin").isEmpty()){
                User admin = new User();
                admin.setUserName("admin");
                admin.setPassword(passwordEncoder.encode("admin123"));
                Optional<Role> adminRole = roleService.findByName("ADMIN");
                if(adminRole.isEmpty()){
                    RoleCreationRequest roleCreationRequest = new RoleCreationRequest();
                    roleCreationRequest.setName("ADMIN");
                    Set<String> permissions = new HashSet<>();
                    permissions.add("ADMIN");
                    roleCreationRequest.setPermission(permissions);
                    adminRole = Optional.ofNullable(roleService.create(roleCreationRequest));
                }
                admin.setRole(adminRole);
                userRepository.save(admin);
                log.warn("Successfully created admin user");
            } else {
                log.warn("Admin user already exists");
            }
        };
    }
}
