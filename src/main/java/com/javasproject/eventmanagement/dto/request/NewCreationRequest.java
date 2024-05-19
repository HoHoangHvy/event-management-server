package com.javasproject.eventmanagement.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class NewCreationRequest {
    String name;
    String type;
    String content;
    LocalDate createDate;
}
