package com.eteration.simplebanking.services;

import com.eteration.simplebanking.dto.BankAccountDTO;
import com.eteration.simplebanking.dto.TransactionRequestDTO;
import com.eteration.simplebanking.dto.TransactionResponseDTO;

public interface BankAccountService {

    BankAccountDTO getBankAccountDTOByNumber(String accountNumber);

    TransactionResponseDTO processWithdrawal(String accountNumber, TransactionRequestDTO requestDTO);
    TransactionResponseDTO processDeposit(String accountNumber, TransactionRequestDTO requestDTO);
}
