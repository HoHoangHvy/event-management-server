package com.javasproject.eventmanagement.controller;


import com.javasproject.eventmanagement.dto.request.ApiResponse;
import com.javasproject.eventmanagement.dto.request.EmployeeCreationRequest;
import com.javasproject.eventmanagement.dto.request.EmployeeUpdateRequest;
import com.javasproject.eventmanagement.entity.Employee;
import com.javasproject.eventmanagement.service.EmployeeService;
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
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    ApiResponse<Employee> createEmployee(@RequestBody EmployeeCreationRequest request){
        ApiResponse<Employee> apiResponse = new ApiResponse<>();

        apiResponse.setData(employeeService.createEmployee(request));
        apiResponse.setMessage("Successfully build the employee.");
        return apiResponse;
    }

    @GetMapping
    ApiResponse<List<Employee>> getAllEmployee(){
        ApiResponse<List<Employee>> apiResponse = new ApiResponse<>();

        apiResponse.setData(employeeService.getAllEmployee());
        apiResponse.setMessage("Successfully get the employee's list");
        return apiResponse;
    }

    @GetMapping("/{employeeId}")
    ApiResponse<Employee> getEmployeeById(@PathVariable("employeeId") String employeeId) {
        ApiResponse<Employee> apiResponse = new ApiResponse<>();

        apiResponse.setData(employeeService.getEmployeeById(employeeId));
        apiResponse.setMessage("Successfully get the employee");
        return apiResponse;
    }

    @PutMapping("/{employeeId}")
    ApiResponse<Employee> getEmployee(@PathVariable String employeeId, @RequestBody EmployeeUpdateRequest request) {
        ApiResponse<Employee> apiResponse = new ApiResponse<>();

        apiResponse.setData(employeeService.updateEmployee(employeeId, request));
        apiResponse.setMessage("Successfully update the employee");
        return apiResponse;
    }

    @DeleteMapping("/{employeeId}")
    ApiResponse<String> deleteEmployee(@PathVariable String employeeId) {
        ApiResponse<String> apiResponse = new ApiResponse<>();

        apiResponse.setData("Employee has been deleted");
        employeeService.deleteEmployee(employeeId);
        return apiResponse;
    }
}
