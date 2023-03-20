package com.ontop.application.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountDTO {
    private Long id;

    @JsonProperty("user_id")
    private Long userId;

    @JsonProperty("full_name")
    private String fullName;
    private String currency;
    @JsonProperty("account_number")
    private String accountNumber;
    @JsonProperty("routing_number")
    private String routingNumber;

    @JsonProperty("bank_name")
    private String bankName;
    private String dni;
}
