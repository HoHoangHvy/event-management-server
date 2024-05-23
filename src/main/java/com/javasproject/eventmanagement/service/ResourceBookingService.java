package com.javasproject.eventmanagement.service;

import com.javasproject.eventmanagement.dto.request.ResourceBookingRequest;
import com.javasproject.eventmanagement.dto.response.ResourceBookingResponse;
import com.javasproject.eventmanagement.entity.ResourceBookingDetail;
import com.javasproject.eventmanagement.mapper.ResourceBookingMapper;
import com.javasproject.eventmanagement.repository.ResourceBookingRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ResourceBookingService {

    ResourceService resourceService;
    EmployeeService employeeService;
    ResourceBookingRepository resourceBookingRepository;
    ResourceBookingMapper rbMapper;

    public ResourceBookingResponse createResourceBooking(ResourceBookingRequest request) {
        ResourceBookingDetail object = new ResourceBookingDetail();
        object.setResource(this.resourceService.getResourceObjectById(request.getResourceId()));
        object.setEmployee(this.employeeService.getEmployeeObjectById(request.getEmployeeId()));
        object.setStartDate(request.getStartDate());
        object.setEndDate(request.getEndDate());
        object.setStatus(request.getStatus());
        object.setQuantity(request.getQuantity());

        return rbMapper.toResourceBookingResponse(this.resourceBookingRepository.save(object));
    }

    public List<ResourceBookingResponse> getAllResourceBookings() {
        return resourceBookingRepository.findAll().stream()
                .map(rbMapper::toResourceBookingResponse)
                .collect(Collectors.toList());
    }

    public ResourceBookingResponse getResourceBookingById(String id) {
        ResourceBookingDetail object = resourceBookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Resource Booking not found"));
        return rbMapper.toResourceBookingResponse(object);
    }

    public ResourceBookingResponse updateResourceBooking(String id, ResourceBookingRequest request) {
        ResourceBookingDetail object = resourceBookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Resource Booking not found"));

        object.setResource(this.resourceService.getResourceObjectById(request.getResourceId()));
        object.setEmployee(this.employeeService.getEmployeeObjectById(request.getEmployeeId()));
        object.setStartDate(request.getStartDate());
        object.setEndDate(request.getEndDate());
        object.setStatus(request.getStatus());
        object.setQuantity(request.getQuantity());

        return rbMapper.toResourceBookingResponse(this.resourceBookingRepository.save(object));
    }

    public void deleteResourceBooking(String id) {
        ResourceBookingDetail object = resourceBookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Resource Booking not found"));
        resourceBookingRepository.delete(object);
    }
}
