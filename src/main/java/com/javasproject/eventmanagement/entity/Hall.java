package com.javasproject.eventmanagement.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "halls")
public class Hall {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;
    String name;
    int scale;
    String location;
    LocalDateTime dateEntered = LocalDateTime.now();
    Boolean inUse = true;
    Boolean deleted = false;

    @OneToMany(mappedBy = "hall")
    Set<Event> event;
}
