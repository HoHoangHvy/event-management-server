package com.javasproject.eventmanagement.service;

import com.javasproject.eventmanagement.dto.request.CustomerCreationRequest;
import com.javasproject.eventmanagement.dto.request.CustomerUpdateRequest;
import com.javasproject.eventmanagement.dto.response.CustomerResponse;
import com.javasproject.eventmanagement.dto.response.OptionResponse;
import com.javasproject.eventmanagement.entity.Customer;
import com.javasproject.eventmanagement.mapper.CustomerMapper;
import com.javasproject.eventmanagement.repository.CustomerRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    private CustomerMapper customerMapper;

    public CustomerResponse createCustomer(CustomerCreationRequest request) {
        Customer customer = new Customer();

        customer.setName(request.getName());
        customer.setPhone(request.getPhone());
        customer.setEmail(request.getEmail());
        customer.setType(request.getType());
        customer.setDob(request.getDob());

        return customerMapper.toCustomerResponse(customerRepository.save(customer));
    }

    public CustomerResponse updateCustomer(String customerId, CustomerUpdateRequest request) {
        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new RuntimeException("Customer not found"));

        customer.setName(request.getName());
        customer.setPhone(request.getPhone());
        customer.setEmail(request.getEmail());
        customer.setType(request.getType());
        customer.setDob(request.getDob());

        return customerMapper.toCustomerResponse(customerRepository.save(customer));
    }

    public void deleteCustomer(String customerId) {
        customerRepository.deleteById(customerId);
    }

    public List<CustomerResponse> getAllCustomer() {
        return customerRepository.findAll().stream().map(customerMapper::toCustomerResponse).collect(Collectors.toList());
    }

    public CustomerResponse getCustomerById(String id) {
        return customerMapper.toCustomerResponse(customerRepository.findById(id).orElseThrow(() -> new RuntimeException("Customer not found")));
    }

    public Customer getCustomerObjectById(String id) {
        return customerRepository.findById(id).orElseThrow(() -> new RuntimeException("Customer not found"));
    }

    public List<OptionResponse> getAllOption() {
        return customerRepository.findAll().stream().map(customerMapper::toOptionResponse).collect(Collectors.toList());
    }

}
