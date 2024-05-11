package com.javasproject.eventmanagement.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "eventdetails")
@Setter
@Getter
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
    Boolean deleted = false;
    @ManyToOne
    @JoinColumn(name = "idEvent")
    Event events;
    @ManyToMany(cascade = CascadeType.ALL)
    Set<ThirdParty> thirdparties = new HashSet<>();
    @ManyToMany(cascade = CascadeType.ALL)
    Set<Facility> facilities = new HashSet<>();
    @ManyToMany(cascade = CascadeType.ALL)
    Set<Dish> dishes = new HashSet<>();
}
