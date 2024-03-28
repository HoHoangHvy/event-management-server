package com.javasproject.eventmanagement.service;

import com.javasproject.eventmanagement.dto.request.CustomerCreationRequest;
import com.javasproject.eventmanagement.dto.request.CustomerUpdateRequest;
import com.javasproject.eventmanagement.entity.Customer;
import com.javasproject.eventmanagement.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    public Customer createCustomer(CustomerCreationRequest request) {
        Customer customer = new Customer();

        customer.setName(request.getName());
        customer.setPhone(request.getPhone());
        customer.setEmail(request.getEmail());
        customer.setType(request.getType());
        customer.setDob(request.getDob());

        return customerRepository.save(customer);
    }

    public Customer updateCustomer(String customerId, CustomerUpdateRequest request) {
        Customer customer = new Customer();

        customer.setPhone(request.getPhone());
        customer.setEmail(request.getEmail());
        customer.setType(request.getType());
        customer.setDob(request.getDob());

        return customerRepository.save(customer);
    }

    public void deleteCustomer(String customerId) {
        customerRepository.deleteById(customerId);
    }

    public List<Customer> getAllCustomer() {
        return customerRepository.findAll();
    }

    public Customer getCustomerById(String id) {
        return customerRepository.findById(id).orElseThrow(() -> new RuntimeException("Customer not found"));
    }
}
