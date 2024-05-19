package com.javasproject.eventmanagement.mapper;

import com.javasproject.eventmanagement.dto.request.ResourceCreationRequest;
import com.javasproject.eventmanagement.dto.response.ResourceResponse;
import com.javasproject.eventmanagement.entity.Resource;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ResourceMapper {
    Resource toResource(ResourceCreationRequest request);
    @Mapping(target = "dateEntered", source = "date_entered", dateFormat = "yyyy-MM-dd")
    ResourceResponse toResourceResponse(Resource resource);
}
