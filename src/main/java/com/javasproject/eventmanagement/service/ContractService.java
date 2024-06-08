package com.javasproject.eventmanagement.service;

import com.javasproject.eventmanagement.dto.request.ContractCreationRequest;
import com.javasproject.eventmanagement.dto.request.ContractUpdateRequest;
import com.javasproject.eventmanagement.dto.request.PaymentCreationRequest;
import com.javasproject.eventmanagement.dto.response.ContractCreateResponse;
import com.javasproject.eventmanagement.dto.response.ContractResponse;
import com.javasproject.eventmanagement.dto.response.OptionResponse;
import com.javasproject.eventmanagement.entity.Contract;
import com.javasproject.eventmanagement.entity.Event;
import com.javasproject.eventmanagement.entity.EventDetails;
import com.javasproject.eventmanagement.entity.Payment;
import com.javasproject.eventmanagement.exception.AppException;
import com.javasproject.eventmanagement.exception.ErrorCode;
import com.javasproject.eventmanagement.mapper.ContractMapper;
import com.javasproject.eventmanagement.repository.ContractRepository;

import com.javasproject.eventmanagement.repository.EventRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.lang.Math.round;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Service
public class ContractService {
    ContractRepository contractsRepository;
    ContractMapper contractsMapper;
    EventService eventService;
    PaymentService paymentService;

    private double calculateTaxValue(double netValue) {
        double taxRate = 0.10; // 10% tax rate
        return Math.round(netValue * taxRate * 100.0) / 100.0; // Calculate tax and round to 2 decimal places
    }
    private String generateContractCode() {
        return "CONTRACT-" + LocalDateTime.now().getYear() + "-" + (contractsRepository.count() + 1);
    }
    public ContractCreateResponse createContract(ContractCreationRequest request) {
        Contract contracts = new Contract();
        contracts.setName(generateContractCode());
        contracts.setExpirationDate(request.getExpirationDate().plusDays(1));
        contracts.setStatus("Draft");
        contracts.setTerms(request.getTerms());
        contracts.setPaymentTerm(request.getPaymentTerm());
        contracts.setEvent(eventService.getObjectById(request.getEventId()));
        double netValue = eventService.getObjectById(request.getEventId()).getEventDetails().stream().mapToLong(EventDetails::getPrice).sum() - contracts.getDiscount();
        contracts.setNetValue(netValue);
        contracts.setTaxValue(calculateTaxValue(netValue));
        contracts.setTotalValue(netValue + contracts.getTaxValue());
        double cost = eventService.getObjectById(request.getEventId()).getEventDetails().stream().mapToLong(EventDetails::getCost).sum();

        PaymentCreationRequest paymentDepositRequest = new PaymentCreationRequest();
        paymentDepositRequest.setContract(contracts);
        paymentDepositRequest.setType("Deposit");
        paymentDepositRequest.setValue(cost);

        PaymentCreationRequest paymentRequest = new PaymentCreationRequest();
        paymentRequest.setContract(contracts);
        paymentRequest.setType("Pay");

        Contract savedContract = contractsRepository.save(contracts);

        switch (request.getPaymentTerm()) {
            case "1": // pay 100%
                paymentRequest.setValue(contracts.getTotalValue());
                paymentService.createPayment(paymentRequest);
                break;
            case "2": // deposit and pay later
                paymentService.createPayment(paymentDepositRequest);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                paymentRequest.setValue(contracts.getTotalValue() - cost);
                paymentService.createPayment(paymentRequest);
                break;
            case "3": // deposit and 2 term pay later 50%
                paymentService.createPayment(paymentDepositRequest);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                paymentRequest.setValue((contracts.getTotalValue() - cost)/2);
                paymentService.createPayment(paymentRequest);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                paymentService.createPayment(paymentRequest);
                break;
            default:
                break;
        }
        //Update event status
        eventService.updateEventStatus(request.getEventId(), "Contracted");
        return contractsMapper.toContractCreateResponse(savedContract);
    }

    public ContractResponse updateContract(String contractsId, ContractUpdateRequest request) {
        Contract contracts = contractsRepository.findById(contractsId).orElseThrow(() -> new RuntimeException("Contract not found"));


        if (request.getCompanySignedDate() != null) {
            contracts.setCompanySignedDate(request.getCompanySignedDate());
        }
        if (request.getCustomerSignedDate() != null) {
            contracts.setCustomerSignedDate(request.getCustomerSignedDate());
        }
        if (request.getExpirationDate() != null) {
            contracts.setExpirationDate(request.getExpirationDate());
        }
        if (request.getStatus() != null) {
            contracts.setStatus(request.getStatus());
        }
        if (request.getDiscount() > 0) {
            contracts.setDiscount(request.getDiscount());
        }
        if (request.getTerms() != null) {
            contracts.setTerms(request.getTerms());
        }
        if (request.getSumPaid() > 0) {
            contracts.setSumPaid(request.getSumPaid());
        }

        return contractsMapper.toContractResponse(contractsRepository.save(contracts));
    }

    public void deleteContract(String contractsId) {
        Contract contracts = contractsRepository.findById(contractsId).orElseThrow(() -> new RuntimeException("Contract not found"));
        if(contracts.getPayment().stream().anyMatch(payment -> payment.getStatus().equals("Paid"))){
            throw new AppException(ErrorCode.HAVE_PAID_PAYMENT);
        }
        contracts.setDeleted(true);
        eventService.updateEventStatus(contracts.getEvent().getId(), "Draft");
        contractsRepository.save(contracts);
    }
    public List<ContractResponse> getAllContract() {
        return contractsRepository.findAllByDeletedFalse().stream()
                .sorted(Comparator.comparing(Contract::getDateEntered).reversed()) // Sort by dateEntered
                .map(contractsMapper::toContractResponse).collect(Collectors.toList());
    }

    public long countAllContract() {
        return contractsRepository.count();
    }

    public ContractResponse getContractById(String id) {
        return contractsRepository.findById(id).map(contractsMapper::toContractResponse).orElseThrow(() -> new RuntimeException("Contract not found"));
    }

    public Contract getObjectById(String id) {
        return contractsRepository.findById(id).orElseThrow(() -> new RuntimeException("Contract not found"));
    }

    public Map<String, Object> getRelated() {
        List<OptionResponse> eventList = eventService.getAllOptions();
        return Map.of("eventId", eventList);
    }

}
