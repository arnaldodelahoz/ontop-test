package com.ontop.application.dtos;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PaymentDTO {
    private Long id;
    private Long sourceId;
    private Long destinationId;
    private Long transactionId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
