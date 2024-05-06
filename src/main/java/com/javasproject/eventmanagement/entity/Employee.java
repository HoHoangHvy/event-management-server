package com.javasproject.eventmanagement.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Entity
@Table(name = "employees")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults( level = AccessLevel.PRIVATE)
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(nullable = false)
    String id;
    String name;
    String phone;
    String empLevel;
    String gender;
    String status;
    LocalDate dob;
    LocalDate startDate;
    String email;

    @OneToOne(mappedBy = "employee")
    User user;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idDepartment")
    Department department;

    @OneToOne(mappedBy = "employee")
    Task task;

}
