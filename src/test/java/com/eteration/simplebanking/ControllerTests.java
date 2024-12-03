package com.eteration.simplebanking;

import com.eteration.simplebanking.controller.BankAccountController;
import com.eteration.simplebanking.services.BankAccountService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration
@AutoConfigureMockMvc
class ControllerTests {

    @Spy
    @InjectMocks
    private BankAccountController controller;

    @Mock
    private BankAccountService service;


/*    @Test
    void givenId_Credit_thenReturnJson() throws Exception {

        Account account = new Account("Kerem Karaca", "17892");

        doReturn(account).when(service).findAccount("17892");
        ResponseEntity<TransactionStatus> result = controller.credit("17892", new DepositTransaction(1000.0));
        verify(service, times(1)).findAccount("17892");
        assertEquals("OK", result.getBody().getStatus());
    }

    @Test
    void givenId_CreditAndThenDebit_thenReturnJson() throws Exception {

        Account account = new Account("Kerem Karaca", "17892");

        doReturn(account).when(service).findAccount("17892");
        ResponseEntity<TransactionStatus> result = controller.credit("17892", new DepositTransaction(1000.0));
        ResponseEntity<TransactionStatus> result2 = controller.debit("17892", new WithdrawalTransaction(50.0));
        verify(service, times(2)).findAccount("17892");
        assertEquals("OK", result.getBody().getStatus());
        assertEquals("OK", result2.getBody().getStatus());
        assertEquals(950.0, account.getBalance(), 0.001);
    }

    @Test
    void givenId_CreditAndThenDebitMoreGetException_thenReturnJson() throws Exception {
        Assertions.assertThrows(InsufficientBalanceException.class, () -> {
            Account account = new Account("Kerem Karaca", "17892");

            doReturn(account).when(service).findAccount("17892");
            ResponseEntity<TransactionStatus> result = controller.credit("17892", new DepositTransaction(1000.0));
            assertEquals("OK", result.getBody().getStatus());
            assertEquals(1000.0, account.getBalance(), 0.001);
            verify(service, times(1)).findAccount("17892");

            ResponseEntity<TransactionStatus> result2 = controller.debit("17892", new WithdrawalTransaction(5000.0));
        });
    }

    @Test
    void givenId_GetAccount_thenReturnJson() throws Exception {

        Account account = new Account("Kerem Karaca", "17892");

        doReturn(account).when(service).findAccount("17892");
        ResponseEntity<Account> result = controller.getAccount("17892");
        verify(service, times(1)).findAccount("17892");
        assertEquals(account, result.getBody());
    }*/

}
