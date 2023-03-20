package com.ontop.infraestructure.services;

import com.ontop.application.dtos.*;
import com.ontop.application.exceptions.PaymentException;
import com.ontop.application.mappers.AccountMapper;
import com.ontop.application.services.ApiService;
import com.ontop.domain.entities.Transaction;
import com.ontop.domain.repositories.AccountRepository;
import com.ontop.domain.repositories.PaymentRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class PaymentServiceImplTest {
    @Mock
    private PaymentRepository paymentRepository;

    @Mock
    private ApiService apiService;

    @Mock
    private AccountMapper accountMapper;

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private PaymentServiceImpl paymentService;

    private Transaction transaction;

    @BeforeEach
    public void setUp() {
        transaction = new Transaction();
        transaction.setAmount(BigDecimal.TEN);
        transaction.setUserId(1L);
    }
    @Test
    public void updatePaymentStatusByCode_shouldThrowException_whenPaymentNotFound() {
        UUID code = UUID.randomUUID();
        String status = "approved";
        when(paymentRepository.updateStatusByCode(code, status)).thenReturn(0);

        Assertions.assertThrows(PaymentException.class, () -> paymentService.updatePaymentStatusByCode(new UpdatePaymentDTO(code, status)));
    }

    @Test
    public void updatePaymentStatusByCode_shouldUpdateStatus_whenPaymentFound() {
        UUID code = UUID.randomUUID();
        String status = "Processing";
        when(paymentRepository.updateStatusByCode(code, status)).thenReturn(1);

        paymentService.updatePaymentStatusByCode(new UpdatePaymentDTO(code, status));

        // Verify that the paymentRepository was called with the correct arguments
        verify(paymentRepository, times(1)).updateStatusByCode(code, status);
    }
}