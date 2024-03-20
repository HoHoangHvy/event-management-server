package com.javasproject.eventmanagement.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "customers")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults( level = AccessLevel.PRIVATE)
public class Customers {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;
    String name;
    String phone;
    String email;
    String type;
}
