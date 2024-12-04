package com.eteration.simplebanking.model;

import com.eteration.simplebanking.model.base.BillPaymentTransaction;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

@Entity
@DiscriminatorValue("PhoneBillPaymentTransaction")
@EqualsAndHashCode(callSuper = false)
@Getter
@Setter
public class PhoneBillPaymentTransaction extends BillPaymentTransaction {

    @Transient
    private String payee;

    @Transient
    private String phoneNumber;

    public PhoneBillPaymentTransaction(String payee, String phoneNumber, double amount, BankAccount bankAccount) {
        super(amount, bankAccount);
        this.payee = payee;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public void post() {
        getAccount().debit(getAmount());
    }
}