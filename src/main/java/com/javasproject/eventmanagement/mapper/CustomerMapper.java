package com.javasproject.eventmanagement.mapper;


import com.javasproject.eventmanagement.dto.request.CustomerCreationRequest;
import com.javasproject.eventmanagement.dto.response.CustomerResponse;
import com.javasproject.eventmanagement.dto.response.DepartmentResponse;
import com.javasproject.eventmanagement.dto.response.OptionResponse;
import com.javasproject.eventmanagement.entity.Customer;
import com.javasproject.eventmanagement.entity.Department;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import javax.swing.text.html.Option;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    Customer toCustomer(CustomerCreationRequest request);

    @Mapping(target = "dob", source = "dob", dateFormat = "yyyy-MM-dd")
    @Mapping(target = "dateEntered", source = "dateEntered", dateFormat = "yyyy-MM-dd HH:mm:ss")
    CustomerResponse toCustomerResponse(Customer customer);

    @Mapping(target = "label", source = "name")
    @Mapping(target = "value", source = "id")
    OptionResponse toOptionResponse(Customer customer);

}
