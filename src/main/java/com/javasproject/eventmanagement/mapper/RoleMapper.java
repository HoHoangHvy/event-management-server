package com.javasproject.eventmanagement.mapper;

import com.javasproject.eventmanagement.dto.request.RoleCreationRequest;
import com.javasproject.eventmanagement.dto.response.OptionResponse;
import com.javasproject.eventmanagement.dto.response.RoleListResponse;
import com.javasproject.eventmanagement.dto.response.RoleResponse;
import com.javasproject.eventmanagement.entity.Department;
import com.javasproject.eventmanagement.entity.Role;
import jakarta.persistence.OneToMany;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    Role toRole(RoleCreationRequest request);

    RoleResponse toRoleResponse(Role role);

    @Mapping(target = "totalUser", expression = "java(role.getUsers().size())")
    @Mapping(target = "dateEntered", source = "date_entered", dateFormat = "dd-MM-yyyy")
    RoleListResponse toRoleListResponse(Role role);

    @Mapping(target = "label", source = "name")
    @Mapping(target = "value", source = "id")
    OptionResponse toOptionResponse(Role role);
}
