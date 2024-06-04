package com.javasproject.eventmanagement.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HallResponse {
    String id;
    String name;
    int scale;
    String location;
    String dateEntered;
    Boolean inUse;
    Boolean deleted;
}
