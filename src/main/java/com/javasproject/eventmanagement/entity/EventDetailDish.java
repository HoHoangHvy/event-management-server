package com.javasproject.eventmanagement.entity;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

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
    LocalDateTime date_entered = LocalDateTime.now();

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
