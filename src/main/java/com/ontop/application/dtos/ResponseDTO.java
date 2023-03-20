package com.ontop.application.dtos;

import com.ontop.application.utils.ResponseCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseDTO {
    String message;
    ResponseCode code;

    public ResponseDTO(String message, ResponseCode code) {
        this.message = message;
        this.code = code;
    }


}


