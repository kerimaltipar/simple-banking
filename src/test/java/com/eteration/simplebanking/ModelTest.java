package com.eteration.simplebanking;


import com.eteration.simplebanking.exception.InsufficientBalanceException;
import com.eteration.simplebanking.model.BankAccount;
import com.eteration.simplebanking.model.DepositTransaction;
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
        assertThrows(InsufficientBalanceException.class, () -> {
            BankAccount account = new BankAccount("Demet Demircan", "9834");
            DepositTransaction deposit = new DepositTransaction(100, account);
            deposit.post();
            WithdrawalTransaction withdrawal = new WithdrawalTransaction(500, account);
            withdrawal.post();
        });

    }

//    @Test
//    void testTransactions() throws InsufficientBalanceException {
//        // Create account
//        BankAccount account = new BankAccount("Canan Kaya", "1234");
//        account.setTransactions(new HashSet<>());
//        assertEquals(0, account.getTransactions().size());
//
//        // Deposit Transaction
//        DepositTransaction depositTrx = new DepositTransaction(100, account);
//        assertNotNull(depositTrx.getDate());
//        depositTrx.post();
//        assertEquals(100, account.getBalance());
//        assertEquals(1, account.getTransactions().size());
//
//        // Withdrawal Transaction
//        WithdrawalTransaction withdrawalTrx = new WithdrawalTransaction(60, account);
//        assertNotNull(withdrawalTrx.getDate());
//        withdrawalTrx.post();
//        assertEquals(40, account.getBalance());
//        assertEquals(2, account.getTransactions().size());
//    }
}
