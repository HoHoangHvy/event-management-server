package com.javasproject.eventmanagement.service;

import com.javasproject.eventmanagement.dto.request.FacilityCreationRequest;
import com.javasproject.eventmanagement.dto.response.FacilityResponse;
import com.javasproject.eventmanagement.dto.response.ResourceResponse;
import com.javasproject.eventmanagement.entity.Facility;
import com.javasproject.eventmanagement.entity.Resource;
import com.javasproject.eventmanagement.entity.ResourceBookingDetail;
import com.javasproject.eventmanagement.mapper.FacilityMapper;
import com.javasproject.eventmanagement.repository.FacilityRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Service
public class FacilityService {
    FacilityRepository facilityRepository;
    FacilityMapper facilityMapper;

    public FacilityResponse createFacility(FacilityCreationRequest request) {
        Facility facility = facilityMapper.toFacility(request);
        facility.setDateEntered(LocalDateTime.now());
        facility.setDeleted(false);
        Facility savedFacility = facilityRepository.save(facility);
        return facilityMapper.toFacilityResponse(savedFacility);
    }

    public FacilityResponse updateFacility(String facilityId, FacilityCreationRequest request) {
        Facility facility = facilityRepository.findById(facilityId).orElseThrow(() -> new RuntimeException("Facility not found"));

        if (request.getName() != null) {
            facility.setName(request.getName());
        }
        if (request.getTotal() > 0) {
            facility.setTotal(request.getTotal());
        }
        if (request.getType() != null) {
            facility.setType(request.getType());
        }
        if (request.getPrice() > 0) {
            facility.setPrice(request.getPrice());
        }

        return facilityMapper.toFacilityResponse(facilityRepository.save(facility));
    }

    public void deleteFacility(String facilityId) {
        Facility facility = facilityRepository.findById(facilityId).orElseThrow(() -> new RuntimeException("Facility not found"));
        facility.setDeleted(true);
        facilityRepository.save(facility);
    }

    public List<FacilityResponse> getAllFacilities() {
        return facilityRepository.findAllByDeletedFalse().stream().map(facilityMapper::toFacilityResponse).collect(Collectors.toList());
    }

    public long countAllFacilities() {
        return facilityRepository.count();
    }

    public FacilityResponse getFacilityById(String id) {
        return facilityRepository.findById(id).map(facilityMapper::toFacilityResponse).orElseThrow(() -> new RuntimeException("Facility not found"));
    }
    public Facility getObjectById(String id) {
        return facilityRepository.findById(id).orElseThrow(() -> new RuntimeException("Facility not found"));
    }
    private EventBookingService eventBookingService;
    public LocalDateTime convertTimeZone(LocalDateTime localDateTime){
        // Convert LocalDateTime to ZonedDateTime with UTC time zone
        ZonedDateTime utcDateTime = localDateTime.atZone(ZoneId.of("UTC"));

        // Convert UTC time to GMT+7 (Asia/Ho_Chi_Minh) time zone
        ZonedDateTime gmtPlus7DateTime = utcDateTime.withZoneSameInstant(ZoneId.of("Asia/Ho_Chi_Minh"));
        return gmtPlus7DateTime.toLocalDateTime();
    }
    public List<FacilityResponse> getAllAvailableFacility(LocalDateTime startDate, LocalDateTime endDate) {
        // Fetch all resources
        List<Facility> allFacilities = facilityRepository.findAllByDeletedFalse();
        // Filter out resources where the quantity is equal to the sum of its relationships
        List<Facility> availableFacilities = allFacilities.stream()
                .filter(facility -> {
                    long bookedQuantity = eventBookingService.countBookedFacilityById(facility.getId(), convertTimeZone(startDate), convertTimeZone(endDate));
                    return facility.getTotal() > bookedQuantity;
                })
                .map(facility -> {
                    long bookedQuantity = eventBookingService.countBookedFacilityById(facility.getId(), convertTimeZone(startDate), convertTimeZone(endDate));
                    facility.setAvailableQuantity(facility.getTotal() - bookedQuantity);
                    return facility;
                })
                .toList();

        // Convert the available resources to response DTOs
        return availableFacilities.stream().map(facilityMapper::toFacilityResponse)
                .collect(Collectors.toList());
    }

}
