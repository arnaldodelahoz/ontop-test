package com.ontop.web.controllers;

import com.ontop.application.dtos.*;
import com.ontop.application.services.TransactionService;
import com.ontop.web.shared.DateUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class TransactionControllerTest {

    private MockMvc mockMvc;

    @Mock
    private TransactionService transactionService;

    @InjectMocks
    private TransactionController transactionController;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(transactionController).build();
    }

    @Test
    public void testGetBalance() throws Exception {
        Long userId = 1L;
        BalanceDTO mockBalanceDTO = new BalanceDTO();
        mockBalanceDTO.setUserId(userId);
        mockBalanceDTO.setBalance(BigDecimal.valueOf(1000));

        lenient().when(transactionService.getBalanceByUserId(userId)).thenReturn(mockBalanceDTO);

        mockMvc.perform(get("/transactions/balance")
                        .param("user_id", userId.toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted());
    }

    @Test
    public void testCreateTransaction() throws Exception {
        TransactionRequestDTO mockRequestDTO = new TransactionRequestDTO();
        mockRequestDTO.setUser_id(2L);
        mockRequestDTO.setAmount(100);

        TransactionResponseDTO mockResponseDTO = new TransactionResponseDTO();
        mockResponseDTO.setWalletTransactionId(1L);
        mockResponseDTO.setStatus("Processing");
        mockResponseDTO.setCode(UUID.randomUUID());
        mockResponseDTO.setAmount(BigDecimal.valueOf(100));
        mockResponseDTO.setDate(LocalDate.now());

       lenient().when(transactionService.createTransaction(mockRequestDTO)).thenReturn(mockResponseDTO);

        mockMvc.perform(post("/transactions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"user_id\":1,\"amount\":100}"))
                .andExpect(status().isCreated());

    }

    @Test
    public void testGetTransactions() throws Exception {
        TransactionFilterResponseDTO mockFilterResponseDTO = new TransactionFilterResponseDTO();
        TransactionFilterDTO filter = new TransactionFilterDTO();
        filter.setSize(5);
        filter.setPage(0);
        filter.setMinAmount(BigDecimal.ZERO);
        filter.setMaxAmount(BigDecimal.valueOf(Double.MAX_VALUE));
        filter.setStartDate(DateUtil.formatDate(null,DateUtil.START_DATE));
        filter.setEndDate(DateUtil.formatDate(null,DateUtil.END_DATE).withHour(23).withMinute(59));
        lenient().when(transactionService.getTransactions(filter))
                .thenReturn(mockFilterResponseDTO);

        mockMvc.perform(get("/transactions?min_amount=5&start_date=2023-03-15")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}