package com.javasproject.eventmanagement.service;

import com.javasproject.eventmanagement.dto.request.PaymentCreationRequest;
import com.javasproject.eventmanagement.dto.request.PaymentUpdateRequest;
import com.javasproject.eventmanagement.dto.response.PaymentResponse;
import com.javasproject.eventmanagement.entity.Payment;
import com.javasproject.eventmanagement.mapper.PaymentMapper;
import com.javasproject.eventmanagement.repository.ContractRepository;
import com.javasproject.eventmanagement.repository.EventRepository;
import com.javasproject.eventmanagement.repository.PaymentRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Service
public class PaymentService {
    PaymentRepository paymentRepository;
    PaymentMapper paymentMapper;
    EventRepository eventRepository;

    public static String formatToVND(double amount) {
        Locale vietnam = new Locale("vi", "VN");
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(vietnam);
        return currencyFormatter.format(amount);
    }
    public PaymentResponse createPayment(PaymentCreationRequest request) {
        Payment payment = new Payment();
        payment.setContract(request.getContract());
        payment.setType(request.getType());
        payment.setValue(request.getValue());
        payment.setName("PAYMENT-" + LocalDateTime.now().getYear() + "-" + (paymentRepository.count() + 1));
        Payment savedPayment = paymentRepository.save(payment);
        return paymentMapper.toPaymentResponse(savedPayment);
    }
    UserService userService;
    ContractRepository contractRepository;
    public PaymentResponse updatePayment(String paymentId, PaymentUpdateRequest request) {
        Payment payment = paymentRepository.findById(paymentId).orElseThrow(() -> new RuntimeException("Payment not found"));

        if (request.getStatus() != null) {

            if(request.getStatus().equals("Paid")){
                if (request.getPaymentMethod() != null) {
                    payment.setPaymentMethod(request.getPaymentMethod());
                }
                payment.setStatus("Paid");
                payment.setPaymentDate(LocalDateTime.now());
                payment.setConfirmedBy(this.userService.getCurrentUser().getEmployee());
                contractRepository.updateIncreaseContractSumPaid(payment.getContract().getId(), payment.getValue());
                if(payment.getType().equals("Deposit")){
                    contractRepository.updateContractStatus(payment.getContract().getId(), "Deposited");
                    eventRepository.updateEventStatus(payment.getContract().getEvent().getId(), "Wait for approval");
                }
            }

            if(request.getStatus().equals("Unpaid") && payment.getStatus().equals("Paid")){
                payment.setStatus("Unpaid");
                payment.setPaymentMethod(null);
                payment.setPaymentDate(null);
                payment.setConfirmedBy(null);
                contractRepository.updateDecreaseContractSumPaid(payment.getContract().getId(), payment.getValue());
            }
            if(!payment.getType().equals("Deposit")){
                contractRepository.updateContractStatusPartPaid();
            }
            contractRepository.updateContractStatusFullyPaid();
        }

        return paymentMapper.toPaymentResponse(paymentRepository.save(payment));
    }

    public void deletePayment(String paymentId) {
        Payment payment = paymentRepository.findById(paymentId).orElseThrow(() -> new RuntimeException("Payment not found"));
        payment.setDeleted(true);
        paymentRepository.save(payment);
    }

    public List<PaymentResponse> getAllPayments() {
        return paymentRepository.findAllByDeletedFalse().stream().map(paymentMapper::toPaymentResponse).collect(Collectors.toList());
    }

    public long countAllPayments() {
        return paymentRepository.count();
    }

    public PaymentResponse getPaymentById(String id) {
        return paymentRepository.findById(id).map(paymentMapper::toPaymentResponse).orElseThrow(() -> new RuntimeException("Payment not found"));
    }

    public Payment getObjectById(String id) {
        return paymentRepository.findById(id).orElseThrow(() -> new RuntimeException("Payment not found"));
    }
}
