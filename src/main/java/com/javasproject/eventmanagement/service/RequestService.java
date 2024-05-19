package com.javasproject.eventmanagement.service;

import com.javasproject.eventmanagement.dto.request.RequestCreationRequest;
import com.javasproject.eventmanagement.dto.request.RequestCreationRequest;
import com.javasproject.eventmanagement.dto.response.RequestResponse;
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

    public RequestResponse createRequest(RequestCreationRequest request) {
        Request requestEntity = new Request();
        requestEntity.setName(request.getName());
        requestEntity.setType(request.getType());
        requestEntity.setContent(request.getContent());
        requestEntity.setStatus(request.getStatus());
        requestEntity.setApproveDate(request.getApproveDate());
        requestEntity.setRejectReason(request.getRejectReason());
        requestEntity.setDeleted(false);

        Request savedRequest = requestRepository.save(requestEntity);
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
        if (request.getApproveDate() != null) {
            requestEntity.setApproveDate(request.getApproveDate());
        }
        if (request.getRejectReason() != null && !request.getRejectReason().isEmpty()) {
            requestEntity.setRejectReason(request.getRejectReason());
        }
        if (request.getDeleted() != null) {
            requestEntity.setDeleted(request.getDeleted());
        }

        return requestMapper.toRequestResponse(requestRepository.save(requestEntity));
    }

    public void deleteRequest(String requestId) {
        Request request = requestRepository.findById(requestId).orElseThrow(() -> new AppException(ErrorCode.REQUEST_NOT_FOUND));
        request.setDeleted(true);
        requestRepository.save(request);
    }

    public List<RequestResponse> getAllRequests() {
        return requestRepository.findAll().stream().map(requestMapper::toRequestResponse).collect(Collectors.toList());
    }

    public RequestResponse getRequestById(String id) {
        return requestRepository.findById(id).map(requestMapper::toRequestResponse).orElseThrow(() -> new AppException(ErrorCode.REQUEST_NOT_FOUND));
    }
}
