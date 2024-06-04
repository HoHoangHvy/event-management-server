package com.javasproject.eventmanagement.service;

import com.javasproject.eventmanagement.dto.request.CustomerCreationRequest;
import com.javasproject.eventmanagement.dto.response.CustomerResponse;
import com.javasproject.eventmanagement.dto.response.OptionResponse;
import com.javasproject.eventmanagement.entity.Customer;
import com.javasproject.eventmanagement.mapper.CustomerMapper;
import com.javasproject.eventmanagement.repository.CustomerRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Service
public class CustomerService {
    CustomerRepository customerRepository;
    CustomerMapper customerMapper;

    public CustomerResponse createCustomer(CustomerCreationRequest request) {
        Customer customer = customerMapper.toCustomer(request);
        customer.setDateEntered(LocalDateTime.now());
        customer.setDeleted(false);
        Customer savedCustomer = customerRepository.save(customer);
        return customerMapper.toCustomerResponse(savedCustomer);
    }

    public CustomerResponse updateCustomer(String customerId, CustomerCreationRequest request) {
        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new RuntimeException("Customer not found"));

        if (request.getName() != null) {
            customer.setName(request.getName());
        }
        if (request.getPhone() != null) {
            customer.setPhone(request.getPhone());
        }
        if (request.getEmail() != null) {
            customer.setEmail(request.getEmail());
        }
        if (request.getType() != null) {
            customer.setType(request.getType());
        }
        if (request.getDob() != null) {
            customer.setDob(request.getDob());
        }

        return customerMapper.toCustomerResponse(customerRepository.save(customer));
    }

    public void deleteCustomer(String customerId) {
        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new RuntimeException("Customer not found"));
        customer.setDeleted(true);
        customerRepository.save(customer);
    }

    public List<CustomerResponse> getAllCustomers() {
        return customerRepository.findAllByDeletedFalse().stream().map(customerMapper::toCustomerResponse).collect(Collectors.toList());
    }

    public long countAllCustomers() {
        return customerRepository.count();
    }

    public CustomerResponse getCustomerById(String id) {
        return customerRepository.findById(id).map(customerMapper::toCustomerResponse).orElseThrow(() -> new RuntimeException("Customer not found"));
    }
    public Customer getCustomerObjectById(String id) {
        return customerRepository.findById(id).orElseThrow(() -> new RuntimeException("Customer not found"));
    }
    public List<OptionResponse> getAllOption() {
        return customerRepository.findAllByDeletedFalse().stream().map(customerMapper::toOptionResponse).collect(Collectors.toList());
    }
}
