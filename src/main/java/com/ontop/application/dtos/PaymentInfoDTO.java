package com.ontop.application.dtos;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
public class PaymentInfoDTO {
    private UUID id;
    private BigDecimal amount;
}
