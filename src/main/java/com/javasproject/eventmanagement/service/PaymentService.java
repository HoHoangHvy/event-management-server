package com.javasproject.eventmanagement.service;

import com.javasproject.eventmanagement.dto.request.PaymentCreationRequest;
import com.javasproject.eventmanagement.entity.Payment;
import com.javasproject.eventmanagement.exception.AppException;
import com.javasproject.eventmanagement.exception.ErrorCode;
import com.javasproject.eventmanagement.repository.PaymentRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PaymentService {
    PaymentRepository paymentRepository;

    public Payment createPayment (PaymentCreationRequest request){
        Payment payment = new Payment();

        payment.setName(request.getName());
        payment.setType(request.getType());
        payment.setPaymentDate(request.getPaymentDate());
        payment.setValue(request.getValue());

        return paymentRepository.save(payment);
    }

    public List<Payment> getAllPayment(){
        return paymentRepository.findAll();
    }

    public Payment getPaymentById(String paymentId){
        return paymentRepository.findById(paymentId).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXIST));
    }

    public Payment updatePayment(String paymentId, PaymentCreationRequest request){
        Payment payment = getPaymentById(paymentId);

        payment.setName(request.getName());
        payment.setType(request.getType());
        payment.setPaymentDate(request.getPaymentDate());
        payment.setValue(request.getValue());

        return paymentRepository.save(payment);
    }

    public void deletePayment (String paymentId){
        if(!paymentRepository.existsById(paymentId))
            throw new AppException(ErrorCode.USER_NOT_EXIST);
        paymentRepository.deleteById(paymentId);
    }
}
