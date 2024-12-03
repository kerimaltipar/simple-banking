package com.eteration.simplebanking.controller;

import com.eteration.simplebanking.dto.TransactionRequestDTO;
import com.eteration.simplebanking.dto.TransactionResponseDTO;
import com.eteration.simplebanking.exception.InsufficientBalanceException;
import com.eteration.simplebanking.model.BankAccount;
import com.eteration.simplebanking.services.BankAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/account")
public class BankAccountController {

    private final BankAccountService bankAccountService;

    @GetMapping("v1/{accountNumber}")
    public BankAccount getAccount(@PathVariable String accountNumber) {
        return bankAccountService.getAccountByNumber(accountNumber);
    }

    @PostMapping("v1/credit/{accountNumber}")
    public TransactionResponseDTO credit(@PathVariable String accountNumber, @RequestBody TransactionRequestDTO requestDTO) {
        return bankAccountService.processDeposit(accountNumber, requestDTO);
    }

    @PostMapping("v1/debit/{accountNumber}")
    public TransactionResponseDTO debit(@PathVariable String accountNumber, @RequestBody TransactionRequestDTO requestDTO) throws InsufficientBalanceException {
        return bankAccountService.processWithdrawal(accountNumber, requestDTO);
    }

}