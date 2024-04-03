package com.javasproject.eventmanagement.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EventDetailCreationRequest {
    String name;
    long price;
    long cost;
    String type;
    Set<String> dishes;
    Set<String> facilities;
    Set<String> thirdparties;
}
