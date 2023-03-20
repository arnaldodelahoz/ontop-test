package com.ontop.web.controllers;

import com.ontop.application.dtos.AccountDTO;
import com.ontop.application.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("accounts")
public class AccountController extends  ParentController{

    @Autowired
    AccountService accountService;
    @PostMapping("")
    public ResponseEntity<Object> createAccount(@RequestBody AccountDTO accountDTO){
        try{
            return new ResponseEntity<>(this.accountService.createAccount(accountDTO), HttpStatus.CREATED);
        }catch (Exception e){
            return handleException(e);
        }
    }
}
