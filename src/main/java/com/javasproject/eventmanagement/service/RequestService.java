package com.javasproject.eventmanagement.service;

import com.javasproject.eventmanagement.dto.request.NotificationCreationRequest;
import com.javasproject.eventmanagement.dto.request.RequestCreationRequest;
import com.javasproject.eventmanagement.dto.request.RequestCreationRequest;
import com.javasproject.eventmanagement.dto.response.RequestResponse;
import com.javasproject.eventmanagement.entity.Employee;
import com.javasproject.eventmanagement.entity.Request;
import com.javasproject.eventmanagement.exception.AppException;
import com.javasproject.eventmanagement.exception.ErrorCode;
import com.javasproject.eventmanagement.mapper.RequestMapper;
import com.javasproject.eventmanagement.repository.RequestRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Service
public class RequestService {
    RequestRepository requestRepository;
    RequestMapper requestMapper;
    NotificationService notiService;
    UserService userService;
    EmployeeService employeeService;
    public RequestResponse createRequest(RequestCreationRequest request) {
        Request requestEntity = new Request();
        requestEntity.setName(request.getName());
        requestEntity.setType(request.getType());
        requestEntity.setContent(request.getContent());
        requestEntity.setStatus(request.getStatus());
        requestEntity.setCreatedBy(this.userService.getCurrentUser().getEmployee());
        Request savedRequest = requestRepository.save(requestEntity);

        employeeService.getEmployeeManager(this.userService.getCurrentUser().getEmployee()).forEach(employee -> {
            if(employee.getStatus().equals("Working") && userService.getCurrentUser().getEmployee().getId() != employee.getId()) {
                NotificationCreationRequest notiRequest = new NotificationCreationRequest();
                notiRequest.setName("You have new " + request.getType() + " request from "+ employee.getName());
                notiRequest.setContent(savedRequest.getContent());
                notiRequest.setParentId(savedRequest.getId());
                notiRequest.setType("Notice");
                notiRequest.setParentType("Request");
                notiRequest.setEmployeeId(employee.getId());
                notiService.createNotification(notiRequest);
            }
        });

        return requestMapper.toRequestResponse(savedRequest);
    }

    public RequestResponse updateRequest(String requestId, RequestCreationRequest request) {
        Request requestEntity = requestRepository.findById(requestId).orElseThrow(() -> new AppException(ErrorCode.REQUEST_NOT_FOUND));

        if (request.getName() != null && !request.getName().isEmpty()) {
            requestEntity.setName(request.getName());
        }
        if (request.getType() != null && !request.getType().isEmpty()) {
            requestEntity.setType(request.getType());
        }
        if (request.getContent() != null && !request.getContent().isEmpty()) {
            requestEntity.setContent(request.getContent());
        }
        if (request.getStatus() != null && !request.getStatus().isEmpty()) {
            requestEntity.setStatus(request.getStatus());
        }
        return requestMapper.toRequestResponse(requestRepository.save(requestEntity));
    }

    public void deleteRequest(String requestId) {
        Request request = requestRepository.findById(requestId).orElseThrow(() -> new AppException(ErrorCode.REQUEST_NOT_FOUND));
        request.setDeleted(true);
        requestRepository.save(request);
    }

    public List<RequestResponse> getAllRequests() {
        Employee currentEmp = this.userService.getCurrentUser().getEmployee();
        var empLevel = currentEmp.getEmpLevel();
        if("Manager".equals(empLevel)) {
            return requestRepository.findAllActiveByDepartmentId(currentEmp.getDepartment()).stream().map(requestMapper::toRequestResponse).collect(Collectors.toList());
        } else if("Staff".equals(empLevel)) {
            return requestRepository.findAllActiveByUserId(currentEmp).stream().map(requestMapper::toRequestResponse).collect(Collectors.toList());
        }
        return requestRepository.findAllByDeleted(false).stream().map(requestMapper::toRequestResponse).collect(Collectors.toList());
    }

    public RequestResponse getRequestById(String id) {
        return requestRepository.findById(id).map(requestMapper::toRequestResponse).orElseThrow(() -> new AppException(ErrorCode.REQUEST_NOT_FOUND));
    }
}
