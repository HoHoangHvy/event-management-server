package com.javasproject.eventmanagement.mapper;


import com.javasproject.eventmanagement.dto.request.EmployeeCreationRequest;
import com.javasproject.eventmanagement.dto.response.EmployeeResponse;
import com.javasproject.eventmanagement.entity.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
    Employee toEmployee(EmployeeCreationRequest request);

    @Named("toEmployeeResponse")
    @Mapping(target = "dob", source = "dob", dateFormat = "yyyy-MM-dd")
    @Mapping(target = "departmentName",source = "department.name")
    @Mapping(source = "user.role.id", target = "roleId")
    @Mapping(source = "department.id", target = "departmentId")
    EmployeeResponse toEmployeeResponse(Employee employee);
}
