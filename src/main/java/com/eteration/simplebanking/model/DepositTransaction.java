package com.eteration.simplebanking.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("DepositTransaction")
public class DepositTransaction extends Transaction {

    public DepositTransaction(double amount, BankAccount bankAccount) {
        super(amount, bankAccount);
    }

    @Override
    public void post() {
        getAccount().credit(getAmount());
    }
}
