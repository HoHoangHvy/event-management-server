package com.javasproject.eventmanagement.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EventResponse {
    String id;
    String name;
    String description;
    String startDate;
    String endDate;
    String deleted;
    String status;
    String createdByName;
    String approvedByName;
    String customerName;
    String customerId;
    String hallId;
    String hallName;
    String contractName;
    String contractId;
    double totalAmount;
}
