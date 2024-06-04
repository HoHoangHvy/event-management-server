package com.javasproject.eventmanagement.mapper;

import com.javasproject.eventmanagement.dto.request.DishCreationRequest;
import com.javasproject.eventmanagement.dto.response.DishResponse;
import com.javasproject.eventmanagement.entity.Dish;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")

public interface DishMapper {
    Dish toDish(DishCreationRequest request);

    @Mapping(target="dateEntered", source="dateEntered", dateFormat = "yyyy-MM-dd HH:mm:ss")
    DishResponse toDishResponse(Dish dish);
}
