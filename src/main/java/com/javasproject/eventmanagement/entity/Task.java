package com.javasproject.eventmanagement.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Entity
@Table(name = "tasks")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;
    String name;
    String type;
    String status;
    LocalDate startDate;
    LocalDate dueDate;
    LocalDate finishedDate;
    String priority;
    String description;
    String progress;
    Boolean deleted = false;

    @ManyToOne
    @JoinColumn(name = "idEvent")
    Event events;

    @OneToOne
    @JoinColumn(name = "idEmployee")
    Employee employee;
}
