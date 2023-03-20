package com.ontop.web.controllers;

import com.ontop.application.dtos.ResponseDTO;
import com.ontop.application.dtos.UpdatePaymentDTO;
import com.ontop.application.services.PaymentService;
import com.ontop.application.utils.ResponseCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("payments")
public class PaymentController extends ParentController {

    @Autowired
    PaymentService paymentService;
    @PutMapping("/update-status")
    public ResponseEntity<Object> createPayment(@RequestBody UpdatePaymentDTO dto){
        try{
            this.paymentService.updatePaymentStatusByCode(dto);
            return new ResponseEntity<>(new ResponseDTO("Object modified sucessfully", ResponseCode.SUCCESS), HttpStatus.OK);
        }
        catch (Exception e){
            return handleException(e);
        }
    }
}
