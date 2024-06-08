package com.javasproject.eventmanagement.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ContractUpdateRequest {
    LocalDate expirationDate;
    long discount;
    String terms;
    String status;
    LocalDateTime companySignedDate;
    LocalDateTime customerSignedDate;
    long sumPaid;
}
