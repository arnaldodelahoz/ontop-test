package com.ontop.web.controllers;

import com.ontop.application.dtos.*;
import com.ontop.application.exceptions.InsufficientBalanceException;
import com.ontop.application.services.TransactionService;
import com.ontop.web.shared.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;


@RestController
@RequestMapping("transactions")
public class TransactionController extends ParentController {
    @Autowired
    TransactionService transactionService;

    @GetMapping("balance")
    public ResponseEntity<Object> getBalance(@RequestParam("user_id") Long userId){
        try {
            BalanceDTO balance = this.transactionService.getBalanceByUserId(userId);
            return new ResponseEntity<>(balance, HttpStatus.ACCEPTED);
        }catch (Exception e){
            return  handleException(e);
        }
    }
    @PostMapping("")
    @ExceptionHandler({InsufficientBalanceException.class})
    public ResponseEntity<Object> transaction(@RequestBody TransactionRequestDTO dto) {
        try{
            TransactionResponseDTO response = this.transactionService.createTransaction(dto);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            return handleException(e);
        }
    }

    @GetMapping("")
    public ResponseEntity<Object> transactions(
            @RequestParam(name = "start_date", required = false) String startDateStr,
            @RequestParam(name = "end_date", required = false) String endDateStr,
            @RequestParam(name = "min_amount", required = false) BigDecimal minAmount,
            @RequestParam(name = "max_amount", required = false) BigDecimal maxAmount,
            @RequestParam(name="page",required = false) Integer page,
            @RequestParam(name="size", required = false) Integer size
    ){
        try{
            TransactionFilterDTO filter = new TransactionFilterDTO();
            filter.setSize(size!=null?size:5);
            filter.setPage(page!=null?page:0);
            filter.setMinAmount(minAmount != null ? minAmount : BigDecimal.ZERO);
            filter.setMaxAmount(maxAmount != null ? maxAmount : BigDecimal.valueOf(Double.MAX_VALUE));
            filter.setStartDate(DateUtil.formatDate(startDateStr,DateUtil.START_DATE));
            filter.setEndDate(DateUtil.formatDate(endDateStr,DateUtil.END_DATE).withHour(23).withMinute(59));
            TransactionFilterResponseDTO transactions = transactionService.getTransactions(filter);
            return new ResponseEntity<>(transactions,HttpStatus.OK);
        }
        catch (Exception e){
            return handleException(e);
        }
    }

}
