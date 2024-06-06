package com.javasproject.eventmanagement.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
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
    LocalDateTime date_entered = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "idEvent")
    Event events;
    @OneToMany(mappedBy = "eventDetails", cascade = CascadeType.ALL)
    Set<EventDetailThirdParty> thirdparties = new HashSet<>();
    @OneToMany(mappedBy = "eventDetails", cascade = CascadeType.ALL)
    Set<EventDetailFacility> facilities = new HashSet<>();
    @OneToMany(mappedBy = "eventDetails", cascade = CascadeType.ALL)
    Set<EventDetailDish> eventDetailDishes = new HashSet<>();
}
