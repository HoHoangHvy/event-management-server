package com.javasproject.eventmanagement.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FacilityResponse {
    String id;
    String name;
    long total;
    String type;
    long price;
    String dateEntered;
    Boolean deleted;
    long availableQuantity;
}
