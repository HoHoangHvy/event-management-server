package com.javasproject.eventmanagement.service;

import com.javasproject.eventmanagement.dto.request.ResourceCreationRequest;
import com.javasproject.eventmanagement.dto.request.ResourceCreationRequest;
import com.javasproject.eventmanagement.dto.response.ResourceResponse;
import com.javasproject.eventmanagement.entity.Resource;
import com.javasproject.eventmanagement.exception.AppException;
import com.javasproject.eventmanagement.exception.ErrorCode;
import com.javasproject.eventmanagement.mapper.ResourceMapper;
import com.javasproject.eventmanagement.repository.ResourceRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Service
public class ResourceService {
    ResourceRepository resourceRepository;
    ResourceMapper resourceMapper;

    public ResourceResponse createResource(ResourceCreationRequest request) {
        Resource resource = new Resource();
        resource.setName(request.getName());
        resource.setType(request.getType());
        resource.setTotalQuantity(request.getTotalQuantity());

        Resource savedResource = resourceRepository.save(resource);
        return resourceMapper.toResourceResponse(savedResource);
    }

    public ResourceResponse updateResource(String resourceId, ResourceCreationRequest request) {
        Resource resource = resourceRepository.findById(resourceId).orElseThrow(() -> new AppException(ErrorCode.RESOURCE_NOT_FOUND));

        if (request.getName() != null && !request.getName().isEmpty()) {
            resource.setName(request.getName());
        }
        if (request.getType() != null && !request.getType().isEmpty()) {
            resource.setType(request.getType());
        }
        if (request.getTotalQuantity() != 0) {
            resource.setTotalQuantity(request.getTotalQuantity());
        }

        return resourceMapper.toResourceResponse(resourceRepository.save(resource));
    }

    public void deleteResource(String resourceId) {
        Resource resource = resourceRepository.findById(resourceId).orElseThrow(() -> new AppException(ErrorCode.RESOURCE_NOT_FOUND));
        resource.setDeleted(true);
        resourceRepository.save(resource);
    }

    public List<ResourceResponse> getAllResources() {
        return resourceRepository.findAll().stream().map(resourceMapper::toResourceResponse).collect(Collectors.toList());
    }

    public ResourceResponse getResourceById(String id) {
        return resourceRepository.findById(id).map(resourceMapper::toResourceResponse).orElseThrow(() -> new AppException(ErrorCode.RESOURCE_NOT_FOUND));
    }
    public Resource getResourceObjectById(String id) {
        return resourceRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.RESOURCE_NOT_FOUND));
    }
}
