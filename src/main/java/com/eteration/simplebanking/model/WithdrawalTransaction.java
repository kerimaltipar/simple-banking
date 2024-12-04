package com.eteration.simplebanking.model;

import com.eteration.simplebanking.model.base.Transaction;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("WithdrawalTransaction")
@NoArgsConstructor
public class WithdrawalTransaction extends Transaction {

    public WithdrawalTransaction(double amount, BankAccount bankAccount) {
        super(amount, bankAccount);
    }

    @Override
    public void post() {
        getAccount().debit(getAmount());
    }
}


