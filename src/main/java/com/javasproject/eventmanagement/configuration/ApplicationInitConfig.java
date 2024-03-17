package com.javasproject.eventmanagement.configuration;

import com.javasproject.eventmanagement.entity.User;
import com.javasproject.eventmanagement.enums.RoleEnum;
import com.javasproject.eventmanagement.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class ApplicationInitConfig {

    PasswordEncoder passwordEncoder;

    @Bean
    ApplicationRunner applicationRunner(UserRepository userRepository) {
        return args -> {
            if(userRepository.findByUserName("admin").isEmpty()){
                User admin = User.builder()
                        .userName("admin")
                        .password(passwordEncoder.encode("admin123"))
                        .role(RoleEnum.ADMIN.name())
                        .build();
                userRepository.save(admin);
                log.warn("Successfully created admin user");
            } else {
                log.warn("Admin user already exists");
            }
        };
    }
}
