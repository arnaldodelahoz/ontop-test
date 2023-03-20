package com.ontop.domain.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "accounts")
@Getter
@Setter
public class Account extends ParentEntity {

    @Transient
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "user_id")
    private Long userId;
    @Column(name = "full_name")
    private String fullName;
    @Column(name = "account_number")
    private String accountNumber;

    @Column(name = "routing_number")
    private String routingNumber;

    @Column(name = "bank_name")
    private String bankName;

    @Column(name = "dni")
    private String dni;

    @Column(name = "currency")
    private String currency;

    public Account(){}
    public Account(Long id) {
        this.id = id;
    }
}
