package com.javasproject.eventmanagement.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "facilities")
@Data
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
    @ManyToMany(mappedBy = "facilities", cascade = CascadeType.ALL)
    private Set<EventDetails> eventDetails = new HashSet<>();
}
