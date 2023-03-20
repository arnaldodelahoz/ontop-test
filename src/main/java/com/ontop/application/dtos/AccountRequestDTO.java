package com.ontop.application.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountRequestDTO {
    private String routingNumber;
    private String accountNumber;
    private String currency;
}
