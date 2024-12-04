package com.eteration.simplebanking;


import com.eteration.simplebanking.exception.InsufficientBalanceException;
import com.eteration.simplebanking.model.BankAccount;
import com.eteration.simplebanking.model.DepositTransaction;
import com.eteration.simplebanking.model.PhoneBillPaymentTransaction;
import com.eteration.simplebanking.model.WithdrawalTransaction;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ModelTest {

    @Test
    void testCreateAccountAndSetBalance0() {
        BankAccount bankAccount = new BankAccount("Kerem Karaca", "17892");
        assertEquals("Kerem Karaca", bankAccount.getOwner());
        assertEquals("17892", bankAccount.getAccountNumber());
        assertEquals(0, bankAccount.getBalance());
    }

    @Test
    void testDepositIntoBankAccount() {
        BankAccount account = new BankAccount("Demet Demircan", "9834");
        DepositTransaction deposit = new DepositTransaction(100, account);
        deposit.post();
        assertEquals(100, account.getBalance());
    }

    @Test
    void testWithdrawFromBankAccount() {
        BankAccount account = new BankAccount("Demet Demircan", "9834");
        DepositTransaction deposit = new DepositTransaction(100, account);
        deposit.post();
        assertEquals(100, account.getBalance());
        WithdrawalTransaction withdrawal = new WithdrawalTransaction(50, account);
        withdrawal.post();
        assertEquals(50, account.getBalance());
    }

    @Test
    void testWithdrawException() {
        BankAccount account = new BankAccount("Demet Demircan", "9834");
        DepositTransaction deposit = new DepositTransaction(100, account);
        deposit.post();
        WithdrawalTransaction withdrawal = new WithdrawalTransaction(500, account);
        assertThrows(InsufficientBalanceException.class, withdrawal::post);

    }

    @Test
    void providedUnitTest() {
        BankAccount account = new BankAccount("Jim", "12345");
        DepositTransaction deposit = new DepositTransaction(1000, account);
        deposit.post();
        WithdrawalTransaction withdrawal = new WithdrawalTransaction(200, account);
        withdrawal.post();
        PhoneBillPaymentTransaction phoneBillPayment = new PhoneBillPaymentTransaction("Vodafone", "5423345566", 96.50, account);
        phoneBillPayment.post();
        assertEquals(703.50, account.getBalance(), 0.0001);
    }
}
