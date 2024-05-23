package com.javasproject.eventmanagement.service;

import com.javasproject.eventmanagement.dto.request.DepartmentRequest;
import com.javasproject.eventmanagement.dto.response.DepartmentListResponse;
import com.javasproject.eventmanagement.dto.response.DepartmentResponse;
import com.javasproject.eventmanagement.dto.response.EmployeeResponse;
import com.javasproject.eventmanagement.dto.response.OptionResponse;
import com.javasproject.eventmanagement.entity.Department;
import com.javasproject.eventmanagement.mapper.DepartmentMapper;
import com.javasproject.eventmanagement.mapper.EmployeeMapper;
import com.javasproject.eventmanagement.repository.DepartmentRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DepartmentService {

    DepartmentRepository departmentRepository;
    DepartmentMapper departmentMapper;

    public DepartmentResponse create(DepartmentRequest request){
        Department department = new Department();
        department.setName(request.getName());

        return departmentMapper.toDepartmentResponse(departmentRepository.save(department));
    }

    public List<DepartmentListResponse> getAll() {
        return departmentRepository.findAll().stream().map(departmentMapper::toDepartmentListResponse).collect(Collectors.toList());
    }

    public List<OptionResponse> getAllOption() {
        return departmentRepository.findAll().stream().map(departmentMapper::toOptionResponse).collect(Collectors.toList());
    }

    public DepartmentResponse getById(String id){
        return departmentRepository.findById(id).map(departmentMapper::toDepartmentResponse).orElseThrow(() -> new RuntimeException("Department not found"));
    }
    public Department getObjectById(String id){
        return departmentRepository.findById(id).orElseThrow(() -> new RuntimeException("Department not found"));
    }

    public Boolean deleteById(String id){
        if(departmentRepository.existsById(id)){
            departmentRepository.deleteById(id);
            return true;
        }
        else {
            return false;
        }
    }
    public long countAll(){
        return departmentRepository.count();
    }

    public DepartmentResponse updateDepartment(String id, DepartmentRequest request) {
        Department department = departmentRepository.findById(id).orElseThrow();
        department.setName(request.getName());
        return departmentMapper.toDepartmentResponse(departmentRepository.save(department));
    }
    EmployeeMapper employeeMapper;
    public List<EmployeeResponse> getRelatedEmployees(String id) {
        return departmentRepository.findById(id).orElseGet(Department::new).getEmployees().stream().map(employeeMapper::toEmployeeResponse).collect(Collectors.toList());
    }
}
