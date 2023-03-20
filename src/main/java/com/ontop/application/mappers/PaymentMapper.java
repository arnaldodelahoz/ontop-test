package com.ontop.application.mappers;

import com.ontop.application.dtos.PaymentDTO;
import com.ontop.domain.entities.Payment;
import com.ontop.domain.entities.Transaction;
import org.springframework.stereotype.Component;


@Component
public class PaymentMapper {

    public PaymentDTO toDTO(Payment payment) {
        PaymentDTO dto = new PaymentDTO();
        dto.setId(payment.getId());
        dto.setTransactionId(payment.getTransactionId());
        dto.setCreatedAt(payment.getCreatedAt());
        dto.setUpdatedAt(payment.getUpdatedAt());
        return dto;
    }

    public Payment toEntity(PaymentDTO dto) {
        Payment payment = new Payment();
        if (dto.getId() != null) {
            payment.setId(dto.getId());
        }
        payment.setTransaction(new Transaction(dto.getTransactionId()));
        payment.setCreatedAt(dto.getCreatedAt());
        payment.setUpdatedAt(dto.getUpdatedAt());
        return payment;
    }
}
