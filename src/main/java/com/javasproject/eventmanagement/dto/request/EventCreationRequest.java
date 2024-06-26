package com.javasproject.eventmanagement.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EventCreationRequest {
    String name;
    LocalDateTime startDate;
    LocalDateTime endDate;
    String description;
    String hallId;
    String type;
    String customerId;
    Set<EventDetailCreationRequest> dishDetails;
    Set<EventDetailCreationRequest> facilityDetails;
    Set<EventDetailCreationRequest> thirdpartyDetails;
}
