package com.ontop.domain.entities;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "payments")
public class Payment extends ParentEntity{

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "destination_id", referencedColumnName = "id")
    private Account destination;

    @OneToOne
    @JoinColumn(name = "transaction_id", referencedColumnName = "id")
    private Transaction transaction;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name="code",nullable = false)
    private UUID code;

    public Long getDestination() {
        return destination.getId();
    }

    public void setDestination(Account destination) {
        this.destination = destination;
    }

    public Long getTransactionId() {
        return transaction.getId();
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public UUID getCode() {
        return code;
    }

    public void setCode(UUID code) {
        this.code = code;
    }
}
