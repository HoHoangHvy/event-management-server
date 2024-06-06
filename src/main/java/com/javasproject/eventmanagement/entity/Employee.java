package com.javasproject.eventmanagement.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;

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
    LocalDateTime dob;
    LocalDateTime startDate;
    String email;
    @Column(nullable = true)
    Boolean deleted = false;
    LocalDateTime date_entered = LocalDateTime.now();


    @OneToOne(mappedBy = "employee")
    User user;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idDepartment")
    Department department;

    @OneToOne(mappedBy = "employee")
    Task task;


    @OneToMany(mappedBy = "employee")
    List<New> news;

    @OneToMany(mappedBy = "employee")
    List<Notification> notifications;

    @OneToMany(mappedBy = "createdBy")
    List<Request> createdRequests;

    @OneToMany(mappedBy = "approvedBy")
    List<Request> approvedRequests;

    @OneToMany(mappedBy = "employee")
    List<ResourceBookingDetail> resourceBookingDetails;
}
