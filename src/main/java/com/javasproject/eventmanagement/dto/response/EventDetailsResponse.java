package com.javasproject.eventmanagement.dto.response;

import com.javasproject.eventmanagement.entity.EventDetailDish;
import com.javasproject.eventmanagement.entity.EventDetailFacility;
import com.javasproject.eventmanagement.entity.EventDetailThirdParty;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EventDetailsResponse {
    String id;
    String name;
    Set<EventDetailDish> eventDetailDishes;
    Set<EventDetailFacility> eventDetailFacilities;
    Set<EventDetailThirdParty> eventDetailThirdParties;
}
