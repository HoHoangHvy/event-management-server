package com.javasproject.eventmanagement.service;

import com.javasproject.eventmanagement.dto.request.ThirdPartyCreationRequest;
import com.javasproject.eventmanagement.dto.response.ThirdPartyResponse;
import com.javasproject.eventmanagement.entity.ThirdParty;
import com.javasproject.eventmanagement.mapper.ThirdPartyMapper;
import com.javasproject.eventmanagement.repository.ThirdPartyRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Service
public class ThirdPartyService {
    ThirdPartyRepository thirdPartyRepository;
    ThirdPartyMapper thirdPartyMapper;

    public ThirdPartyResponse createThirdParty(ThirdPartyCreationRequest request) {
        ThirdParty thirdParty = thirdPartyMapper.toThirdParty(request);
        thirdParty.setDateEntered(LocalDateTime.now());
        thirdParty.setDeleted(false);
        ThirdParty savedThirdParty = thirdPartyRepository.save(thirdParty);
        return thirdPartyMapper.toThirdPartyResponse(savedThirdParty);
    }

    public ThirdPartyResponse updateThirdParty(String thirdPartyId, ThirdPartyCreationRequest request) {
        ThirdParty thirdParty = thirdPartyRepository.findById(thirdPartyId).orElseThrow(() -> new RuntimeException("Third Party not found"));

        if (request.getName() != null) {
            thirdParty.setName(request.getName());
        }
        if (request.getSupplier() != null) {
            thirdParty.setSupplier(request.getSupplier());
        }
        if (request.getCost() != null) {
            thirdParty.setCost(request.getCost());
        }
        if (request.getPrice() != null) {
            thirdParty.setPrice(request.getPrice());
        }
        if (request.getType() != null) {
            thirdParty.setType(request.getType());
        }

        return thirdPartyMapper.toThirdPartyResponse(thirdPartyRepository.save(thirdParty));
    }

    public void deleteThirdParty(String thirdPartyId) {
        ThirdParty thirdParty = thirdPartyRepository.findById(thirdPartyId).orElseThrow(() -> new RuntimeException("Third Party not found"));
        thirdParty.setDeleted(true);
        thirdPartyRepository.save(thirdParty);
    }

    public List<ThirdPartyResponse> getAllThirdParties() {
        return thirdPartyRepository.findAllByDeletedFalse().stream().map(thirdPartyMapper::toThirdPartyResponse).collect(Collectors.toList());
    }

    public long countAllThirdParties() {
        return thirdPartyRepository.count();
    }

    public ThirdPartyResponse getThirdPartyById(String id) {
        return thirdPartyRepository.findById(id).map(thirdPartyMapper::toThirdPartyResponse).orElseThrow(() -> new RuntimeException("Third Party not found"));
    }
    public ThirdParty getObjectById(String id) {
        return thirdPartyRepository.findById(id).orElseThrow(() -> new RuntimeException("Third Party not found"));
    }
}
