package com.javasproject.eventmanagement.service;

import com.javasproject.eventmanagement.dto.request.EmployeeCreationRequest;
import com.javasproject.eventmanagement.dto.request.EmployeeUpdateRequest;
import com.javasproject.eventmanagement.dto.request.UserCreationRequest;
import com.javasproject.eventmanagement.dto.response.EmployeeResponse;
import com.javasproject.eventmanagement.entity.Employee;
import com.javasproject.eventmanagement.mapper.EmployeeMapper;
import com.javasproject.eventmanagement.repository.EmployeeRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Service
public class EmployeeService {
    EmployeeRepository employeeRepository;
    UserService userService;
    String defaultPassword = "123123123";
    EmployeeMapper employeeMapper;
    public Employee createEmployee(EmployeeCreationRequest request) {
        Employee employee = new Employee();

        employee.setName(request.getName());
        employee.setPhone(request.getPhone());
        employee.setEmpLevel(request.getEmpLevel());
        employee.setGender(request.getGender());
        employee.setStatus(request.getStatus());
        employee.setStartDate(request.getStartDate());
        employee.setDob(request.getDoB());
        employee.setEmail(request.getEmail());

        Employee savedEmployee = employeeRepository.save(employee);
        UserCreationRequest userCreationRequest = new UserCreationRequest();
        userCreationRequest.setStatus("Active");
        userCreationRequest.setUserName(request.getEmail());
        userCreationRequest.setPassword(this.defaultPassword);
        userCreationRequest.setRoleId(request.getRoleId());
        userCreationRequest.setEmployee(savedEmployee);

        userService.createUser(userCreationRequest);
        return savedEmployee;
    }

    public EmployeeResponse updateEmployee(String EmployeeId, EmployeeUpdateRequest request) {
        Employee employee = employeeRepository.findById(EmployeeId).orElseThrow(() -> new RuntimeException("Employee not found"));

        if (request.getPhone() != null && !request.getPhone().isEmpty()) {
            employee.setPhone(request.getPhone());
        }
        if (request.getEmpLevel() != null) {
            employee.setEmpLevel(request.getEmpLevel());
        }
        if (request.getGender() != null) {
            employee.setGender(request.getGender());
        }
        if (request.getStatus() != null) {
            employee.setStatus(request.getStatus());
        }
        if (request.getStartDate() != null) {
            employee.setStartDate(request.getStartDate());
        }
        if (request.getDob() != null) {
            employee.setDob(request.getDob().plusDays(1));
        }
        if (request.getEmail() != null && !request.getPhone().isEmpty()) {
            employee.setEmail(request.getEmail());
        }


        return employeeMapper.toEmployeeResponse(employeeRepository.save(employee));
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
