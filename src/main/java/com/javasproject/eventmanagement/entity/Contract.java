package com.javasproject.eventmanagement.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

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
    Date dateCreated;
    Date companySignedDate;
    Date customerSignedDate;
    Date expirationDate;
    String status;
    long totalValue;
    int discount;
    String terms;
    long sumPaid;
    Boolean deleted = false;

    //    String createdBy;
    @OneToOne(mappedBy = "contract")
    Event event;
    @OneToMany(mappedBy = "contract")
    Set<Payment> payment;

}
