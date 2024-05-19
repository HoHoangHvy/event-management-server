package com.javasproject.eventmanagement.configuration;

import com.javasproject.eventmanagement.dto.request.RoleCreationRequest;
import com.javasproject.eventmanagement.entity.Employee;
import com.javasproject.eventmanagement.entity.Role;
import com.javasproject.eventmanagement.entity.User;
import com.javasproject.eventmanagement.repository.EmployeeRepository;
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

import java.util.*;

@Configuration
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class ApplicationInitConfig {

    PasswordEncoder passwordEncoder;
    RoleService roleService;
    @Bean
    ApplicationRunner applicationRunner(UserRepository userRepository, EmployeeRepository employeeRepository) {
        return args -> {
            if(userRepository.findByUserName("admin").isEmpty()){
                Employee employee = new Employee();
                employee.setName("Admin");
                employee.setPhone("1234567890");
                employee.setEmail("admin@gmail.com");
                employeeRepository.save(employee);

                User admin = new User();
                admin.setUserName("admin");
                admin.setPassword(passwordEncoder.encode("admin123"));
                Optional<Role> adminRole = roleService.findByName("ADMIN");
                if(adminRole.isEmpty()) adminRole = Optional.ofNullable(this.createRoleAdmin());
                admin.setRole(adminRole);
                admin.setEmployee(employee);
                userRepository.save(admin);


                log.warn("Successfully created admin user");
            } else {
                log.warn("Admin user already exists");
            }
        };
    }

    public Role createRoleAdmin(){
        RoleCreationRequest roleCreationRequest = new RoleCreationRequest();
        roleCreationRequest.setName("ADMIN");
        Map<String, Map<String, Boolean>> permissions = new HashMap<>();

        permissions.put("Contracts", new HashMap<>(Map.of("UPSERT", true, "VIEW", true, "DELETE", true)));
        permissions.put("Customers", new HashMap<>(Map.of("UPSERT", true, "VIEW", true, "DELETE", true)));
        permissions.put("Departments", new HashMap<>(Map.of("UPSERT", true, "VIEW", true, "DELETE", true)));
        permissions.put("Dishes", new HashMap<>(Map.of("UPSERT", true, "VIEW", true, "DELETE", true)));
        permissions.put("Employees", new HashMap<>(Map.of("UPSERT", true, "VIEW", true, "DELETE", true)));
        permissions.put("EventDetails", new HashMap<>(Map.of("UPSERT", true, "VIEW", true, "DELETE", true)));
        permissions.put("Events", new HashMap<>(Map.of("UPSERT", true, "VIEW", true, "DELETE", true)));
        permissions.put("Facilities", new HashMap<>(Map.of("UPSERT", true, "VIEW", true, "DELETE", true)));
        permissions.put("Payments", new HashMap<>(Map.of("UPSERT", true, "VIEW", true, "DELETE", true)));
        permissions.put("Roles", new HashMap<>(Map.of("UPSERT", true, "VIEW", true, "DELETE", true)));
        permissions.put("Tasks", new HashMap<>(Map.of("UPSERT", true, "VIEW", true, "DELETE", true)));
        permissions.put("ThirdParties", new HashMap<>(Map.of("UPSERT", true, "VIEW", true, "DELETE", true)));
        permissions.put("Users", new HashMap<>(Map.of("UPSERT", true, "VIEW", true, "DELETE", true)));

        roleCreationRequest.setPermission(permissions);
        Optional<Role> adminRole = Optional.ofNullable(roleService.create(roleCreationRequest));
        return adminRole.orElse(null);
    }
}
