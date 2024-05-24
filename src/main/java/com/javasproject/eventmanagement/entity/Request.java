package com.javasproject.eventmanagement.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "requests")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(nullable = false)
    String id;

    String name;
    String type;
    String content;
    LocalDateTime date_entered = LocalDateTime.now();
    String status;
    LocalDateTime approveDate;
    String rejectReason;
    Boolean deleted = false;

    @ManyToOne
    @JoinColumn(name = "created_by")
    Employee createdBy;

    @ManyToOne
    @JoinColumn(name = "approved_by")
    Employee approvedBy;

    @OneToMany(mappedBy = "request")
    List<RequestDepartment> requestDepartments;

    @OneToOne
    @JoinColumn(name = "resource_booking_id")
    ResourceBookingDetail resourceBookingDetail;

}
