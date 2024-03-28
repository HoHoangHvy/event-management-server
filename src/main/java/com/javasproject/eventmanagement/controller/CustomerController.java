package com.javasproject.eventmanagement.controller;

import com.javasproject.eventmanagement.dto.request.ApiResponse;
import com.javasproject.eventmanagement.dto.request.CustomerUpdateRequest;
import com.javasproject.eventmanagement.entity.Customer;
import com.javasproject.eventmanagement.dto.request.CustomerCreationRequest;
import com.javasproject.eventmanagement.service.CustomerService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @PostMapping
    ApiResponse<Customer> createCustomer(@RequestBody CustomerCreationRequest request) {
        ApiResponse<Customer> apiResponse = new ApiResponse<>();

        apiResponse.setData(customerService.createCustomer(request));
        apiResponse.setMessage("Successfully build the customer");
        return apiResponse;
    }
    @GetMapping
    ApiResponse<List<Customer>> getAllCustomer(){
        ApiResponse<List<Customer>> apiResponse = new ApiResponse<>();

        apiResponse.setData(customerService.getAllCustomer());
        apiResponse.setMessage("Successfully get the customer's list");
        return apiResponse;
    }

    @GetMapping("/{customerId}")
    ApiResponse<Customer> getCustomerById(@PathVariable("customerId") String customerId) {
        ApiResponse<Customer> apiResponse = new ApiResponse<>();

        apiResponse.setData(customerService.getCustomerById(customerId));
        apiResponse.setMessage("Successfully get the customer");
        return apiResponse;
    }

    @PutMapping("/{customerId}")
    ApiResponse<Customer> updateCustomer(@PathVariable String customerId, @RequestBody CustomerUpdateRequest request) {
        ApiResponse<Customer> apiResponse = new ApiResponse<>();

        apiResponse.setData(customerService.updateCustomer(customerId, request));
        apiResponse.setMessage("Successfully update the customer");
        return apiResponse;
    }

    @DeleteMapping("/{customerId}")
    ApiResponse<String> deleteCustomer(@PathVariable String customerId) {
        ApiResponse<String> apiResponse = new ApiResponse<>();

        apiResponse.setData("Customer has been deleted");
        customerService.deleteCustomer(customerId);
        return apiResponse;
    }
}
