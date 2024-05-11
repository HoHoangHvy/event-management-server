package com.javasproject.eventmanagement.mapper;


import com.javasproject.eventmanagement.dto.request.EmployeeCreationRequest;
import com.javasproject.eventmanagement.dto.response.EmployeeResponse;
import com.javasproject.eventmanagement.entity.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
    Employee toEmployee(EmployeeCreationRequest request);
    @Mapping(target = "dob", source = "dob", dateFormat = "yyyy-MM-dd")
    @Mapping(source = "employee.department.name", target = "departmentName")
    @Mapping(source = "employee.user.role.id", target = "roleId")
    @Mapping(source = "employee.department.id", target = "departmentId")
    EmployeeResponse toEmployeeResponse(Employee employee);
}
