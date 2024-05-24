package com.javasproject.eventmanagement.dto.response;

import com.javasproject.eventmanagement.entity.RequestDepartment;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


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
    LocalDateTime dateEnteredLocal;
    String status;
    String approveDate;
    Boolean deleted;
    String employeeName;
    String rejectReason = "";
    String approvedByName = "";
    List<RequestDepartmentListResponse> requestDepartments;
}
