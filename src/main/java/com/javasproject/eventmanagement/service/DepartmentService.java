package com.javasproject.eventmanagement.service;

import com.javasproject.eventmanagement.dto.request.DepartmentRequest;
import com.javasproject.eventmanagement.dto.response.DepartmentResponse;
import com.javasproject.eventmanagement.dto.response.OptionResponse;
import com.javasproject.eventmanagement.entity.Department;
import com.javasproject.eventmanagement.mapper.DepartmentMapper;
import com.javasproject.eventmanagement.repository.DepartmentRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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

    public List<DepartmentResponse> getAll() {
        return departmentRepository.findAll().stream().map(departmentMapper::toDepartmentResponse).collect(Collectors.toList());
    }

    public List<OptionResponse> getAllOption() {
        return departmentRepository.findAll().stream().map(departmentMapper::toOptionResponse).collect(Collectors.toList());
    }

    public Optional<Department> getById(String id){
        return departmentRepository.findById(id);
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
}
