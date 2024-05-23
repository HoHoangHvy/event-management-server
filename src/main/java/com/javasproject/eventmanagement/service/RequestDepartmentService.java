package com.javasproject.eventmanagement.service;

import com.javasproject.eventmanagement.dto.request.NotificationCreationRequest;
import com.javasproject.eventmanagement.dto.request.RequestCreationRequest;
import com.javasproject.eventmanagement.dto.request.RequestDepartmentCreationRequest;
import com.javasproject.eventmanagement.dto.request.RequestDepartmentRequest;
import com.javasproject.eventmanagement.dto.response.OptionResponse;
import com.javasproject.eventmanagement.dto.response.RequestDepartmentListResponse;
import com.javasproject.eventmanagement.dto.response.RequestDepartmentResponse;
import com.javasproject.eventmanagement.dto.response.RequestResponse;
import com.javasproject.eventmanagement.entity.Employee;
import com.javasproject.eventmanagement.entity.RequestDepartment;
import com.javasproject.eventmanagement.mapper.RequestDepartmentMapper;
import com.javasproject.eventmanagement.repository.RequestDepartmentRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RequestDepartmentService {
    RequestDepartmentRepository requestDepartmentRepository;
    RequestDepartmentMapper rDMapper;
    DepartmentService departmentService;
    RequestService requestService;
    public RequestDepartmentResponse createRequest(RequestDepartmentCreationRequest request) {
        RequestDepartment requestDepartment = new RequestDepartment();
        requestDepartment.setDepartment(this.departmentService.getObjectById(request.getDepartmentId()));
        requestDepartment.setNote(request.getNote());
        requestDepartment.setRequest(this.requestService.getRequestObjectById(request.getRequestId()));
        return rDMapper.toRequestDepartmentResponse(requestDepartmentRepository.save(requestDepartment));
    };
    EmployeeService employeeService;
    public Boolean createForwardRequest(RequestDepartmentRequest request) {
        RequestDepartment requestDepartment = new RequestDepartment();
        requestDepartment.setDepartment(request.getDepartment());
        requestDepartment.setNote(request.getNote());
        requestDepartment.setResolveStatus("Pending");
        requestDepartment.setRequest(request.getRequest());

        employeeService.getEmployeeManagerByDepartment(request.getDepartment()).forEach(employee -> {
            if(employee.getStatus().equals("Working")) {
                NotificationCreationRequest notiRequest = new NotificationCreationRequest();
                notiRequest.setName("You have new forward " + request.getRequest().getType() + " request from "+ this.userService.getCurrentUser().getEmployee().getName());
                notiRequest.setContent(request.getRequest().getContent());
                notiRequest.setParentId(request.getRequest().getId());
                notiRequest.setType("Notice");
                notiRequest.setParentType("Request");
                notiRequest.setEmployeeId(employee.getId());
                notificationService.createNotification(notiRequest);
            }
        });
        requestDepartmentRepository.save(requestDepartment);
        return true;
    }
    public List<RequestDepartmentResponse> getALlRequestDepartment() {
        return requestDepartmentRepository.findAll().stream().map(rDMapper::toRequestDepartmentResponse).collect(Collectors.toList());
    }
    private NotificationService notificationService;
    private UserService userService;
    public RequestDepartmentResponse updateRequest(String id,RequestDepartmentCreationRequest request) {
        RequestDepartment requestDepartmentObject = this.getRequestDepartmentObjectById(id);
        if(request.getDepartmentId() != null && !"Forwarding".equals(request.getStatus())) {
            requestDepartmentObject.setDepartment(this.departmentService.getObjectById(request.getDepartmentId()));
        }
        if(request.getNote() != null && !"Forwarding".equals(request.getStatus())) requestDepartmentObject.setNote(request.getNote());
        if(request.getResponse() != null ) requestDepartmentObject.setResponse(request.getResponse());
        if(request.getStatus() != null) {
            requestDepartmentObject.setResolveStatus(request.getStatus());
            requestDepartmentObject.setResolvedBy(this.userService.getCurrentUser().getEmployee());
            requestDepartmentObject.setResolvedDate(java.time.LocalDateTime.now());
            if("Approved".equals(request.getStatus())) {
                RequestCreationRequest requestCreationRequest = new RequestCreationRequest();
                requestCreationRequest.setStatus("Approved");
                this.requestService.updateRequest(requestDepartmentObject.getRequest().getId(), requestCreationRequest);
            } else if("Rejected".equals(request.getStatus())) {
                RequestCreationRequest requestCreationRequest = new RequestCreationRequest();
                requestCreationRequest.setStatus("Rejected");
                requestCreationRequest.setRejectReason(request.getResponse());
                this.requestService.updateRequest(requestDepartmentObject.getRequest().getId(), requestCreationRequest);
            } else if("Forwarding".equals(request.getStatus())) {
                RequestDepartmentRequest requestDepartmentRequest = new RequestDepartmentRequest();
                requestDepartmentRequest.setDepartment(this.departmentService.getObjectById(request.getDepartmentId()));
                requestDepartmentRequest.setNote(request.getNote());
                requestDepartmentRequest.setRequest(requestDepartmentObject.getRequest());
                this.createForwardRequest(requestDepartmentRequest);
            }
        }
        requestDepartmentRepository.save(requestDepartmentObject);
        return rDMapper.toRequestDepartmentResponse(requestDepartmentObject);
    }
    public RequestDepartment getRequestDepartmentObjectById(String id) {
        return requestDepartmentRepository.findById(id).orElse(null);
    }
    public RequestDepartmentResponse getRequestDepartmentById(String id) {
        return rDMapper.toRequestDepartmentResponse(requestDepartmentRepository.findById(id).orElse(null));
    }

    public Boolean deleteRequestDepartment(String id) {
        if(requestDepartmentRepository.existsById(id)) {
            requestDepartmentRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<RequestDepartmentListResponse> getAllByRequest(String requestId){
        return requestDepartmentRepository.findRequestDepartmentsByRequest(this.requestService.getRequestObjectById(requestId)).stream().map(rDMapper::toRequestDepartmentListResponse).collect(Collectors.toList());
    }

    public Map<String, Object> getRelated() {
        List<OptionResponse> departmentList = departmentService.getAllOption();
        return Map.of("departmentId", departmentList);
    }

}
