package com.javasproject.eventmanagement.controller;

import com.javasproject.eventmanagement.entity.Contract;
import com.javasproject.eventmanagement.service.ContractService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/contractPage")
public class ContractController {
    @Autowired
    private ContractService contractService;
    @PostMapping("/contract")
    public ResponseEntity<String> createContract(@RequestBody  Contract contract){
        String status = contractService.upsert(contract);
        return new ResponseEntity<>(status, HttpStatus.CREATED);
    }
    @GetMapping("/contract/{id}")
    public ResponseEntity<Contract> getContract(@PathVariable String id){
        Contract contract = contractService.getById(id);
        return new ResponseEntity<>(contract, HttpStatus.OK);
    }
    @GetMapping("/contracts")
    public ResponseEntity<List<Contract>> getAllContracts(){
        List<Contract> allContracts = contractService.getContractList();
        return new ResponseEntity<>(allContracts, HttpStatus.OK);
    }
    @PutMapping("/contract")
    public ResponseEntity<String> updateContract(@RequestBody  Contract contract){
        String status = contractService.upsert(contract);
        return new ResponseEntity<>(status, HttpStatus.OK);
    }
    @DeleteMapping("/contract/{id}")
    public ResponseEntity<String> deleteContract(@PathVariable String id){
        String status = contractService.deleteById(id);
        return new ResponseEntity<>(status, HttpStatus.OK);
    }
}
