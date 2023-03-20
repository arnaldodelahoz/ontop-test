package com.ontop.domain.repositories;

import com.ontop.domain.entities.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    Page<Transaction> findByAmountBetweenAndCreatedAtBetweenOrderByCreatedAtDesc(BigDecimal minAmount, BigDecimal maxAmount,
                                                                                 LocalDateTime startDate, LocalDateTime endDate, Pageable pageable);

}
