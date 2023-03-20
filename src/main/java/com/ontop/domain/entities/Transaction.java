package com.ontop.domain.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "transactions")
public class Transaction extends ParentEntity{

    public static final BigDecimal FEE = BigDecimal.valueOf(0.1);

    @Column(name="user_id")
    private Long userId;
    @Column(nullable = false)
    private BigDecimal amount;

    @Column(name = "wallet_transaction_id")
    private Long walletTransactionId;
    @Column(nullable = false)
    private BigDecimal fee;
    @OneToOne(mappedBy = "transaction",fetch = FetchType.EAGER)
    private Payment payment;

    public Transaction(){}
    public Transaction(Long id) {
        super.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Long getWalletTransactionId() {
        return walletTransactionId;
    }

    public void setWalletTransactionId(Long walletTransactionId) {
        this.walletTransactionId = walletTransactionId;
    }

    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }
}

