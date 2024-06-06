package com.javasproject.eventmanagement.entity;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "departments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;
    String name;
    LocalDateTime date_entered = LocalDateTime.now();

    @Column(nullable = true)
    Boolean deleted = false;
    @OneToMany(mappedBy = "department")
    Set<Employee> employees;

    @OneToMany(mappedBy = "department")
    List<RequestDepartment> requestDepartments;
}
