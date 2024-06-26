package com.javasproject.eventmanagement.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL) //Use to ignore null value in response
public class ApiResponse<T> {
    @Builder.Default
    int code = 100;
    @Builder.Default
    boolean success = true;
    String message;
    T data;
    List angularState ;

}
