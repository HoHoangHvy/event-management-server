package com.javasproject.eventmanagement.controller;

import com.javasproject.eventmanagement.dto.request.ApiResponse;
import com.javasproject.eventmanagement.dto.request.PaymentCreationRequest;
import com.javasproject.eventmanagement.entity.Payment;
import com.javasproject.eventmanagement.service.PaymentService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
@RequestMapping("/payments")
public class PaymentController {
    PaymentService paymentService;

    @PostMapping
    ApiResponse<Payment> createPayment (@RequestBody PaymentCreationRequest request){
        ApiResponse<Payment> apiResponse = new ApiResponse<>();
        apiResponse.setData(paymentService.createPayment(request));
        apiResponse.setMessage("Successfully build the payment.");
        return apiResponse;
    }

    @GetMapping
    ApiResponse<List<Payment>> getAllPayment (){
        ApiResponse<List<Payment>> apiResponse = new ApiResponse<>();
        apiResponse.setData(paymentService.getAllPayment());
        apiResponse.setMessage("Successfully get the payment's list");
        return apiResponse;
    }

    @GetMapping("/{paymentId}")
    ApiResponse<Payment> getPaymentById (@PathVariable ("paymentId") String paymentId){
        ApiResponse<Payment> apiResponse = new ApiResponse<>();
        apiResponse.setData(paymentService.getPaymentById(paymentId));
        apiResponse.setMessage("Successfully get the payment");
        return apiResponse;
    }

    @PutMapping("/{paymentId}")
    ApiResponse<Payment> updatePayment (@PathVariable String paymentId, @RequestBody PaymentCreationRequest request){
        ApiResponse<Payment> apiResponse = new ApiResponse<>();
        apiResponse.setData(paymentService.updatePayment(paymentId,request));
        apiResponse.setMessage("Successfully update the payment");
        return apiResponse;
    }

    @DeleteMapping("/{paymentId}")
    ApiResponse<String> deletePayment(@PathVariable String paymentId){
        ApiResponse<String> apiResponse = new ApiResponse<>();
        apiResponse.setData("payment has been deleted.");
        paymentService.deletePayment(paymentId);
        return apiResponse;
    }
}
