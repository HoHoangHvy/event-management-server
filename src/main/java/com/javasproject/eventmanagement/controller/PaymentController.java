package com.javasproject.eventmanagement.controller;

import com.javasproject.eventmanagement.dto.request.ApiResponse;
import com.javasproject.eventmanagement.dto.request.PaymentCreationRequest;
import com.javasproject.eventmanagement.dto.request.PaymentUpdateRequest;
import com.javasproject.eventmanagement.dto.response.PaymentResponse;
import com.javasproject.eventmanagement.dto.response.ListResponse;
import com.javasproject.eventmanagement.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/payments")
    public ApiResponse<PaymentResponse> createPayment(@RequestBody PaymentCreationRequest request) {
        ApiResponse<PaymentResponse> apiResponse = new ApiResponse<>();
        apiResponse.setData(paymentService.createPayment(request));
        apiResponse.setMessage("Successfully created the payment.");
        return apiResponse;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/payments")
    public ApiResponse<ListResponse> getAllPayments() {
        ApiResponse<ListResponse> apiResponse = new ApiResponse<>();
        var listResponse = new ListResponse<PaymentResponse>();
        List<PaymentResponse> payments = paymentService.getAllPayments();
        long totalData = paymentService.countAllPayments();
        listResponse.setListData(payments);
        listResponse.setTotalData(totalData);

        apiResponse.setData(listResponse);
        apiResponse.setMessage("Successfully retrieved the payment list.");
        return apiResponse;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/payments/{paymentId}")
    public ResponseEntity<ApiResponse<PaymentResponse>> getPaymentById(@PathVariable("paymentId") String paymentId) {
        ApiResponse<PaymentResponse> apiResponse = new ApiResponse<>();
        apiResponse.setData(paymentService.getPaymentById(paymentId));
        apiResponse.setMessage("Successfully retrieved the payment.");
        return ResponseEntity.ok(apiResponse);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping("/payments/{id}")
    public ApiResponse<PaymentResponse> updatePayment(@PathVariable String id, @RequestBody PaymentUpdateRequest request) {
        ApiResponse<PaymentResponse> apiResponse = new ApiResponse<>();
        apiResponse.setData(paymentService.updatePayment(id, request));
        apiResponse.setMessage("Successfully updated the payment.");
        return apiResponse;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping("/payments/{paymentId}")
    public ApiResponse<String> deletePayment(@PathVariable String paymentId) {
        ApiResponse<String> apiResponse = new ApiResponse<>();
        apiResponse.setData("Payment has been deleted");
        paymentService.deletePayment(paymentId);
        return apiResponse;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/related/payments")
    public ApiResponse<Map<String, Object>> getRelated() {
        ApiResponse<Map<String, Object>> apiResponse = new ApiResponse<>();
        Map<String, Object> listResponse = new HashMap<>();
        apiResponse.setData(listResponse);
        apiResponse.setMessage("Successfully retrieved related data.");
        return apiResponse;
    }
}
