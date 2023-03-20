package com.ontop.infraestructure.services;

import com.ontop.application.dtos.BalanceDTO;
import com.ontop.application.dtos.TransactionRequestDTO;
import com.ontop.application.dtos.TransactionResponseDTO;
import com.ontop.application.exceptions.AccountException;
import com.ontop.application.exceptions.InsufficientBalanceException;
import com.ontop.application.mappers.TransactionMapper;
import com.ontop.application.services.ApiService;
import com.ontop.application.services.PaymentService;
import com.ontop.domain.entities.Payment;
import com.ontop.domain.entities.Transaction;
import com.ontop.domain.repositories.AccountRepository;
import com.ontop.domain.repositories.TransactionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpEntity;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)

class TransactionServiceImplTest {
    @Mock
    private TransactionRepository transactionRepository;
    @Mock
    private TransactionMapper transactionMapper;
    @Mock
    private AccountRepository accountRepository;
    @Mock
    private ApiService apiService;
    @Mock
    private PaymentService paymentService;

    @InjectMocks
    private TransactionServiceImpl transactionService;

    @Test
    void testCreateTransaction() {
        UUID code = UUID.randomUUID();
        TransactionRequestDTO requestDto = new TransactionRequestDTO();
        requestDto.setUser_id(1L);
        requestDto.setAmount(100);
        TransactionResponseDTO responseDto = new TransactionResponseDTO();
        responseDto.setCode(code);
        when(apiService.post(ArgumentMatchers.anyString(), ArgumentMatchers.any(HttpEntity.class), ArgumentMatchers.any(Class.class))).thenReturn(responseDto);
        Transaction transaction = new Transaction();
        when(transactionMapper.toEntity(responseDto)).thenReturn(transaction);
        when(transactionRepository.save(transaction)).thenReturn(transaction);
        Payment payment = new Payment();
        when(paymentService.createPayment(transaction)).thenReturn(payment);
        TransactionResponseDTO expectedDto = new TransactionResponseDTO();
        expectedDto.setCode(code);
        when(transactionMapper.transactionResponseDTO(transaction)).thenReturn(expectedDto);

        TransactionResponseDTO actualDto = transactionService.createTransaction(requestDto);

        assertEquals(expectedDto, actualDto);
        verify(apiService).post(ArgumentMatchers.anyString(), ArgumentMatchers.any(HttpEntity.class), ArgumentMatchers.any(Class.class));
        verify(transactionRepository).save(transaction);
        verify(paymentService).createPayment(transaction);
        verify(transactionMapper).toEntity(responseDto);
        verify(transactionMapper).transactionResponseDTO(transaction);
    }
    @Test
    void testCreateTransaction_WithdrawalWithoutBankAccount() {
        TransactionRequestDTO requestDto = new TransactionRequestDTO();
        requestDto.setUser_id(1L);
        requestDto.setAmount(-100);
        when(accountRepository.existsByUserId(ArgumentMatchers.anyLong())).thenReturn(false);
        assertThrows(AccountException.class, () -> {
            transactionService.createTransaction(requestDto);
        });
    }

    @Test
    void testCreateTransaction_WithdrawalWithInsufficientBalance() {
        TransactionRequestDTO requestDto = new TransactionRequestDTO();
        requestDto.setUser_id(1L);
        requestDto.setAmount(-100);
        BalanceDTO balanceDto = new BalanceDTO();
        balanceDto.setUserId(1L);
        balanceDto.setBalance(new BigDecimal(50));
        when(accountRepository.existsByUserId(ArgumentMatchers.anyLong())).thenReturn(true);
        when(apiService.get(ArgumentMatchers.anyString(), ArgumentMatchers.any(Class.class))).thenReturn(balanceDto);

        assertThrows(InsufficientBalanceException.class, () -> {
            transactionService.createTransaction(requestDto);
        });
    }
}