package com.javasproject.eventmanagement.controller;

import com.javasproject.eventmanagement.dto.request.ApiResponse;
import com.javasproject.eventmanagement.dto.request.CustomerUpdateRequest;
import com.javasproject.eventmanagement.dto.response.CustomerResponse;
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
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping
    ApiResponse<CustomerResponse> createCustomer(@RequestBody CustomerCreationRequest request) {
        ApiResponse<CustomerResponse> apiResponse = new ApiResponse<>();

        apiResponse.setData(customerService.createCustomer(request));
        apiResponse.setMessage("Successfully build the customer");
        return apiResponse;
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping
    ApiResponse<List<CustomerResponse>> getAllCustomer(){
        ApiResponse<List<CustomerResponse>> apiResponse = new ApiResponse<>();

        apiResponse.setData(customerService.getAllCustomer());
        apiResponse.setMessage("Successfully get the customer's list");
        return apiResponse;
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/{customerId}")
    ApiResponse<CustomerResponse> getCustomerById(@PathVariable("customerId") String customerId) {
        ApiResponse<CustomerResponse> apiResponse = new ApiResponse<>();

        apiResponse.setData(customerService.getCustomerById(customerId));
        apiResponse.setMessage("Successfully get the customer");
        return apiResponse;
    }

    @PutMapping("/{customerId}")
    ApiResponse<CustomerResponse> updateCustomer(@PathVariable String customerId, @RequestBody CustomerUpdateRequest request) {
        ApiResponse<CustomerResponse> apiResponse = new ApiResponse<>();

        apiResponse.setData(customerService.updateCustomer(customerId, request));
        apiResponse.setMessage("Successfully update the customer");
        return apiResponse;
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping("/{customerId}")
    ApiResponse<String> deleteCustomer(@PathVariable String customerId) {
        ApiResponse<String> apiResponse = new ApiResponse<>();

        apiResponse.setData("Customer has been deleted");
        customerService.deleteCustomer(customerId);
        return apiResponse;
    }
}
