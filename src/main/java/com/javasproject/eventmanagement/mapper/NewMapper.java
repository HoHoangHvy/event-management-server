package com.javasproject.eventmanagement.mapper;

import com.javasproject.eventmanagement.dto.response.NewResponse;
import com.javasproject.eventmanagement.entity.New;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface NewMapper {

     @Mapping(target = "createdByName", source = "employee.name")
     @Mapping(target = "dateEntered", source = "date_entered", dateFormat = "yyyy-MM-dd HH:mm:ss")
     NewResponse toNewResponse(New news);
}
