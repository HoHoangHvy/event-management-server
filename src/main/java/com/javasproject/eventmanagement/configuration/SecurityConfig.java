package com.javasproject.eventmanagement.configuration;

import com.javasproject.eventmanagement.enums.Permission;
import com.javasproject.eventmanagement.enums.RoleEnum;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;

import javax.crypto.spec.SecretKeySpec;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final String[] PUBLIC_ENDPOINTS = {
            "/auth/token",
            "/auth/introspect"
    };
    @Value("${jwt.signerKey}")
    private String SIGNER_KEY;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests(request ->
                request.requestMatchers(HttpMethod.POST, PUBLIC_ENDPOINTS).permitAll()
                        .requestMatchers("/users").hasAnyRole(RoleEnum.ADMIN.name())
//                        .requestMatchers(HttpMethod.GET, "/users").hasAnyAuthority(Permission.USER_READ.name())
//                        .requestMatchers(HttpMethod.POST, "/users").hasAnyAuthority(Permission.USER_WRITE.name())
//                        .requestMatchers(HttpMethod.PUT, "/users").hasAnyAuthority(Permission.USER_WRITE.name())
//                        .requestMatchers(HttpMethod.DELETE, "/users").hasAnyAuthority(Permission.USER_DELETE.name())

                        .requestMatchers("/events").hasAnyRole(RoleEnum.ADMIN.name(), RoleEnum.MANAGER.name(), RoleEnum.USER.name())
//                        .requestMatchers(HttpMethod.GET, "/events").hasAnyAuthority(Permission.EVENT_READ.name())
//                        .requestMatchers(HttpMethod.POST, "/events").hasAnyAuthority(Permission.EVENT_WRITE.name())
//                        .requestMatchers(HttpMethod.PUT, "/events").hasAnyAuthority(Permission.EVENT_WRITE.name())
//                        .requestMatchers(HttpMethod.DELETE, "/events").hasAnyAuthority(Permission.EVENT_DELETE.name())

                        .requestMatchers("/payments").hasAnyRole(RoleEnum.ADMIN.name(), RoleEnum.MANAGER.name(), RoleEnum.USER.name())
//                        .requestMatchers(HttpMethod.GET, "/payments").hasAnyAuthority(Permission.PAYMENT_READ.name())
//                        .requestMatchers(HttpMethod.POST, "/payments").hasAnyAuthority(Permission.PAYMENT_WRITE.name())
//                        .requestMatchers(HttpMethod.PUT, "/payments").hasAnyAuthority(Permission.PAYMENT_WRITE.name())
//                        .requestMatchers(HttpMethod.DELETE, "/payments").hasAnyAuthority(Permission.PAYMENT_DELETE.name())

                        .requestMatchers("/contracts").hasAnyRole(RoleEnum.ADMIN.name(), RoleEnum.MANAGER.name(), RoleEnum.USER.name())
//                        .requestMatchers(HttpMethod.GET, "/contracts").hasAnyAuthority(Permission.CONTRACT_READ.name())
//                        .requestMatchers(HttpMethod.POST, "/contracts").hasAnyAuthority(Permission.CONTRACT_WRITE.name())
//                        .requestMatchers(HttpMethod.PUT, "/contracts").hasAnyAuthority(Permission.CONTRACT_WRITE.name())
//                        .requestMatchers(HttpMethod.DELETE, "/contracts").hasAnyAuthority(Permission.CONTRACT_DELETE.name())

                        .requestMatchers("/employees").hasAnyRole(RoleEnum.ADMIN.name(), RoleEnum.MANAGER.name())
//                        .requestMatchers(HttpMethod.GET, "/employees").hasAnyAuthority(Permission.EMPLOYEE_READ.name())
//                        .requestMatchers(HttpMethod.POST, "/employees").hasAnyAuthority(Permission.EMPLOYEE_WRITE.name())
//                        .requestMatchers(HttpMethod.PUT, "/employees").hasAnyAuthority(Permission.EMPLOYEE_WRITE.name())
//                        .requestMatchers(HttpMethod.DELETE, "/employees").hasAnyAuthority(Permission.EMPLOYEE_DELETE.name())

                        .requestMatchers("/facilities").hasAnyRole(RoleEnum.ADMIN.name(), RoleEnum.MANAGER.name(), RoleEnum.USER.name())
//                        .requestMatchers(HttpMethod.GET, "/facilities").hasAnyAuthority(Permission.FACILITY_READ.name())
//                        .requestMatchers(HttpMethod.POST, "/facilities").hasAnyAuthority(Permission.FACILITY_WRITE.name())
//                        .requestMatchers(HttpMethod.PUT, "/facilities").hasAnyAuthority(Permission.FACILITY_WRITE.name())
//                        .requestMatchers(HttpMethod.DELETE, "/facilities").hasAnyAuthority(Permission.FACILITY_DELETE.name())

                        .requestMatchers("/dishes").hasAnyRole(RoleEnum.ADMIN.name(), RoleEnum.MANAGER.name(), RoleEnum.USER.name())
//                        .requestMatchers(HttpMethod.GET, "/dishes").hasAnyAuthority(Permission.DISH_READ.name())
//                        .requestMatchers(HttpMethod.POST, "/dishes").hasAnyAuthority(Permission.DISH_WRITE.name())
//                        .requestMatchers(HttpMethod.PUT, "/dishes").hasAnyAuthority(Permission.DISH_WRITE.name())
//                        .requestMatchers(HttpMethod.DELETE, "/dishes").hasAnyAuthority(Permission.DISH_DELETE.name())
                        .anyRequest().authenticated()
        );

        httpSecurity.oauth2ResourceServer( oauth2 ->
                oauth2.jwt(jwtConfigurer ->
                        jwtConfigurer.decoder(jwtDecoder())
                                .jwtAuthenticationConverter(jwtAuthenticationConverter()))
        );
        httpSecurity.csrf(AbstractHttpConfigurer::disable);
        return httpSecurity.build();
    }
    @Bean
    JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
        jwtGrantedAuthoritiesConverter.setAuthorityPrefix("ROLE_");

        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwtGrantedAuthoritiesConverter);
        return jwtAuthenticationConverter;
    }
    @Bean
    JwtDecoder jwtDecoder() {
        SecretKeySpec secretKeySpec = new SecretKeySpec(SIGNER_KEY.getBytes(), "HS512");
        return NimbusJwtDecoder
                .withSecretKey(secretKeySpec)
                .macAlgorithm(MacAlgorithm.HS512)
                .build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }
}
