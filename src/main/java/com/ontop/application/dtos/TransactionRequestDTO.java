package com.ontop.application.dtos;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class TransactionRequestDTO {

    private Integer amount;

    private Long user_id;
}
