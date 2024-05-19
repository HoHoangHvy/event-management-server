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
public class RequestCreationRequest {
    String name;
    String type;
    String content;
    LocalDate dateEntered;
    String status;
    LocalDate approveDate;
    String rejectReason;
    Boolean deleted = false;
}
