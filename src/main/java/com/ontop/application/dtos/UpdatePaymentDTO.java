package com.ontop.application.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UpdatePaymentDTO {
    private UUID code;
    private String status;

    public UpdatePaymentDTO(UUID code, String status){
        this.status = status;
        this.code = code;
    }
    public UpdatePaymentDTO(){}
}
