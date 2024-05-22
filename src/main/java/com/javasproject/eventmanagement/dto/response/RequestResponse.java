package com.javasproject.eventmanagement.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RequestResponse {
    String id;
    String name;
    String type;
    String content;
    String dateEntered;
    String status;
    String approveDate;
    Boolean deleted;
    String employeeName;
    String rejectReason = "";
    String approvedByName = "";

}
