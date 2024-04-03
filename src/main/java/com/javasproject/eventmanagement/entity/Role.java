package com.javasproject.eventmanagement.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "roles")
public class Role  {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;
    String name;
    String permission;
    @OneToMany(mappedBy = "role")
    Set<User> users;

    public Set<String> getPermission() {
        if(this.permission != null){
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                return new HashSet<String>(objectMapper.readValue(permission, HashSet.class));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
                return null;
            }
        }
        return new HashSet<>();
    }
}
