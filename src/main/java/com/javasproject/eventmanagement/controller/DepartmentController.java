package com.javasproject.eventmanagement.controller;

import com.javasproject.eventmanagement.dto.request.ApiResponse;
import com.javasproject.eventmanagement.dto.request.DepartmentRequest;
import com.javasproject.eventmanagement.dto.response.DepartmentResponse;
import com.javasproject.eventmanagement.mapper.DepartmentMapper;
import com.javasproject.eventmanagement.service.DepartmentService;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/api/departments")
public class DepartmentController {
    DepartmentService departmentService;
    private final DepartmentMapper departmentMapper;

    @PostMapping
    public ApiResponse<DepartmentResponse> createDepartment(@RequestBody DepartmentRequest request){
        ApiResponse<DepartmentResponse> response = new ApiResponse<>();
        response.setData(departmentService.create(request));
        return response;
    }

    @GetMapping
    public ApiResponse<List<DepartmentResponse>> getAllDepartments(){
        ApiResponse<List<DepartmentResponse>> response = new ApiResponse<>();
        response.setData(departmentService.getAll());
        return response;
    }

    @GetMapping("/{id}")
    public ApiResponse<DepartmentResponse> getDepartmentById(@PathVariable String id){
        ApiResponse<DepartmentResponse> response = new ApiResponse<>();
        response.setData(departmentService.getById(id).map(departmentMapper::toDepartmentResponse).orElse(null));
        return response;
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Boolean> deleteDepartmentById(@PathVariable String id){
        ApiResponse<Boolean> response = new ApiResponse<>();
        response.setData(departmentService.deleteById(id));
        return response;
    }

}
