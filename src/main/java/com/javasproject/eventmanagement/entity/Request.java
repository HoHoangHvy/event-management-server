package com.javasproject.eventmanagement.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

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
    LocalDate date_entered = LocalDate.now();
    String status;
    LocalDate approveDate;
    String rejectReason;
    Boolean deleted = false;

    @ManyToOne
    @JoinColumn(name = "created_by")
    Employee createdBy;

    @ManyToOne
    @JoinColumn(name = "approved_by")
    Employee approvedBy;
}
