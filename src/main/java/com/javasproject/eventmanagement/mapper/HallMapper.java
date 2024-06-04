package com.javasproject.eventmanagement.mapper;

import com.javasproject.eventmanagement.dto.request.HallCreationRequest;
import com.javasproject.eventmanagement.dto.response.HallResponse;
import com.javasproject.eventmanagement.dto.response.OptionResponse;
import com.javasproject.eventmanagement.entity.Department;
import com.javasproject.eventmanagement.entity.Hall;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface HallMapper {
    Hall toHall(HallCreationRequest request);

    @Named("toHallResponse")
    @Mapping(target = "dateEntered", source = "dateEntered", dateFormat = "yyyy-MM-dd HH:mm:ss")
    HallResponse toHallResponse(Hall hall);
    @Mapping(target = "label", source = "name")
    @Mapping(target = "value", source = "id")
    OptionResponse toOptionResponse(Hall hall);

}
