package com.javasproject.eventmanagement.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
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
    LocalDate date_entered = LocalDate.now();

    Boolean deleted = false;
    @OneToMany(mappedBy = "facility", cascade = CascadeType.ALL)
    private Set<EventDetailFacility> eventDetailFacilities = new HashSet<>();
}
