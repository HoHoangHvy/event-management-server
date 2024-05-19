package com.javasproject.eventmanagement.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "resources")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Resource {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(nullable = false)
    String id;

    String name;
    String type;
    int totalQuantity;
    LocalDate date_entered = LocalDate.now();
    Boolean deleted = false;

    @OneToMany(mappedBy = "resource")
    List<ResourceBookingDetail> bookingDetails;
}
