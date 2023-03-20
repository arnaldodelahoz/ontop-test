package com.ontop.application.dtos;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class TransactionFilterDTO {
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private BigDecimal minAmount;
    private BigDecimal maxAmount;
    private Integer page;
    private Integer size;
}
