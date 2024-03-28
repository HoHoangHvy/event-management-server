package com.javasproject.eventmanagement.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "eventdetails")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EventDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;
    String name;
    long price;
    long cost;
    String type;
    String idEvent;
}
