package com.javasproject.eventmanagement.service;

import com.javasproject.eventmanagement.entity.Contract;
import com.javasproject.eventmanagement.repository.ContractRepository;
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
public class ContractService {
    @Autowired
    ContractRepository contractRepository;

    public Contract upsert(Contract contract){
        return contractRepository.save(contract);
    }
    public Contract getById(String id){
        Optional<Contract> findById = contractRepository.findById(id);
        return findById.orElse(null);
    }
    public List<Contract> getContractList(){

        return contractRepository.findAllByDeletedFalse();
    }
    public Boolean deleteById(String id){
        if(contractRepository.existsById(id)){
            contractRepository.deleteById(id);
            return true;
        }
        else {
            return false;
        }

    }


}
