package com.javasproject.eventmanagement.mapper;


import com.javasproject.eventmanagement.dto.response.EventResponse;
import com.javasproject.eventmanagement.dto.response.OptionResponse;
import com.javasproject.eventmanagement.entity.Event;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EventMapper {


    @Mapping(target = "createdByName", source = "createdBy.name")
    @Mapping(target = "approvedByName", source = "approvedBy.name")
    @Mapping(target = "customerName", source = "customer.name")
    @Mapping(target = "customerId", source = "customer.id")
    @Mapping(target = "hallName", source = "hall.name")
    @Mapping(target = "hallId", source = "hall.id")
    @Mapping(target = "contractName", source = "contract.name")
    @Mapping(target = "contractId", source = "contract.id")
    @Mapping(target = "startDate", source = "startDate", dateFormat = "yyyy-MM-dd HH:mm:ss")
    @Mapping(target = "endDate", source = "endDate", dateFormat = "yyyy-MM-dd HH:mm:ss")
    EventResponse toEventResponse(Event event);

    @Mapping(target = "label", source = "name")
    @Mapping(target = "value", source = "id")
    @Mapping(target = "status", source = "status")
    OptionResponse toOptionResponse(Event event);
}
