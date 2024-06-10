package com.javasproject.eventmanagement.service;

import com.javasproject.eventmanagement.dto.request.HallCreationRequest;
import com.javasproject.eventmanagement.dto.response.HallResponse;
import com.javasproject.eventmanagement.dto.response.OptionResponse;
import com.javasproject.eventmanagement.dto.response.ResourceResponse;
import com.javasproject.eventmanagement.entity.Hall;
import com.javasproject.eventmanagement.entity.Resource;
import com.javasproject.eventmanagement.entity.ResourceBookingDetail;
import com.javasproject.eventmanagement.mapper.HallMapper;
import com.javasproject.eventmanagement.repository.HallRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Service
public class HallService {
    HallRepository hallRepository;
    HallMapper hallMapper;

    public HallResponse createHall(HallCreationRequest request) {
        Hall hall = hallMapper.toHall(request);
        hall.setDateEntered(LocalDateTime.now());
        hall.setDeleted(false);
        Hall savedHall = hallRepository.save(hall);
        return hallMapper.toHallResponse(savedHall);
    }

    public HallResponse updateHall(String hallId, HallCreationRequest request) {
        Hall hall = hallRepository.findById(hallId).orElseThrow(() -> new RuntimeException("Hall not found"));

        if (request.getName() != null) {
            hall.setName(request.getName());
        }
        if (request.getScale() > 0) {
            hall.setScale(request.getScale());
        }
        if (request.getLocation() != null) {
            hall.setLocation(request.getLocation());
        }
        if (request.getInUse() != null) {
            hall.setInUse(request.getInUse());
        }

        return hallMapper.toHallResponse(hallRepository.save(hall));
    }

    public void deleteHall(String hallId) {
        Hall hall = hallRepository.findById(hallId).orElseThrow(() -> new RuntimeException("Hall not found"));
        hall.setDeleted(true);
        hallRepository.save(hall);
    }

    public List<HallResponse> getAllHalls() {
        return hallRepository.findAll().stream().map(hallMapper::toHallResponse).collect(Collectors.toList());
    }

    public long countAllHalls() {
        return hallRepository.count();
    }

    public HallResponse getHallById(String id) {
        return hallRepository.findById(id).map(hallMapper::toHallResponse).orElseThrow(() -> new RuntimeException("Hall not found"));
    }
    public List<OptionResponse> getAllOptions() {
        return hallRepository.findAllByDeletedFalse().stream().map(hallMapper::toOptionResponse).collect(Collectors.toList());
    }
    public LocalDateTime convertTimeZone(LocalDateTime localDateTime){
        // Convert LocalDateTime to ZonedDateTime with UTC time zone
        ZonedDateTime utcDateTime = localDateTime.atZone(ZoneId.of("UTC"));

        // Convert UTC time to GMT+7 (Asia/Ho_Chi_Minh) time zone
        ZonedDateTime gmtPlus7DateTime = utcDateTime.withZoneSameInstant(ZoneId.of("Asia/Ho_Chi_Minh"));
        return gmtPlus7DateTime.toLocalDateTime();
    }
    public List<HallResponse> getAllAvailableHalls(LocalDateTime startDate, LocalDateTime endDate) {
        // Fetch all resources
        List<Hall> allResources = hallRepository.findAllByDeletedFalse();
        // Filter out resources where the quantity is equal to the sum of its relationships
        List<Hall> availableResources = allResources.stream()
                .filter(hall -> hall.getEvent().stream()
                        .noneMatch(booking -> booking.getStartDate().isBefore(convertTimeZone(endDate)) && booking.getEndDate().isAfter(convertTimeZone(startDate))))
                .collect(Collectors.toList());

        // Convert the available resources to response DTOs
        return availableResources.stream()
                .map(hallMapper::toHallResponse)
                .collect(Collectors.toList());
    }

    public Hall getHallObjectById(String id) {
        return hallRepository.findById(id).orElse(null);
    }

}
