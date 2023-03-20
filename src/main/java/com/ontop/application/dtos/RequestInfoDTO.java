package com.ontop.application.dtos;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class RequestInfoDTO {
    private String status;
    private LocalDateTime date;
}
