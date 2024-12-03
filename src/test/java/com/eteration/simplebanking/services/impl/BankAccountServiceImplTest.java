package com.eteration.simplebanking.services.impl;

import com.eteration.simplebanking.dto.BankAccountDTO;
import com.eteration.simplebanking.dto.TransactionRequestDTO;
import com.eteration.simplebanking.dto.TransactionResponseDTO;
import com.eteration.simplebanking.exception.InsufficientBalanceException;
import com.eteration.simplebanking.exception.NegativeAmountException;
import com.eteration.simplebanking.mapper.BankAccountMapper;
import com.eteration.simplebanking.model.BankAccount;
import com.eteration.simplebanking.repository.BankAccountRepository;
import com.eteration.simplebanking.services.TransactionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BankAccountServiceImplTest {

    @Mock
    private BankAccountRepository bankAccountRepository;

    @Mock
    private TransactionService transactionService;

    @Mock
    private BankAccountMapper bankAccountMapper;

    @InjectMocks
    private BankAccountServiceImpl bankAccountService;

    @Test
    void getBankAccountDTOByNumber_when_accountNumberValid_then_returnBankAccountDTO() {
        String accountNumber = "123-4567";
        BankAccount bankAccount = new BankAccount();
        BankAccountDTO expected = new BankAccountDTO();

        when(bankAccountRepository.findByAccountNumber(accountNumber)).thenReturn(bankAccount);
        when(bankAccountMapper.bankAccountToDTO(bankAccount)).thenReturn(expected);

        BankAccountDTO actual = bankAccountService.getBankAccountDTOByNumber(accountNumber);

        assertEquals(expected, actual);
    }

    @Test
    void processDeposit_when_accountNumberAndAmountValid_then_returnTransactionResponseDTO() {
        String accountNumber = "123-4567";
        TransactionRequestDTO requestDTO = new TransactionRequestDTO();
        requestDTO.setAmount(100.0);
        BankAccount bankAccount = new BankAccount();
        TransactionResponseDTO expected = TransactionResponseDTO.builder()
                .status("OK")
                .build();

        when(bankAccountRepository.findByAccountNumber(accountNumber)).thenReturn(bankAccount);

        TransactionResponseDTO actual = bankAccountService.processDeposit(accountNumber, requestDTO);

        assertEquals(expected, actual);
    }

    @Test
    void processDeposit_when_amountNotValid_then_throwNegativeAmountException() {
        String accountNumber = "123-4567";
        TransactionRequestDTO requestDTO = new TransactionRequestDTO();
        requestDTO.setAmount(-100.0);
        String expectedErrorMessage = "You can't deposit zero or negative amount!";

        NegativeAmountException exception = assertThrows(NegativeAmountException.class,
                () -> bankAccountService.processDeposit(accountNumber, requestDTO));

        assertEquals(expectedErrorMessage, exception.getMessage());
    }

    @Test
    void processWithdrawal_when_accountNumberAndAmountValid_then_returnTransactionResponseDTO() {
        String accountNumber = "123-4567";
        TransactionRequestDTO requestDTO = new TransactionRequestDTO();
        requestDTO.setAmount(150.0);
        BankAccount bankAccount = new BankAccount();
        bankAccount.setBalance(200.0);
        TransactionResponseDTO expected = TransactionResponseDTO.builder()
                .status("OK")
                .build();

        when(bankAccountRepository.findByAccountNumber(accountNumber)).thenReturn(bankAccount);

        TransactionResponseDTO actual = bankAccountService.processWithdrawal(accountNumber, requestDTO);

        assertEquals(expected, actual);
    }

    @Test
    void processWithdrawal_when_balanceBelowAmount_then_throwsInsufficientBalanceException() {
        String accountNumber = "123-4567";
        TransactionRequestDTO requestDTO = new TransactionRequestDTO();
        requestDTO.setAmount(350.0);
        BankAccount bankAccount = new BankAccount();
        bankAccount.setBalance(200.0);
        String expectedErrorMessage = "You don't have enough money!";

        when(bankAccountRepository.findByAccountNumber(accountNumber)).thenReturn(bankAccount);

        InsufficientBalanceException exception = assertThrows(InsufficientBalanceException.class,
                () -> bankAccountService.processWithdrawal(accountNumber, requestDTO));

        assertEquals(expectedErrorMessage, exception.getMessage());
    }
}