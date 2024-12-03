package com.eteration.simplebanking.model;

import com.eteration.simplebanking.exception.InsufficientBalanceException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
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

    public void debit(double amount) throws InsufficientBalanceException {
        if (amount > 0 && this.balance >= amount) {
            this.balance -= amount;
        } else {
            throw new InsufficientBalanceException("You don't have enough money!");
        }
    }

}
