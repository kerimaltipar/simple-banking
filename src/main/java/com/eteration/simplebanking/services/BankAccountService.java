package com.eteration.simplebanking.services;

import com.eteration.simplebanking.dto.TransactionRequestDTO;
import com.eteration.simplebanking.dto.TransactionResponseDTO;
import com.eteration.simplebanking.exception.InsufficientBalanceException;
import com.eteration.simplebanking.model.BankAccount;

public interface BankAccountService {

    BankAccount getAccountByNumber(String accountNumber);

    TransactionResponseDTO processWithdrawal(String accountNumber, TransactionRequestDTO requestDTO) throws InsufficientBalanceException;

    TransactionResponseDTO processDeposit(String accountNumber, TransactionRequestDTO requestDTO);
}
