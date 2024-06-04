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
public class ThirdPartyResponse {
    String id;
    String name;
    String supplier;
    Long cost;
    Long price;
    String type;
    String dateEntered;
    Boolean deleted;
}
