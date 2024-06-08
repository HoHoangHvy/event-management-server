package com.javasproject.eventmanagement.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ContractCreateResponse {
    String id;
    String name;
    String companySignedDate;
    String customerSignedDate;
    String expirationDate;
    String status;
    String totalValue;
    String netValue;
    String netTotalValue;
    String taxValue;
    double taxValueNumber;
    double netTotalValueNumber;
    double totalNumber;
    double discountNumber;
    String discount;
    String terms;
    String sumPaid;
    double sumPaidNumber;
    Boolean deleted;
    Boolean taxable;
    String dateEntered;
    String paymentTerm;
}
