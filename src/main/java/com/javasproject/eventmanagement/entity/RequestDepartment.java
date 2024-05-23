package com.javasproject.eventmanagement.entity;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "request_department")
public class RequestDepartment {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    @ManyToOne
    @JoinColumn(name = "request_id")
    Request request;

    @ManyToOne
    @JoinColumn(name = "department_id")
    Department department;

    // Additional fields
    LocalDateTime resolvedDate;
    String resolveStatus = "Pending";
    String response;
    String note;
    Boolean deleted = false;
    @ManyToOne
    @JoinColumn(name = "resolved_by")
    Employee resolvedBy;
    LocalDateTime date_entered = LocalDateTime.now();
}
