package com.javasproject.eventmanagement.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalDateTime;

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
    LocalDateTime date_entered = LocalDateTime.now();

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
