package com.javasproject.eventmanagement.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL) //Use to ignore null value in response
public class ApiResponse<T> {
    @Builder.Default
    int code = 100;
    String message;
    T data;
}
