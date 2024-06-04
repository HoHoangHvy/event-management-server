package com.javasproject.eventmanagement.mapper;

import com.javasproject.eventmanagement.dto.request.ThirdPartyCreationRequest;
import com.javasproject.eventmanagement.dto.response.ThirdPartyResponse;
import com.javasproject.eventmanagement.entity.ThirdParty;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface ThirdPartyMapper {
    ThirdParty toThirdParty(ThirdPartyCreationRequest request);

    @Named("toThirdPartyResponse")
    @Mapping(target = "dateEntered", source = "dateEntered", dateFormat = "yyyy-MM-dd HH:mm:ss")
    ThirdPartyResponse toThirdPartyResponse(ThirdParty thirdParty);
}
