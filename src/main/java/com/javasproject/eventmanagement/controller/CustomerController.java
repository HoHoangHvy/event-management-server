package com.javasproject.eventmanagement.controller;

import com.javasproject.eventmanagement.dto.request.ApiResponse;
import com.javasproject.eventmanagement.dto.request.CustomerCreationRequest;
import com.javasproject.eventmanagement.dto.response.CustomerResponse;
import com.javasproject.eventmanagement.dto.response.ListResponse;
import com.javasproject.eventmanagement.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/customers")
    public ApiResponse<CustomerResponse> createCustomer(@RequestBody CustomerCreationRequest request) {
        ApiResponse<CustomerResponse> apiResponse = new ApiResponse<>();
        apiResponse.setData(customerService.createCustomer(request));
        apiResponse.setMessage("Successfully created the customer.");
        return apiResponse;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/customers")
    public ApiResponse<ListResponse> getAllCustomers() {
        ApiResponse<ListResponse> apiResponse = new ApiResponse<>();
        var listResponse = new ListResponse<CustomerResponse>();
        List<CustomerResponse> customers = customerService.getAllCustomers();
        long totalData = customerService.countAllCustomers();
        listResponse.setListData(customers);
        listResponse.setTotalData(totalData);

        apiResponse.setData(listResponse);
        apiResponse.setMessage("Successfully retrieved the customer list.");
        return apiResponse;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/customers/{customerId}")
    public ResponseEntity<ApiResponse<CustomerResponse>> getCustomerById(@PathVariable("customerId") String customerId) {
        ApiResponse<CustomerResponse> apiResponse = new ApiResponse<>();
        apiResponse.setData(customerService.getCustomerById(customerId));
        apiResponse.setMessage("Successfully retrieved the customer.");
        return ResponseEntity.ok(apiResponse);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping("/customers/{id}")
    public ApiResponse<CustomerResponse> updateCustomer(@PathVariable String id, @RequestBody CustomerCreationRequest request) {
        ApiResponse<CustomerResponse> apiResponse = new ApiResponse<>();
        apiResponse.setData(customerService.updateCustomer(id, request));
        apiResponse.setMessage("Successfully updated the customer.");
        return apiResponse;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping("/customers/{customerId}")
    public ApiResponse<String> deleteCustomer(@PathVariable String customerId) {
        ApiResponse<String> apiResponse = new ApiResponse<>();
        apiResponse.setData("Customer has been deleted");
        customerService.deleteCustomer(customerId);
        return apiResponse;
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/related/customers")
    public ApiResponse<Map<String, Object>> getRelated(){
        ApiResponse<Map<String, Object>> apiResponse = new ApiResponse<>();
        Map<String, Object> listResponse = new HashMap<>();
        apiResponse.setData(listResponse);
        apiResponse.setMessage("Successfully get the employee's list");
        return apiResponse;
    }
}
