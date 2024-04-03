package com.javasproject.eventmanagement.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

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
    @ManyToMany(mappedBy = "thirdparties", cascade = CascadeType.ALL)
    private Set<EventDetails> eventDetails = new HashSet<>();

}
