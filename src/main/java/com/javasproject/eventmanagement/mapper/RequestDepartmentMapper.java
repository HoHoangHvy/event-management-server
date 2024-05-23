package com.javasproject.eventmanagement.mapper;

import com.javasproject.eventmanagement.dto.response.RequestDepartmentListResponse;
import com.javasproject.eventmanagement.dto.response.RequestDepartmentResponse;
import com.javasproject.eventmanagement.entity.RequestDepartment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.Qualifier;

@Mapper(componentModel = "spring")
public interface RequestDepartmentMapper {

    @Named("toRequestDepartmentResponse")
    @Mapping(target = "departmentName", source = "department.name")
    @Mapping(target = "departmentId", source = "department.id")
    RequestDepartmentResponse toRequestDepartmentResponse(RequestDepartment requestDepartment);

    @Named("#toRequestDepartmentListResponse")
    @Mapping(target = "requestName", source = "request.name")
    @Mapping(target = "resolveByName", source = "resolvedBy.name")
    @Mapping(target = "departmentName", source = "department.name")
    @Mapping(target = "departmentId", source = "department.id")
    @Mapping(target = "resolvedDate", source = "resolvedDate", dateFormat = "yyyy-MM-dd HH:mm:ss")
    @Mapping(target = "dateEntered", source = "date_entered", dateFormat = "yyyy-MM-dd HH:mm:ss")
    RequestDepartmentListResponse toRequestDepartmentListResponse(RequestDepartment requestDepartment);
}
