package com.javasproject.eventmanagement.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
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
    @Column(nullable = true)
    Boolean deleted = false;
    @Lob
    @Column
    String permission;
    @OneToMany(mappedBy = "role")
    Set<User> users;

    public Map<String, Map<String, Boolean>> getPermission() {
        if(this.permission != null){
            ObjectMapper mapper = new ObjectMapper();
            try {
                TypeReference<Map<String, Map<String, Boolean>>> typeRef = new TypeReference<>() {};
                return mapper.readValue(permission, typeRef);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
