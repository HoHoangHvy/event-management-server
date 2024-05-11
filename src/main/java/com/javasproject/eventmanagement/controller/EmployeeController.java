package com.javasproject.eventmanagement.controller;


import com.javasproject.eventmanagement.dto.request.ApiResponse;
import com.javasproject.eventmanagement.dto.request.EmployeeCreationRequest;
import com.javasproject.eventmanagement.dto.request.EmployeeUpdateRequest;
import com.javasproject.eventmanagement.dto.response.EmployeeResponse;
import com.javasproject.eventmanagement.dto.response.ListResponse;
import com.javasproject.eventmanagement.entity.Employee;
import com.javasproject.eventmanagement.service.EmployeeService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/employees")
    public ApiResponse<ListResponse> getAllEmployee(){
        ApiResponse<ListResponse> apiResponse = new ApiResponse<>();
        var listResponse = new ListResponse<EmployeeResponse>();
        List<EmployeeResponse> employees = employeeService.getAllEmployee();
        long totalData = employeeService.countAllEmployee();
        listResponse.setListData(employees);
        listResponse.setTotalData(totalData);

        apiResponse.setData(listResponse);
        apiResponse.setMessage("Successfully get the employee's list");
        return apiResponse;
    }
//    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/employees/{employeeId}")
    public ResponseEntity<ApiResponse<EmployeeResponse>> getEmployeeById(@PathVariable("employeeId") String employeeId) {
        ApiResponse<EmployeeResponse> apiResponse = new ApiResponse<>();

        apiResponse.setData(employeeService.getEmployeeById(employeeId));
        apiResponse.setMessage("Successfully get the employee");
        return ResponseEntity.ok(apiResponse);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping("/employees/{id}")
    public ApiResponse<EmployeeResponse> updateEmployee(@PathVariable String id, @RequestBody EmployeeUpdateRequest request) {
        ApiResponse<EmployeeResponse> apiResponse = new ApiResponse<>();

        apiResponse.setData(employeeService.updateEmployee(id, request));
        apiResponse.setMessage("Successfully update the employee");
        return apiResponse;
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping("/employees/{employeeId}")
    public ApiResponse<String> deleteEmployee(@PathVariable String employeeId) {
        ApiResponse<String> apiResponse = new ApiResponse<>();

        apiResponse.setData("Employee has been deleted");
        employeeService.deleteEmployee(employeeId);
        return apiResponse;
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/related/employees/{employeeId}")
    public ApiResponse<Map<String, Object>> getRelated(@PathVariable("employeeId") String employeeId){
        ApiResponse<Map<String, Object>> apiResponse = new ApiResponse<>();
        Map<String, Object> listResponse = employeeService.getRelated(employeeId);
        apiResponse.setData(listResponse);
        apiResponse.setMessage("Successfully get the employee's list");
        return apiResponse;
    }
}
