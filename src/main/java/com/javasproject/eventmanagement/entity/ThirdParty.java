package com.javasproject.eventmanagement.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
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
    String rebate;
    String type;
    Boolean deleted = false;
    LocalDate date_entered = LocalDate.now();

    @OneToMany(mappedBy = "thirdParty", cascade = CascadeType.ALL)
    private Set<EventDetailThirdParty> eventDetailThirdParties = new HashSet<>();

}
