package com.eteration.simplebanking.services.impl;

import com.eteration.simplebanking.dto.TransactionRequestDTO;
import com.eteration.simplebanking.dto.TransactionResponseDTO;
import com.eteration.simplebanking.exception.InsufficientBalanceException;
import com.eteration.simplebanking.model.BankAccount;
import com.eteration.simplebanking.model.DepositTransaction;
import com.eteration.simplebanking.model.WithdrawalTransaction;
import com.eteration.simplebanking.repository.BankAccountRepository;
import com.eteration.simplebanking.services.BankAccountService;
import com.eteration.simplebanking.services.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BankAccountServiceImpl implements BankAccountService {

    private final BankAccountRepository bankAccountRepository;

    private final TransactionService transactionService;

    @Override
    public BankAccount getAccountByNumber(String accountNumber) {
        return bankAccountRepository.findByAccountNumber(accountNumber);
    }

    @Override
    public TransactionResponseDTO processDeposit(String accountNumber, TransactionRequestDTO requestDTO) {
        BankAccount account = getAccountByNumber(accountNumber);
        DepositTransaction deposit = new DepositTransaction(requestDTO.getAmount(), account);
        deposit.post();
        transactionService.save(deposit);
        bankAccountRepository.save(account);
        return TransactionResponseDTO.builder()
                .approvalCode(deposit.getApprovalCode())
                .status("OK")
                .build();
    }

    @Override
    public TransactionResponseDTO processWithdrawal(String accountNumber, TransactionRequestDTO requestDTO) throws InsufficientBalanceException {
        BankAccount account = getAccountByNumber(accountNumber);
        WithdrawalTransaction withdrawal = new WithdrawalTransaction(requestDTO.getAmount(), account);
        withdrawal.post();
        transactionService.save(withdrawal);
        bankAccountRepository.save(account);
        return TransactionResponseDTO.builder()
                .approvalCode(withdrawal.getApprovalCode())
                .status("OK")
                .build();
    }
}
