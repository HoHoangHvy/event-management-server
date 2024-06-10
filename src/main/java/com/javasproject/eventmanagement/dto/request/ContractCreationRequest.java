package com.javasproject.eventmanagement.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ContractCreationRequest {
    String eventId;
    LocalDate expirationDate;
    double discount;
    Boolean taxable;
    String terms;
    String paymentTerm;
}
