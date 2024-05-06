package com.javasproject.eventmanagement.controller;


import com.javasproject.eventmanagement.dto.request.ApiResponse;
import com.javasproject.eventmanagement.dto.request.EmployeeCreationRequest;
import com.javasproject.eventmanagement.dto.request.EmployeeUpdateRequest;
import com.javasproject.eventmanagement.dto.response.EmployeeResponse;
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
//@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/api/")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/employees")
    public ApiResponse<Employee> createEmployee(@RequestBody EmployeeCreationRequest request){
        ApiResponse<Employee> apiResponse = new ApiResponse<>();

        apiResponse.setData(employeeService.createEmployee(request));
        apiResponse.setMessage("Successfully build the employee.");
        return apiResponse;
    }

    @GetMapping("/employees")
    public ApiResponse<List<Employee>> getAllEmployee(){
        ApiResponse<List<Employee>> apiResponse = new ApiResponse<>();

        apiResponse.setData(employeeService.getAllEmployee());
        apiResponse.setMessage("Successfully get the employee's list");
        return apiResponse;
    }

    @GetMapping("/employees/{employeeId}")
    public ApiResponse<Employee> getEmployeeById(@PathVariable("employeeId") String employeeId) {
        ApiResponse<Employee> apiResponse = new ApiResponse<>();

        apiResponse.setData(employeeService.getEmployeeById(employeeId));
        apiResponse.setMessage("Successfully get the employee");
        return apiResponse;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping("/employees/{id}")
    public ApiResponse<EmployeeResponse> updateEmployee(@PathVariable String id, @RequestBody EmployeeUpdateRequest request) {
        ApiResponse<EmployeeResponse> apiResponse = new ApiResponse<>();

        apiResponse.setData(employeeService.updateEmployee(id, request));
        apiResponse.setMessage("Successfully update the employee");
        return apiResponse;
    }

    @DeleteMapping("/{employeeId}")
    public ApiResponse<String> deleteEmployee(@PathVariable String employeeId) {
        ApiResponse<String> apiResponse = new ApiResponse<>();

        apiResponse.setData("Employee has been deleted");
        employeeService.deleteEmployee(employeeId);
        return apiResponse;
    }
}
