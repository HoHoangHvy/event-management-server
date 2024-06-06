package com.javasproject.eventmanagement.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class NewCreationRequest {
    String name;
    String type;
    String content;
    LocalDateTime createDate;
}
