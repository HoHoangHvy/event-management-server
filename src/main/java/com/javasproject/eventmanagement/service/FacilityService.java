package com.javasproject.eventmanagement.service;

import com.javasproject.eventmanagement.entity.Facility;
import com.javasproject.eventmanagement.repository.FacilityRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FacilityService {
    @Autowired
    private FacilityRepository facilityRepository;
    public Facility upsert(Facility facility){
        return facilityRepository.save(facility);
    }

    public Facility getById(String id){
        Optional<Facility> findById = facilityRepository.findById(id);
        return findById.orElse(null);
    }

    public List<Facility> getFacilityList() {
        return facilityRepository.findAll();
    }

    public Boolean deleteById(String id){
        if(facilityRepository.existsById(id)){
            facilityRepository.deleteById(id);
            return true;
        }
        else {
            return false;
        }
    }
}
