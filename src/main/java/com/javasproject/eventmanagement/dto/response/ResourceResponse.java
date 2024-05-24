package com.javasproject.eventmanagement.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ResourceResponse {
    String id;
    String name;
    String type;
    int totalQuantity;
    int availableQuantity;
    String dateEntered;
    Boolean deleted;
}
