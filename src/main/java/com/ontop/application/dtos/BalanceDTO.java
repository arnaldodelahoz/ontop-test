package com.ontop.application.dtos;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class BalanceDTO {
    private Long userId;
    private BigDecimal balance;


}
