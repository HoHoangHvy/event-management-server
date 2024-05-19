package com.javasproject.eventmanagement.controller;

import com.javasproject.eventmanagement.dto.request.ApiResponse;
import com.javasproject.eventmanagement.dto.request.DepartmentRequest;
import com.javasproject.eventmanagement.dto.request.EmployeeUpdateRequest;
import com.javasproject.eventmanagement.dto.response.DepartmentListResponse;
import com.javasproject.eventmanagement.dto.response.DepartmentResponse;
import com.javasproject.eventmanagement.dto.response.EmployeeResponse;
import com.javasproject.eventmanagement.dto.response.ListResponse;
import com.javasproject.eventmanagement.mapper.DepartmentMapper;
import com.javasproject.eventmanagement.service.DepartmentService;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/api")
public class DepartmentController {
    DepartmentService departmentService;
    private final DepartmentMapper departmentMapper;
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/departments")
    public ApiResponse<DepartmentResponse> createDepartment(@RequestBody DepartmentRequest request){
        ApiResponse<DepartmentResponse> response = new ApiResponse<>();
        response.setData(departmentService.create(request));
        return response;
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/departments")
    public ApiResponse<ListResponse> getAllDepartments(){
        ApiResponse<ListResponse> apiResponse = new ApiResponse<>();
        var listResponse = new ListResponse<DepartmentListResponse>();
        List<DepartmentListResponse> departments = departmentService.getAll();
        long totalData = departmentService.countAll();
        listResponse.setListData(departments);
        listResponse.setTotalData(totalData);

        apiResponse.setData(listResponse);
        apiResponse.setMessage("Successfully get the employee's list");
        return apiResponse;
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("departments/{id}")
    public ApiResponse<DepartmentResponse> getDepartmentById(@PathVariable String id){
        ApiResponse<DepartmentResponse> response = new ApiResponse<>();
        response.setData(departmentService.getById(id).map(departmentMapper::toDepartmentResponse).orElse(null));
        return response;
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping("departments/{id}")
    public ApiResponse<Boolean> deleteDepartmentById(@PathVariable String id){
        ApiResponse<Boolean> response = new ApiResponse<>();
        response.setData(departmentService.deleteById(id));
        return response;
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping("departments/{id}")
    public ApiResponse<DepartmentResponse> updateDepartment(@PathVariable String id, @RequestBody DepartmentRequest request) {
        ApiResponse<DepartmentResponse> apiResponse = new ApiResponse<>();

        apiResponse.setData(departmentService.updateDepartment(id, request));
        apiResponse.setMessage("Successfully update the employee");
        return apiResponse;
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/related/departments")
    public ApiResponse<Map<String, Object>> getRelated(){
        ApiResponse<Map<String, Object>> apiResponse = new ApiResponse<>();
        apiResponse.setMessage("Successfully get the employee's list");
        return apiResponse;
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/departments/related-employees/{departmentId}")
    public ApiResponse<List<EmployeeResponse>> getRelatedEmployees(@PathVariable("departmentId") String departmentId){
        ApiResponse<List<EmployeeResponse>> apiResponse = new ApiResponse<>();
        List empList = new ArrayList(departmentService.getRelatedEmployees(departmentId));
        apiResponse.setMessage("Successfully get the employee's list");
        apiResponse.setData(empList);
        return apiResponse;
    }

}
