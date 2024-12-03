package com.eteration.simplebanking.model;

import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("DepositTransaction")
@NoArgsConstructor
public class DepositTransaction extends Transaction {

    public DepositTransaction(double amount, BankAccount bankAccount) {
        super(amount, bankAccount);
    }

    @Override
    public void post() {
        getAccount().credit(getAmount());
    }
}
