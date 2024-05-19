package com.javasproject.eventmanagement.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "events")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    @Column(nullable = false)
    String name;

    @Column(nullable = false)
    LocalDateTime startDate;

    @Column(nullable = false)
    LocalDateTime endDate;
    Boolean deleted = false;
    String description;
    LocalDate date_entered = LocalDate.now();

    @Column(nullable = false)
    String status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "createdBy")
    Employee createdBy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "approvedBy")
    Employee approvedBy; // Assuming the same User class is used for the approver

    @OneToMany(mappedBy = "events")
    Set<EventDetails> eventDetails;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idContract", referencedColumnName = "id")
    Contract contract;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idCustomer")
    Customer customer;

    @OneToMany(mappedBy = "events")
    Set<Task> tasks;

}
