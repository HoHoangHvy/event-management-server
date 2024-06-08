package com.javasproject.eventmanagement.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Entity
@Table(name = "payments")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;
    String name;
    String type;
    String status = "Unpaid";
    LocalDateTime paymentDate;
    String paymentMethod;
    Boolean deleted = false;
    LocalDateTime dateEntered = LocalDateTime.now();
    double value;
    @ManyToOne
    @JoinColumn(name = "createdBy")
    Employee createdBy;
    @ManyToOne
    @JoinColumn(name = "confirmedBy")
    Employee confirmedBy;
    @ManyToOne
    @JoinColumn(name = "contractId")
    Contract contract;
}
