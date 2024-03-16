package com.javasproject.eventmanagement.mapper;

import com.javasproject.eventmanagement.dto.request.UserCreationRequest;
import com.javasproject.eventmanagement.dto.response.UserResponse;
import com.javasproject.eventmanagement.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserCreationRequest request);

    @Mapping(source = "firstName", target = "lastName")
    UserResponse toUserResponse(User user);
}
