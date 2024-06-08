package com.javasproject.eventmanagement.controller;

import com.javasproject.eventmanagement.dto.request.ApiResponse;
import com.javasproject.eventmanagement.dto.request.ContractCreationRequest;
import com.javasproject.eventmanagement.dto.request.ContractUpdateRequest;
import com.javasproject.eventmanagement.dto.response.ContractCreateResponse;
import com.javasproject.eventmanagement.dto.response.ContractResponse;
import com.javasproject.eventmanagement.dto.response.ListResponse;
import com.javasproject.eventmanagement.service.ContractService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/")
public class ContractController {

    @Autowired
    private ContractService contractsService;

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/contracts")
    public ApiResponse<ContractCreateResponse> createContract(@RequestBody ContractCreationRequest request) {
        ApiResponse<ContractCreateResponse> apiResponse = new ApiResponse<>();
        apiResponse.setData(contractsService.createContract(request));
        apiResponse.setMessage("Successfully created the contract.");
        return apiResponse;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/contracts")
    public ApiResponse<ListResponse> getAllContract() {
        ApiResponse<ListResponse> apiResponse = new ApiResponse<>();
        var listResponse = new ListResponse<ContractResponse>();
        List<ContractResponse> contracts = contractsService.getAllContract();
        long totalData = contractsService.countAllContract();
        listResponse.setListData(contracts);
        listResponse.setTotalData(totalData);

        apiResponse.setData(listResponse);
        apiResponse.setMessage("Successfully retrieved the contract list.");
        return apiResponse;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/contracts/{contractsId}")
    public ResponseEntity<ApiResponse<ContractResponse>> getContractById(@PathVariable("contractsId") String contractsId) {
        ApiResponse<ContractResponse> apiResponse = new ApiResponse<>();
        apiResponse.setData(contractsService.getContractById(contractsId));
        apiResponse.setMessage("Successfully retrieved the contract.");
        return ResponseEntity.ok(apiResponse);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping("/contracts/{id}")
    public ApiResponse<ContractResponse> updateContract(@PathVariable String id, @RequestBody ContractUpdateRequest request) {
        ApiResponse<ContractResponse> apiResponse = new ApiResponse<>();
        apiResponse.setData(contractsService.updateContract(id, request));
        apiResponse.setMessage("Successfully updated the contract.");
        return apiResponse;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping("/contracts/{contractsId}")
    public ApiResponse<String> deleteContract(@PathVariable String contractsId) {
        ApiResponse<String> apiResponse = new ApiResponse<>();
        apiResponse.setData("Contract has been deleted");
        contractsService.deleteContract(contractsId);
        return apiResponse;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/related/contracts")
    public ApiResponse<Map<String, Object>> getRelated() {
        ApiResponse<Map<String, Object>> apiResponse = new ApiResponse<>();
        Map<String, Object> listResponse = contractsService.getRelated();
        apiResponse.setData(listResponse);
        apiResponse.setMessage("Successfully retrieved related data.");
        return apiResponse;
    }
}
