package com.javasproject.eventmanagement.service;

import com.javasproject.eventmanagement.repository.EventBookingRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EventBookingService {

    EventBookingRepository eventBookingRepository;
    public long countBookedFacilityById(String facilityId, LocalDateTime startDate, LocalDateTime endDate) {
        return eventBookingRepository.countBookedFacilityById(facilityId, startDate, endDate);
    }
}
