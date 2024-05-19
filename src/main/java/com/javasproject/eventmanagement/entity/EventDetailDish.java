package com.javasproject.eventmanagement.entity;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "event_detail_dish")
public class EventDetailDish {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;
    LocalDate date_entered = LocalDate.now();

    @ManyToOne
    @JoinColumn(name = "event_details_id")
    EventDetails eventDetails;

    @ManyToOne
    @JoinColumn(name = "dish_id")
    Dish dish;

    // Additional fields
    Long quantity;
    Long bookingPrice;

}
