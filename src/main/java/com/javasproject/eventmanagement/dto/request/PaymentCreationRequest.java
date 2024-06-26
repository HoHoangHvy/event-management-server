package com.javasproject.eventmanagement.dto.request;

import com.javasproject.eventmanagement.entity.Contract;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PaymentCreationRequest {
    Contract contract;
    String type;
    double value;
}
