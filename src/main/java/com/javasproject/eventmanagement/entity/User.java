package com.javasproject.eventmanagement.entity;

import com.javasproject.eventmanagement.enums.RoleEnum;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Optional;
import java.util.Set;

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
