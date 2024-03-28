package com.javasproject.eventmanagement.service;

import com.javasproject.eventmanagement.dto.request.EmployeeCreationRequest;
import com.javasproject.eventmanagement.dto.request.EmployeeUpdateRequest;
import com.javasproject.eventmanagement.entity.Employee;
import com.javasproject.eventmanagement.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public Employee createEmployee(EmployeeCreationRequest request) {
        Employee employee = new Employee();

        employee.setName(request.getName());
        employee.setPhone(request.getPhone());
        employee.setLevel(request.getLevel());
        employee.setGender(request.getGender());
        employee.setStatus(request.getStatus());
        employee.setStartDate(request.getStartDate());
        employee.setDoB(request.getDoB());

        return employeeRepository.save(employee);
    }

    public Employee updateEmployee(String EmployeeId, EmployeeUpdateRequest request) {
        Employee employee = new Employee();

        employee.setPhone(request.getPhone());
        employee.setLevel(request.getLevel());
        employee.setGender(request.getGender());
        employee.setStatus(request.getStatus());
        employee.setStartDate(request.getStartDate());
        employee.setDoB(request.getDoB());

        return employeeRepository.save(employee);
    }

    public void deleteEmployee(String employeeId) {
        employeeRepository.deleteById(employeeId);
    }

    public List<Employee> getAllEmployee() {
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(String id) {
        return employeeRepository.findById(id).orElseThrow(() -> new RuntimeException("Employee not found"));
    }
}
