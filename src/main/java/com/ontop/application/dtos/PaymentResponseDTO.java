package com.ontop.application.dtos;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentResponseDTO {

    RequestInfoDTO requestInfo;
    PaymentInfoDTO paymentInfo;
}
