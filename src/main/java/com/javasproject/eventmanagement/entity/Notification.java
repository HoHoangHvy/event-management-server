package com.javasproject.eventmanagement.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "notifications")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(nullable = false)
    String id;
    String name;
    String type;
    String content;
    LocalDateTime date_entered = LocalDateTime.now();
    Boolean deleted = false;
    Boolean isRead = false;
    String parentId;
    String parentType;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    Employee employee;
}
