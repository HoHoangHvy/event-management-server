package com.javasproject.eventmanagement.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "resource_booking_details")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ResourceBookingDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(nullable = false)
    String id;

    LocalDateTime startDate;
    LocalDateTime endDate;
    Integer quantity;
    String status;
    String reason;
    LocalDateTime dateEntered = LocalDateTime.now();
    Boolean deleted = false;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    Employee employee;

    @ManyToOne
    @JoinColumn(name = "resource_id")
    Resource resource;

    @OneToOne(mappedBy = "resourceBookingDetail")
    Request request;
}
