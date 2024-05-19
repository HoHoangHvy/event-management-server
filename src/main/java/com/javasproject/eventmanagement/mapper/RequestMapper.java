package com.javasproject.eventmanagement.mapper;

import com.javasproject.eventmanagement.dto.request.RequestCreationRequest;
import com.javasproject.eventmanagement.dto.response.RequestResponse;
import com.javasproject.eventmanagement.entity.Request;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RequestMapper {
    Request toRequest(RequestCreationRequest request);
    @Mapping(target = "dateEntered", source = "date_entered", dateFormat = "yyyy-MM-dd")
    @Mapping(target = "approveDate", source = "approveDate", dateFormat = "yyyy-MM-dd")
    RequestResponse toRequestResponse(Request request);
}
