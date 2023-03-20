package com.ontop.application.services;

import com.ontop.application.dtos.*;

public interface TransactionService {


    TransactionFilterResponseDTO getTransactions(TransactionFilterDTO filter);
    TransactionResponseDTO createTransaction(TransactionRequestDTO dto);
    BalanceDTO getBalanceByUserId(Long userId);
}



