package com.javasproject.eventmanagement.controller;

import com.javasproject.eventmanagement.dto.request.ApiResponse;
import com.javasproject.eventmanagement.entity.Contract;
import com.javasproject.eventmanagement.service.ContractService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/contracts")
public class ContractController {
    @Autowired
    ContractService contractService;
    @PostMapping
    public ApiResponse<Contract> createContract(@RequestBody  Contract contract){
        return ApiResponse.<Contract>builder()
                .data(contractService.upsert(contract))
                .build();
    }
    @GetMapping("/{id}")
    public ApiResponse<Contract> getContractById(@PathVariable String id){
        String message = "Successfully get the contract";
        Contract contract = contractService.getById(id);
        if (contract == null){
            message = "Not found this contract";
        }
        return ApiResponse.<Contract>builder()
                .data(contract)
                .message(message)
                .build();
    }
    @GetMapping
    public ApiResponse<List<Contract>> getAllContracts(){
        String message = "Successfully get the contract list";
        List<Contract> contractList = contractService.getContractList();
        if(contractList.isEmpty()){
            message = "Contracts list is empty";

        }
        return ApiResponse.<List<Contract>>builder()
                .data(contractList)
                .message(message)
                .build();
    }
    @PutMapping
    public ApiResponse<Contract> updateContract(@RequestBody  Contract contract){
        return ApiResponse.<Contract>builder()
                .data(contractService.upsert(contract))
                .build();
    }
    @DeleteMapping("/{id}")
    public ApiResponse<String> deleteContract(@PathVariable String id){
        String message = "Delete successfully";
        Boolean status = contractService.deleteById(id);
        if (!status){
            message = "Delete fail! Not found this record";
        }
        return ApiResponse.<String>builder()
                .message(message)
                .build();
    }
}
