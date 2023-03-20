package com.ontop.application.mappers;

import com.ontop.application.dtos.TransactionDTO;
import com.ontop.application.dtos.TransactionResponseDTO;
import com.ontop.domain.entities.Transaction;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class TransactionMapper {


    public TransactionDTO toDTO(Transaction entity) {
        TransactionDTO dto = new TransactionDTO();
        dto.setId(entity.getId());
        dto.setAmount(entity.getAmount());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setUpdatedAt(entity.getUpdatedAt());
        return dto;
    }

    public Transaction toEntity(TransactionDTO dto) {
        Transaction entity = new Transaction();
        entity.setId(dto.getId());
        entity.setAmount(dto.getAmount());
        entity.setCreatedAt(dto.getCreatedAt());
        entity.setUpdatedAt(dto.getUpdatedAt());
        return entity;
    }

    public TransactionResponseDTO transactionResponseDTO(Transaction entity){
        TransactionResponseDTO dto = new TransactionResponseDTO();
        dto.setAmount(entity.getAmount());
        dto.setWalletTransactionId(entity.getWalletTransactionId());
        dto.setUserId(entity.getUserId());
        dto.setDate(entity.getCreatedAt().toLocalDate());
        if(entity.getPayment()!= null) {
            dto.setCode(entity.getPayment().getCode());
            dto.setStatus(entity.getPayment().getStatus());
        }
        return dto;
    }
    public Transaction toEntity(TransactionResponseDTO dto) {
        Transaction entity = new Transaction();
        BigDecimal fee = BigDecimal.ZERO;
        if(dto.getAmount().compareTo(BigDecimal.ZERO)<0){
            fee = dto.getAmount().multiply(Transaction.FEE).abs();
        }
        entity.setAmount(dto.getAmount());
        entity.setFee(fee);
        entity.setWalletTransactionId(dto.getWalletTransactionId());
        entity.setUserId(dto.getUserId());
        return entity;
    }
}