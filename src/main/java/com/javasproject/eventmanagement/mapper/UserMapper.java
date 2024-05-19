package com.javasproject.eventmanagement.mapper;

import com.javasproject.eventmanagement.dto.request.UserCreationRequest;
import com.javasproject.eventmanagement.dto.request.UserUpdateRequest;
import com.javasproject.eventmanagement.dto.response.UserListResponse;
import com.javasproject.eventmanagement.dto.response.UserResponse;
import com.javasproject.eventmanagement.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Optional;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserCreationRequest request);
    User toUserFromUpdate(UserUpdateRequest request);
    UserResponse toUserResponse(User user);

    @Mapping(target = "roleName", source = "role.name")
    @Mapping(target = "employeeName", source = "employee.name")
    @Mapping(target = "roleId", source = "role.id")
    UserListResponse toUserListResponse(User user);
}
