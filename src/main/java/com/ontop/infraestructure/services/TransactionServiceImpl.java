package com.ontop.infraestructure.services;

import com.ontop.application.dtos.*;
import com.ontop.application.exceptions.AccountException;
import com.ontop.application.exceptions.InsufficientBalanceException;
import com.ontop.application.mappers.TransactionMapper;
import com.ontop.application.services.ApiService;
import com.ontop.application.services.PaymentService;
import com.ontop.application.services.TransactionService;
import com.ontop.domain.entities.*;
import com.ontop.domain.repositories.AccountRepository;
import com.ontop.domain.repositories.TransactionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

@Service
@Transactional
public class TransactionServiceImpl implements TransactionService {

    public static final String WALLET_URL = "http://mockoon.tools.getontop.com:3000/wallets/";

    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;

    private final ApiService apiService;
    private final AccountRepository accountRepository;
    private final PaymentService paymentService;

    @Autowired
    public TransactionServiceImpl(TransactionRepository transactionRepository,
                                  TransactionMapper transactionMapper,
                                  AccountRepository accountRepository,
                                  ApiService apiService,
                                  PaymentService paymentService
                                  ) {
        this.transactionRepository = transactionRepository;
        this.transactionMapper = transactionMapper;
        this.accountRepository = accountRepository;
        this.paymentService=paymentService;
        this.apiService = apiService;
    }

    @Override
    public TransactionFilterResponseDTO getTransactions(TransactionFilterDTO filter) {
        LocalDateTime startDate = filter.getStartDate();
        LocalDateTime endDate = filter.getEndDate();
        if (startDate != null && endDate != null && startDate.isAfter(endDate)) {
            throw new IllegalArgumentException("startDate can not be after endDate");
        }
        Sort sort = Sort.by("createdAt").descending();
        PageRequest pageable = PageRequest.of(filter.getPage(), filter.getSize(), sort);
        Page<Transaction> transactions = transactionRepository.findByAmountBetweenAndCreatedAtBetweenOrderByCreatedAtDesc(filter.getMinAmount(), filter.getMaxAmount(),startDate,endDate, pageable);
        TransactionFilterResponseDTO transactionFilterResponse =  new TransactionFilterResponseDTO();
        transactionFilterResponse.setData(transactions.getContent().stream().map(transactionMapper::transactionResponseDTO).collect(Collectors.toList()));
        transactionFilterResponse.setTotalPages(transactions.getTotalPages());
        transactionFilterResponse.setPage(filter.getPage());
        transactionFilterResponse.setSize(filter.getSize());
        return transactionFilterResponse;
    }

    @Override
    public TransactionResponseDTO createTransaction(TransactionRequestDTO dto){
        HttpEntity<TransactionRequestDTO> requestEntity = new HttpEntity<>(dto);
        try{
            boolean isWithdraw = dto.getAmount()<0;
            if (isWithdraw){
                if(Boolean.FALSE.equals(accountRepository.existsByUserId(dto.getUser_id()))) throw new AccountException("User has not configured a bank account");
                BalanceDTO balance = this.getBalanceByUserId(dto.getUser_id());
                if(balance.getBalance().add(new BigDecimal(dto.getAmount())).compareTo(BigDecimal.ZERO)<0)
                    throw new InsufficientBalanceException("Insufficient balance in wallet");
            }
            TransactionResponseDTO responseDTO = apiService.post(WALLET_URL+"transactions",requestEntity,TransactionResponseDTO.class);
            Transaction transaction = this.transactionRepository.save(transactionMapper.toEntity(responseDTO));
            transaction.setPayment(paymentService.createPayment(transaction));
            return transactionMapper.transactionResponseDTO(transaction);
        } catch (AccountException e) {
            throw new AccountException("Error creating transaction: " + e.getMessage());
        } catch (InsufficientBalanceException e) {
            throw new InsufficientBalanceException("Error creating transaction: " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException("Error creating transaction: " + e.getMessage(), e);
        }
    }

    @Override
    public BalanceDTO getBalanceByUserId(Long userId) {
        try{
            BalanceDTO balanceDTO = apiService.get(WALLET_URL+"balance?user_id="+userId, BalanceDTO.class);//consultar balance.
            return balanceDTO;
        }
        catch(Exception e){
            throw new RuntimeException(e);
        }
    }


}