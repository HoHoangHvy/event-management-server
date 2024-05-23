package com.javasproject.eventmanagement.service;

import com.javasproject.eventmanagement.dto.request.*;
import com.javasproject.eventmanagement.dto.request.RequestCreationRequest;
import com.javasproject.eventmanagement.dto.response.RequestResponse;
import com.javasproject.eventmanagement.entity.Employee;
import com.javasproject.eventmanagement.entity.Request;
import com.javasproject.eventmanagement.entity.RequestDepartment;
import com.javasproject.eventmanagement.exception.AppException;
import com.javasproject.eventmanagement.exception.ErrorCode;
import com.javasproject.eventmanagement.mapper.RequestDepartmentMapper;
import com.javasproject.eventmanagement.mapper.RequestMapper;
import com.javasproject.eventmanagement.repository.RequestDepartmentRepository;
import com.javasproject.eventmanagement.repository.RequestRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
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
        this.createForwardRequest(savedRequest);
        employeeService.getEmployeeManager(this.userService.getCurrentUser().getEmployee()).forEach(employee -> {
            if(employee.getStatus().equals("Working") && userService.getCurrentUser().getEmployee().getId() != employee.getId()) {
                NotificationCreationRequest notiRequest = new NotificationCreationRequest();
                notiRequest.setName("You have new " + request.getType() + " request from "+ this.userService.getCurrentUser().getEmployee().getName());
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
    RequestDepartmentRepository requestDepartmentRepository;
    public Boolean createForwardRequest(Request requestEntity) {
        RequestDepartment requestDepartmentObject = new RequestDepartment();
        requestDepartmentObject.setDepartment(requestEntity.getCreatedBy().getDepartment());
        requestDepartmentObject.setNote("Check and handle this request");
        requestDepartmentObject.setRequest(requestEntity);
        requestDepartmentRepository.save(requestDepartmentObject);
        return true;
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
            if("Approved".equals(request.getStatus())) {
                requestEntity.setApprovedBy(this.userService.getCurrentUser().getEmployee());
                requestEntity.setApproveDate(LocalDateTime.now());
                this.notiService.createNotification(NotificationCreationRequest.builder()
                        .name("Your " +requestEntity.getType()+ " request has been approved")
                        .content("Your request has been approved by " + this.userService.getCurrentUser().getEmployee().getName())
                        .type("Notice")
                        .parentType("Request")
                        .parentId(requestId)
                        .employeeId(requestEntity.getCreatedBy().getId())
                        .build());
            }
        }
        if (request.getRejectReason() != null && "Rejected".equals(request.getStatus())) {
            requestEntity.setRejectReason(request.getRejectReason());
            this.notiService.createNotification(NotificationCreationRequest.builder()
                    .name("Your " +requestEntity.getType()+ " request has been rejected")
                    .content("Your request has been rejected by " + this.userService.getCurrentUser().getEmployee().getName() + " with reason: " + request.getRejectReason())
                    .type("Notice")
                    .parentType("Request")
                    .parentId(requestId)
                    .employeeId(requestEntity.getCreatedBy().getId())
                    .build());
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
            return currentEmp.getDepartment().getRequestDepartments().stream()
                    .map(RequestDepartment::getRequest)  // Assuming getRequest() returns a single Request
                    .map(requestMapper::toRequestResponse)
                    .sorted(Comparator.comparing(RequestResponse::getDateEntered).reversed())
                    .collect(Collectors.toList());
        } else if("Staff".equals(empLevel)) {
            return requestRepository.findAllActiveByUserId(currentEmp).stream().map(requestMapper::toRequestResponse).collect(Collectors.toList());
        }
        return requestRepository.findAllByDeleted(false).stream().map(requestMapper::toRequestResponse).collect(Collectors.toList());
    }
    RequestDepartmentMapper requestDepartmentMapper;
    public RequestResponse getRequestById(String id) {
        RequestResponse requestResponse = requestMapper.toRequestResponse(requestRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.REQUEST_NOT_FOUND)));
        Request request = requestRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.REQUEST_NOT_FOUND));
        requestResponse.setRequestDepartments(request.getRequestDepartments().stream().map(requestDepartmentMapper::toRequestDepartmentListResponse).collect(Collectors.toList()));
        return requestResponse;
    }
    public Request getRequestObjectById (String id) {
        return requestRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.REQUEST_NOT_FOUND));
    }
}
