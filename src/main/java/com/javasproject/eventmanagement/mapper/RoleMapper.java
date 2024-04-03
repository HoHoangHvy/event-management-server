package com.javasproject.eventmanagement.mapper;

import com.javasproject.eventmanagement.dto.request.RoleCreationRequest;
import com.javasproject.eventmanagement.dto.response.RoleResponse;
import com.javasproject.eventmanagement.entity.Role;
import jakarta.persistence.OneToMany;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    Role toRole(RoleCreationRequest request);
    RoleResponse toRoleResponse(Role role);
}
