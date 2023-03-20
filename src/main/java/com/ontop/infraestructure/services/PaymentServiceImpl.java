package com.ontop.infraestructure.services;

import com.ontop.application.dtos.PaymentResponseDTO;
import com.ontop.application.dtos.RequestPaymentDTO;
import com.ontop.application.dtos.UpdatePaymentDTO;
import com.ontop.application.exceptions.AccountException;
import com.ontop.application.exceptions.PaymentException;
import com.ontop.application.mappers.AccountMapper;
import com.ontop.application.services.ApiService;
import com.ontop.application.services.PaymentService;
import com.ontop.domain.entities.Account;
import com.ontop.domain.entities.Payment;
import com.ontop.domain.entities.Transaction;
import com.ontop.domain.repositories.AccountRepository;
import com.ontop.domain.repositories.PaymentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class PaymentServiceImpl implements PaymentService {
     public static final String URL = "http://mockoon.tools.getontop.com:3000/api/v1/payments";
     AccountRepository accountRepository;
     AccountMapper accountMapper;
     ApiService apiService;
     PaymentRepository paymentRepository;

     @Autowired
     public PaymentServiceImpl(PaymentRepository paymentRepository,
                               ApiService apiService,
                               AccountMapper accountMapper,
                               AccountRepository accountRepository
                               ){
         this.paymentRepository = paymentRepository;
         this.apiService = apiService;
         this.accountMapper = accountMapper;
         this.accountRepository = accountRepository;
     }

     @Override
    public Payment createPayment(Transaction transaction) {
        Account companyAccount = accountRepository.findById(1L).orElseThrow(() -> new AccountException("Company's account is not configured yet"));
        Account userAccount = accountRepository.findFirstByUserId(transaction.getUserId()).orElseThrow(() -> new AccountException("User has not configured account yet"));
        Payment payment = new Payment();
        RequestPaymentDTO request = new RequestPaymentDTO();
        request.setAmount(transaction.getAmount().abs().intValue());
        request.setDestination(accountMapper.toDestinationDTO(userAccount));
        request.setSource(accountMapper.toSourceDTO(companyAccount));
        PaymentResponseDTO responseDTO = apiService.post(URL, request, PaymentResponseDTO.class);
        payment.setCode(responseDTO.getPaymentInfo().getId());
        payment.setTransaction(transaction);
        payment.setStatus(responseDTO.getRequestInfo().getStatus());
        payment.setDestination(userAccount);
        return paymentRepository.save(payment);
    }

    @Override
    public void updatePaymentStatusByCode(UpdatePaymentDTO dto) {
        int row = this.paymentRepository.updateStatusByCode(dto.getCode(), dto.getStatus());
        if(row == 0)
            throw new PaymentException("We could not found payment with code " + dto.getCode());
    }


}
