package com.ontop.web.controllers;

import com.ontop.application.dtos.ResponseDTO;
import com.ontop.application.utils.ResponseCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ParentController {
    protected ResponseEntity<Object> handleException(Exception e) {
        ResponseEntity response;
        switch (e.getClass().getSimpleName()) {
            case "PaymentException":
                response = new ResponseEntity<>(new ResponseDTO(e.getMessage(),ResponseCode.PAYMENT_NOT_FOUND),HttpStatus.NOT_FOUND);
                break;
            case "FormatDateException":
                response = new ResponseEntity<>(new ResponseDTO(e.getMessage(),ResponseCode.INVALID_BODY),HttpStatus.BAD_REQUEST);
                break;
            case "AccountException":
                response = new ResponseEntity<>(new ResponseDTO(e.getMessage(), ResponseCode.ACCOUNT_NOT_CONFIGURED), HttpStatus.BAD_REQUEST);
                break;
            case "InsufficientBalanceException":
                response = new ResponseEntity<>(new ResponseDTO(e.getMessage(), ResponseCode.INSUFFICIENT_BALANCE), HttpStatus.BAD_REQUEST);
                break;
            case "ResourceNotFoundException":
                response = new ResponseEntity<>(new ResponseDTO(e.getMessage(), ResponseCode.INVALID_USER),HttpStatus.NOT_FOUND);
                break;
            default:
                response = new ResponseEntity<>(new ResponseDTO(e.getMessage(), ResponseCode.GENERIC_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
                break;
        }
        return response;
    }
}
