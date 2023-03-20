package com.ontop.application.dtos;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class TransactionDTO  {
    private Long id;
    private BigDecimal amount;

    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
