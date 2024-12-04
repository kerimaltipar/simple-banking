package com.eteration.simplebanking.model.base;

import com.eteration.simplebanking.model.BankAccount;

import javax.persistence.Entity;

@Entity
public abstract class BillPaymentTransaction extends Transaction {

    protected BillPaymentTransaction(double amount, BankAccount bankAccount) {
        super(amount, bankAccount);
    }

}