package com.javasproject.eventmanagement.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.Set;

@Data
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

    String description;

    @Column(nullable = false)
    String status;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "created_by")
//    Employees createdBy;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "approved")
//    User approvedBy; // Assuming the same User class is used for the approver

//    @OneToOne(mappedBy = "event", fetch = FetchType.LAZY)
//    Customer idCustomers;

    @OneToMany(mappedBy = "events")
    Set<EventDetails> eventDetails;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "contract_id", referencedColumnName = "id")
    Contract contract;

}
