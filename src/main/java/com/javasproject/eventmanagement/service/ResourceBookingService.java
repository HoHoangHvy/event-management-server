package com.javasproject.eventmanagement.service;

import com.javasproject.eventmanagement.dto.request.RequestCreationRequest;
import com.javasproject.eventmanagement.dto.request.ResourceBookingRequest;
import com.javasproject.eventmanagement.dto.response.RequestResponse;
import com.javasproject.eventmanagement.dto.response.ResourceBookingResponse;
import com.javasproject.eventmanagement.entity.ResourceBookingDetail;
import com.javasproject.eventmanagement.mapper.ResourceBookingMapper;
import com.javasproject.eventmanagement.repository.ResourceBookingRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ResourceBookingService {

    ResourceService resourceService;
    UserService userService;
    RequestService requestService;
    ResourceBookingRepository resourceBookingRepository;
    ResourceBookingMapper rbMapper;

    public ResourceBookingResponse createResourceBooking(ResourceBookingRequest request) {
        ResourceBookingDetail object = new ResourceBookingDetail();
        object.setResource(this.resourceService.getResourceObjectById(request.getResourceId()));
        object.setEmployee(this.userService.getCurrentUser().getEmployee());
        object.setStartDate(request.getStartDate());
        object.setEndDate(request.getEndDate());
        object.setStatus("Wait for approval");
        object.setQuantity(request.getQuantity());
        object.setReason(request.getReason());

        var savedResourceBooking = this.resourceBookingRepository.save(object);
        RequestCreationRequest requestCreationRequest = new RequestCreationRequest();
        requestCreationRequest.setName("Borrow Resource");
        requestCreationRequest.setType("Resource");
        requestCreationRequest.setContent("Resource Booking" + " " + savedResourceBooking.getStartDate() + " " + savedResourceBooking.getEndDate() + " " + object.getQuantity() + " " + object.getReason());
        requestCreationRequest.setStatus("Wait for approval");
        requestCreationRequest.setResourceBookingId(savedResourceBooking.getId());
        this.requestService.createRequest(requestCreationRequest);
        return rbMapper.toResourceBookingResponse(savedResourceBooking);
    }

    public List<ResourceBookingResponse> getAllResourceBookings() {
        return resourceBookingRepository.findAll().stream()
                .filter(resourceBooking -> "Approved".equals(resourceBooking.getStatus())) // Filter by status
                .map(rbMapper::toResourceBookingResponse)
                .sorted(Comparator.comparing(ResourceBookingResponse::getDateEntered).reversed())
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
        object.setStatus(request.getStatus());
        return rbMapper.toResourceBookingResponse(this.resourceBookingRepository.save(object));
    }

    public void deleteResourceBooking(String id) {
        ResourceBookingDetail object = resourceBookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Resource Booking not found"));
        resourceBookingRepository.delete(object);
    }
}
