package com.javasproject.eventmanagement.mapper;


import com.javasproject.eventmanagement.dto.response.ResourceBookingResponse;
import com.javasproject.eventmanagement.entity.ResourceBookingDetail;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ResourceBookingMapper {

    ResourceBookingResponse toResourceBookingResponse(ResourceBookingDetail resource);

}
