package com.eteration.simplebanking.services.impl;

import com.eteration.simplebanking.dto.BankAccountDTO;
import com.eteration.simplebanking.dto.TransactionRequestDTO;
import com.eteration.simplebanking.dto.TransactionResponseDTO;
import com.eteration.simplebanking.exception.AccountNotFoundException;
import com.eteration.simplebanking.exception.NegativeAmountException;
import com.eteration.simplebanking.mapper.BankAccountMapper;
import com.eteration.simplebanking.model.BankAccount;
import com.eteration.simplebanking.model.DepositTransaction;
import com.eteration.simplebanking.model.WithdrawalTransaction;
import com.eteration.simplebanking.repository.BankAccountRepository;
import com.eteration.simplebanking.services.BankAccountService;
import com.eteration.simplebanking.services.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BankAccountServiceImpl implements BankAccountService {

    private final BankAccountRepository bankAccountRepository;

    private final TransactionService transactionService;

    private final BankAccountMapper bankAccountMapper;

    @Override
    public BankAccountDTO getBankAccountDTOByNumber(String accountNumber) {
        return bankAccountMapper.bankAccountToDTO(getBankAccountByNumber(accountNumber));
    }

    @Override
    public TransactionResponseDTO processDeposit(String accountNumber, TransactionRequestDTO requestDTO) {
        // If a throw exception here rather than credit method in BankAccount class
        // like I did in debit method in BankAccount class
        // It saves performance by not doing unnecessary db call
        // And I couldn't use javax validation because I choose to use the same request dto for both services
        validateAmount(requestDTO.getAmount(), "deposit");
        BankAccount account = getBankAccountByNumber(accountNumber);
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
    public TransactionResponseDTO processWithdrawal(String accountNumber, TransactionRequestDTO requestDTO) {
        validateAmount(requestDTO.getAmount(), "withdrawal");
        BankAccount account = getBankAccountByNumber(accountNumber);
        WithdrawalTransaction withdrawal = new WithdrawalTransaction(requestDTO.getAmount(), account);
        withdrawal.post();
        transactionService.save(withdrawal);
        bankAccountRepository.save(account);
        return TransactionResponseDTO.builder()
                .approvalCode(withdrawal.getApprovalCode())
                .status("OK")
                .build();
    }

    private BankAccount getBankAccountByNumber(String accountNumber) {
        Optional<BankAccount> bankAccountOptional = bankAccountRepository.findByAccountNumber(accountNumber);
        if (bankAccountOptional.isEmpty()) {
            throw new AccountNotFoundException("Account not found!");
        }
        return bankAccountOptional.get();
    }

    private void validateAmount(double amount, String processType) {
        if (amount <= 0) {
            throw new NegativeAmountException(String.format("You can't %s zero or negative amount!", processType));
        }
    }
}
