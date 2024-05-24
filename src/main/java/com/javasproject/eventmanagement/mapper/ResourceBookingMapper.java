package com.javasproject.eventmanagement.mapper;


import com.javasproject.eventmanagement.dto.response.ResourceBookingResponse;
import com.javasproject.eventmanagement.entity.ResourceBookingDetail;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ResourceBookingMapper {


    @Mapping(target = "startDate", source = "startDate", dateFormat = "yyyy-MM-dd HH:mm:ss")
    @Mapping(target = "endDate", source = "endDate", dateFormat = "yyyy-MM-dd HH:mm:ss")
    @Mapping(target = "dateEntered", source = "dateEntered", dateFormat = "yyyy-MM-dd HH:mm:ss")
    @Mapping(target = "employeeName", source = "employee.name")
    @Mapping(target = "employeeId", source = "employee.id")
    @Mapping(target = "resourceName", source = "resource.name")
    @Mapping(target = "resourceId", source = "resource.id")
    ResourceBookingResponse toResourceBookingResponse(ResourceBookingDetail resource);

}
