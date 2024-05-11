package com.javasproject.eventmanagement.service;

import com.javasproject.eventmanagement.dto.request.EmployeeCreationRequest;
import com.javasproject.eventmanagement.dto.request.EmployeeUpdateRequest;
import com.javasproject.eventmanagement.dto.request.UserCreationRequest;
import com.javasproject.eventmanagement.dto.response.EmployeeResponse;
import com.javasproject.eventmanagement.dto.response.OptionResponse;
import com.javasproject.eventmanagement.entity.Department;
import com.javasproject.eventmanagement.entity.Employee;
import com.javasproject.eventmanagement.mapper.EmployeeMapper;
import com.javasproject.eventmanagement.repository.EmployeeRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Service
public class EmployeeService {
    EmployeeRepository employeeRepository;
    UserService userService;
    String defaultPassword = "123123123";
    EmployeeMapper employeeMapper;
    private final DepartmentService departmentService;
    private final RoleService roleService;

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
        if (request.getRoleId() != null && !request.getRoleId().isEmpty()) {
            userService.updateUserRole(employee.getId(), request.getRoleId());
        }
        if(request.getDepartmentId() != null && !request.getDepartmentId().isEmpty()){
            Department department = departmentService.getById(request.getDepartmentId()).orElse(null);
            employee.setDepartment(department);
        }

        return employeeMapper.toEmployeeResponse(employeeRepository.save(employee));
    }

    public void deleteEmployee(String employeeId) {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(() -> new RuntimeException("Employee not found"));
        employee.setDeleted(true);
        employeeRepository.save(employee);
    }

    public List<EmployeeResponse> getAllEmployee() {
        return employeeRepository.findAllActive().stream().map(employeeMapper::toEmployeeResponse).collect(Collectors.toList());
    }
    public long countAllEmployee() {
        return employeeRepository.count();
    }

    public EmployeeResponse getEmployeeById(String id) {
        return employeeRepository.findById(id).map(employeeMapper::toEmployeeResponse).orElseThrow(() -> new RuntimeException("Employee not found"));
    }
    public Map<String, Object> getRelated(String employeeId) {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(() -> new RuntimeException("Employee not found"));
        List<OptionResponse> departmentList = departmentService.getAllOption();
        List<OptionResponse> roleList = roleService.getAllOption();
        return Map.of("departmentId", departmentList, "roleId", roleList);
    }
}
