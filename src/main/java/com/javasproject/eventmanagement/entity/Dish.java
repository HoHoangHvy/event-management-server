package com.javasproject.eventmanagement.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "dishes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Dish {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;
    String name;
    long price;
    long cost;
    String unit;
    Boolean deleted = false;
    LocalDate date_entered = LocalDate.now();

    @OneToMany(mappedBy = "dish", cascade = CascadeType.ALL)
    private Set<EventDetailDish> eventDetailDishes = new HashSet<>();

}
