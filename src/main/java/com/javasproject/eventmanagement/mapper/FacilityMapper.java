package com.javasproject.eventmanagement.mapper;

import com.javasproject.eventmanagement.dto.request.FacilityCreationRequest;
import com.javasproject.eventmanagement.dto.response.FacilityResponse;
import com.javasproject.eventmanagement.entity.Facility;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface FacilityMapper {
    Facility toFacility(FacilityCreationRequest request);

    @Named("toFacilityResponse")
    @Mapping(target = "dateEntered", source = "dateEntered", dateFormat = "yyyy-MM-dd HH:mm:ss")
    FacilityResponse toFacilityResponse(Facility facility);
}
