package com.javasproject.eventmanagement.mapper;


import com.javasproject.eventmanagement.dto.response.EventDetailsResponse;
import com.javasproject.eventmanagement.dto.response.EventResponse;
import com.javasproject.eventmanagement.entity.Event;
import com.javasproject.eventmanagement.entity.EventDetails;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface EventDetailMapper {

    @Named("eventDetails")
    @Mapping(target = "eventDetailDishes", source = "eventDetailDishes")
    @Mapping(target = "eventDetailFacilities", source = "facilities")
    @Mapping(target = "eventDetailThirdParties", source = "thirdparties")
    EventDetailsResponse toEventDetailsResponse(EventDetails event);
}
