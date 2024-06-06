package com.javasproject.eventmanagement.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "event_detail_facility")
public class EventDetailFacility {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    @ManyToOne
    @JoinColumn(name = "event_details_id")
    EventDetails eventDetails;

    @ManyToOne
    @JoinColumn(name = "facility_id")
    Facility facility;

    // Additional fields
    Long quantity;
    Long bookingPrice;
    LocalDateTime date_entered = LocalDateTime.now();

}
