package com.javasproject.eventmanagement.mapper;

import com.javasproject.eventmanagement.dto.request.ContractCreationRequest;
import com.javasproject.eventmanagement.dto.response.ContractCreateResponse;
import com.javasproject.eventmanagement.dto.response.ContractResponse;
import com.javasproject.eventmanagement.dto.response.PaymentResponse;
import com.javasproject.eventmanagement.entity.Contract;
import com.javasproject.eventmanagement.entity.Payment;
import com.javasproject.eventmanagement.service.PaymentService;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Comparator;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = {PaymentService.class, PaymentMapper.class})
public interface ContractMapper {
    Contract toContract(ContractCreationRequest request);

    @Mapping(target = "dateEntered", source = "dateEntered", dateFormat = "yyyy-MM-dd HH:mm:ss")
    @Mapping(target = "expirationDate", source = "expirationDate", dateFormat = "yyyy-MM-dd")
    @Mapping(target = "companySignedDate", source = "companySignedDate", dateFormat = "yyyy-MM-dd")
    @Mapping(target = "customerSignedDate", source = "customerSignedDate", dateFormat = "yyyy-MM-dd")
    @Mapping(target = "totalValue", source = "totalValue", qualifiedByName = "formatToVND")
    @Mapping(target = "sumPaid", source = "sumPaid", qualifiedByName = "formatToVND")
    @Mapping(target = "discount", source = "discount", qualifiedByName = "formatToVND")
    @Mapping(target = "netValue", source = "netValue", qualifiedByName = "formatToVND")
    @Mapping(target = "taxValue", source = "taxValue", qualifiedByName = "formatToVND")
    @Mapping(target = "totalNumber", source = "totalValue")
    @Mapping(target = "discountNumber", source = "discount")
    @Mapping(target = "sumPaidNumber", source = "sumPaid")
    @Mapping(target = "taxable", source = "taxValue", qualifiedByName = "checkTaxable")
    @Mapping(target = "payments", source = "payment", qualifiedByName = "mapPayments")
    @Mapping(target = "taxValueNumber", source = "taxValue")
    @Mapping(target = "netTotalValueNumber", source = "netValue")
    @Mapping(target = "eventId", source = "event.id")
    @Mapping(target = "eventName", source = "event.name")
    ContractResponse toContractResponse(Contract contracts);

    @Mapping(target = "dateEntered", source = "dateEntered", dateFormat = "yyyy-MM-dd HH:mm:ss")
    @Mapping(target = "expirationDate", source = "expirationDate", dateFormat = "yyyy-MM-dd")
    @Mapping(target = "companySignedDate", source = "companySignedDate", dateFormat = "yyyy-MM-dd HH:mm:ss")
    @Mapping(target = "customerSignedDate", source = "customerSignedDate", dateFormat = "yyyy-MM-dd HH:mm:ss")
    @Mapping(target = "totalValue", source = "totalValue", qualifiedByName = "formatToVND")
    @Mapping(target = "sumPaid", source = "sumPaid", qualifiedByName = "formatToVND")
    @Mapping(target = "discount", source = "discount", qualifiedByName = "formatToVND")
    @Mapping(target = "netValue", source = "netValue", qualifiedByName = "formatToVND")
    @Mapping(target = "taxValue", source = "taxValue", qualifiedByName = "formatToVND")
    @Mapping(target = "totalNumber", source = "totalValue")
    @Mapping(target = "discountNumber", source = "discount")
    @Mapping(target = "sumPaidNumber", source = "sumPaid")
    @Mapping(target = "taxable", source = "taxValue", qualifiedByName = "checkTaxable")
    @Mapping(target = "taxValueNumber", source = "taxValue")
    @Mapping(target = "netTotalValueNumber", source = "netValue")
    ContractCreateResponse toContractCreateResponse(Contract contracts);

    @Named("formatToVND")
    static String formatToVND(double value) {
        return PaymentService.formatToVND(value);
    }
    @Named("checkTaxable")
    static boolean checkTaxable(double taxValue) {
        return taxValue > 0;
    }
    @Named("mapPayments")
    @IterableMapping(qualifiedByName = "toPaymentResponse")
    static Set<PaymentResponse> mapPayments(Set<Payment> payments) {
        return payments.stream()
                .map(PaymentMapper.INSTANCE::toPaymentResponse)
                .collect(Collectors.toSet());
    }
}
