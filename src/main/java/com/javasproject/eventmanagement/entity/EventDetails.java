package com.javasproject.eventmanagement.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "eventdetails")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EventDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;
    String name;
    long price;
    long cost;
    String type;
    @ManyToOne
    @JoinColumn(name = "events_id")
    Event events;
    @ManyToMany(cascade = CascadeType.ALL)
    Set<ThirdParty> thirdparties = new HashSet<>();
    @ManyToMany(cascade = CascadeType.ALL)
    Set<Facility> facilities = new HashSet<>();
    @ManyToMany(cascade = CascadeType.ALL)
    Set<Dish> dishes = new HashSet<>();
}
