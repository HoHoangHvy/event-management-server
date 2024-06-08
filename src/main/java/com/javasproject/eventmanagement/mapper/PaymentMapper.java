package com.javasproject.eventmanagement.mapper;

import com.javasproject.eventmanagement.dto.request.PaymentCreationRequest;
import com.javasproject.eventmanagement.dto.response.PaymentResponse;
import com.javasproject.eventmanagement.entity.Payment;
import com.javasproject.eventmanagement.service.PaymentService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {PaymentService.class})
public interface PaymentMapper {
    PaymentMapper INSTANCE = Mappers.getMapper(PaymentMapper.class);

    Payment toPayment(PaymentCreationRequest request);

    @Mapping(target = "dateEntered", source = "dateEntered", dateFormat = "yyyy-MM-dd HH:mm:ss")
    @Mapping(target = "paymentDate", source = "paymentDate", dateFormat = "yyyy-MM-dd HH:mm:ss")
    @Mapping(target = "contractId", source = "contract.id")
    @Mapping(target = "contractName", source = "contract.name")
    @Mapping(target = "value", source = "value", qualifiedByName = "formatToVND")
    @Mapping(target = "confirmedByName", source = "confirmedBy.name")
    PaymentResponse toPaymentResponse(Payment payment);

    @Named("formatToVND")
    static String formatToVND(long value) {
        return PaymentService.formatToVND(value);
    }
}
