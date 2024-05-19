package com.javasproject.eventmanagement.mapper;


import com.javasproject.eventmanagement.dto.response.DepartmentListResponse;
import com.javasproject.eventmanagement.dto.response.DepartmentResponse;
import com.javasproject.eventmanagement.dto.response.OptionResponse;
import com.javasproject.eventmanagement.entity.Department;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import javax.swing.text.html.Option;

@Mapper(componentModel = "spring")
public interface DepartmentMapper {
    @Mapping(target = "totalEmployee", expression = "java(department.getEmployees().size())")
    DepartmentListResponse toDepartmentListResponse(Department department);
    DepartmentResponse toDepartmentResponse(Department department);

    @Mapping(target = "label", source = "name")
    @Mapping(target = "value", source = "id")
    OptionResponse toOptionResponse(Department department);
}
