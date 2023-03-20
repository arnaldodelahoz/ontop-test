package com.ontop.application.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
public class TransactionResponseDTO {
    @JsonProperty("wallet_transaction_id")
    private Long walletTransactionId;
    private BigDecimal amount;
    @JsonProperty("user_id")
    private Long userId;
    private UUID code;
    private String status;
    private LocalDate date;
}
