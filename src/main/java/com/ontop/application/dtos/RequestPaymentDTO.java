package com.ontop.application.dtos;


import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class RequestPaymentDTO {
    private SourceDTO source;
    private DestinationDTO destination;

    private Integer amount;
}
