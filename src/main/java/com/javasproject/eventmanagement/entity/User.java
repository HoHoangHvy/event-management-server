package com.javasproject.eventmanagement.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.util.Optional;

@Entity
@Table(name = "users")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;
    String userName;
    String password;
    String status;
    LocalDateTime dateEntered = LocalDateTime.now();
    Boolean deleted = false;

    @ManyToOne
    @JoinColumn(name = "idRole")
    Role role;
    @OneToOne
    @JoinColumn(name = "idEmployee")
    Employee employee;
    

    public void setRole(Optional<Role> adminRole) {
        role = adminRole.orElse(null);
    }
}
