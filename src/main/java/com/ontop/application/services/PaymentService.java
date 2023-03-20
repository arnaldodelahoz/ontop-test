package com.ontop.application.services;

import com.ontop.application.dtos.UpdatePaymentDTO;
import com.ontop.domain.entities.Payment;
import com.ontop.domain.entities.Transaction;



public interface PaymentService {
    Payment createPayment(Transaction transaction);
    void updatePaymentStatusByCode(UpdatePaymentDTO dto);

}
