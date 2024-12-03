package com.eteration.simplebanking.model;

import com.eteration.simplebanking.exception.InsufficientBalanceException;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "transaction")
@Data
@NoArgsConstructor
public abstract class Transaction {

    @Id
    @GeneratedValue(generator = "uuid")
    private String approvalCode;

    private Date date;

    private double amount;

    @ManyToOne
    @JoinColumn(name = "account_account_number", nullable = false)
    private BankAccount account;

    protected Transaction(double amount, BankAccount account) {
        this.amount = amount;
        this.account = account;
        this.date = new Date();
    }

    public abstract void post() throws InsufficientBalanceException;
}
