package com.javasproject.eventmanagement.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "contracts")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Contract {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;
    String name;
    LocalDate companySignedDate;
    LocalDate customerSignedDate;
    LocalDate expirationDate;
    String status;
    double totalValue;
    double netValue;
    double taxValue;
    double discount;
    String terms;
    double sumPaid = 0;
    Boolean deleted = false;
    LocalDateTime dateEntered = LocalDateTime.now();
    String paymentTerm;
    //    String createdBy;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idEvent", referencedColumnName = "id")
    Event event;
    @OneToMany(mappedBy = "contract")
    Set<Payment> payment;

}
