package com.javasproject.eventmanagement.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
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
    LocalDate date_entered = LocalDate.now();

    @Column(nullable = true)
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
