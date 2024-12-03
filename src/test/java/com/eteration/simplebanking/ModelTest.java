package com.eteration.simplebanking;


import com.eteration.simplebanking.model.BankAccount;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ModelTest {

    @Test
    void testCreateAccountAndSetBalance0() {
        BankAccount bankAccount = new BankAccount("Kerem Karaca", "17892");
        assertEquals("Kerem Karaca", bankAccount.getOwner());
        assertEquals("17892", bankAccount.getAccountNumber());
        assertEquals(0, bankAccount.getBalance());
    }

   /* @Test
    void testDepositIntoBankAccount() {
        Account account = new Account("Demet Demircan", "9834");
        account.deposit(100);
        assertTrue(account.getBalance() == 100);
    }

    @Test
    void testWithdrawFromBankAccount() throws InsufficientBalanceException {
        Account account = new Account("Demet Demircan", "9834");
        account.deposit(100);
        assertTrue(account.getBalance() == 100);
        account.withdraw(50);
        assertTrue(account.getBalance() == 50);
    }

    @Test
    void testWithdrawException() {
        Assertions.assertThrows(InsufficientBalanceException.class, () -> {
            Account account = new Account("Demet Demircan", "9834");
            account.deposit(100);
            account.withdraw(500);
        });

    }

    @Test
    void testTransactions() throws InsufficientBalanceException {
        // Create account
        Account account = new Account("Canan Kaya", "1234");
        assertTrue(account.getTransactions().size() == 0);

        // Deposit Transaction
        DepositTransaction depositTrx = new DepositTransaction(100);
        assertTrue(depositTrx.getDate() != null);
        account.post(depositTrx);
        assertTrue(account.getBalance() == 100);
        assertTrue(account.getTransactions().size() == 1);

        // Withdrawal Transaction
        WithdrawalTransaction withdrawalTrx = new WithdrawalTransaction(60);
        assertTrue(withdrawalTrx.getDate() != null);
        account.post(withdrawalTrx);
        assertTrue(account.getBalance() == 40);
        assertTrue(account.getTransactions().size() == 2);
    }*/
}
