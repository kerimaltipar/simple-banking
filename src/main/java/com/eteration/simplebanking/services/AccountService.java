package com.eteration.simplebanking.services;

import com.eteration.simplebanking.dto.TransactionRequestDTO;
import com.eteration.simplebanking.dto.TransactionResponseDTO;
import com.eteration.simplebanking.model.Account;

public interface AccountService {

    Account account(String accountNumber);

    TransactionResponseDTO credit(String accountNumber, TransactionRequestDTO requestDTO);

    TransactionResponseDTO debit(String accountNumber, TransactionRequestDTO requestDTO);
}
