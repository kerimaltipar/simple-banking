package com.eteration.simplebanking.model;

import com.eteration.simplebanking.exception.InsufficientBalanceException;
import com.eteration.simplebanking.model.base.Transaction;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "account")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BankAccount {

    private String owner;

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String accountNumber;

    private double balance;

    private final Date createDate = new Date();

    @OneToMany(mappedBy = "account")
    private Set<Transaction> transactions;

    public BankAccount(String owner, String accountNumber) {
        this.owner = owner;
        this.accountNumber = accountNumber;
    }

    public void credit(double amount) {
        if (amount > 0) {
            this.balance += amount;
        }
    }

    public void debit(double amount) {
        if (amount > 0 && this.balance >= amount) {
            this.balance -= amount;
        } else {
            throw new InsufficientBalanceException("You don't have enough money!");
        }
    }

}
