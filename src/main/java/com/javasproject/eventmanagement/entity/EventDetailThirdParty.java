package com.javasproject.eventmanagement.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "event_detail_thirdparty")
@Entity
public class EventDetailThirdParty {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;
    LocalDate date_entered = LocalDate.now();

    @ManyToOne
    @JoinColumn(name = "event_details_id")
    EventDetails eventDetails;

    @ManyToOne
    @JoinColumn(name = "thirdparty_id")
    ThirdParty thirdParty;

    // Additional fields
    Long quantity;
    Long bookingPrice;
}
