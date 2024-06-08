package com.javasproject.eventmanagement.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PaymentResponse {
    String id;
    String name;
    String type;
    String status;
    String paymentDate;
    String paymentMethod;
    Boolean deleted;
    String dateEntered;
    String value;
    String contractId;
    String contractName;
    String confirmedByName;
}
