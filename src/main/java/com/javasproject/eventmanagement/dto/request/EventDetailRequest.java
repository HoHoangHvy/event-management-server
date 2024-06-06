package com.javasproject.eventmanagement.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EventDetailRequest {
    String type;
    Set<EventDetailCreationRequest> dishDetails;
    Set<EventDetailCreationRequest> facilityDetails;
    Set<EventDetailCreationRequest> thirdpartyDetails;
}
