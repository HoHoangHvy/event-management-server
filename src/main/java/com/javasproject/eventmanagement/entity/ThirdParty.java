package com.javasproject.eventmanagement.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "thirdparties")
@Data
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
}
