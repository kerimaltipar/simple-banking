package com.eteration.simplebanking.services;

import com.eteration.simplebanking.dto.BankAccountDTO;
import com.eteration.simplebanking.dto.TransactionRequestDTO;
import com.eteration.simplebanking.dto.TransactionResponseDTO;
import com.eteration.simplebanking.exception.InsufficientBalanceException;

public interface BankAccountService {

    BankAccountDTO getBankAccountDTOByNumber(String accountNumber);

    TransactionResponseDTO processWithdrawal(String accountNumber, TransactionRequestDTO requestDTO) throws InsufficientBalanceException;

    TransactionResponseDTO processDeposit(String accountNumber, TransactionRequestDTO requestDTO);
}
