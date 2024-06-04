package com.javasproject.eventmanagement.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "thirdparties")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ThirdParty {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;
    String name;
    String supplier;
    Long cost;
    Long price;
    String type;
    Boolean deleted = false;
    LocalDateTime dateEntered = LocalDateTime.now();

    @OneToMany(mappedBy = "thirdParty", cascade = CascadeType.ALL)
    private Set<EventDetailThirdParty> eventDetailThirdParties = new HashSet<>();

}
