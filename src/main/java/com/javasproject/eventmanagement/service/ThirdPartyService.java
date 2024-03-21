package com.javasproject.eventmanagement.service;

import com.javasproject.eventmanagement.entity.ThirdParty;
import com.javasproject.eventmanagement.repository.ThirdPartyRepository;
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
public class ThirdPartyService {
    @Autowired
    private ThirdPartyRepository thirdPartyRepository;
    public ThirdParty upsert(ThirdParty thirdParty){
        return thirdPartyRepository.save(thirdParty);
    }

    public ThirdParty getById(String id){
        Optional<ThirdParty> findById = thirdPartyRepository.findById(id);
        return findById.orElse(null);
    }

    public List<ThirdParty> getThirdPartyList() {
        return thirdPartyRepository.findAll();
    }

    public Boolean deleteById(String id){
        if(thirdPartyRepository.existsById(id)){
            thirdPartyRepository.deleteById(id);
            return true;
        }
        else {
            return false;
        }
    }
}
