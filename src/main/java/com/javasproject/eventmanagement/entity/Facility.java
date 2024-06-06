package com.javasproject.eventmanagement.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "facilities")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Facility {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;
    String name;
    long total;
    String type;
    long price;
    LocalDateTime dateEntered = LocalDateTime.now();
    @Transient
    long availableQuantity;
    Boolean deleted = false;
    @OneToMany(mappedBy = "facility", cascade = CascadeType.ALL)
    private Set<EventDetailFacility> eventDetailFacilities = new HashSet<>();
}
