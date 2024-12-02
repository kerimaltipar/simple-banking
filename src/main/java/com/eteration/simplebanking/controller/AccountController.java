package com.eteration.simplebanking.controller;

import com.eteration.simplebanking.dto.TransactionRequestDTO;
import com.eteration.simplebanking.dto.TransactionResponseDTO;
import com.eteration.simplebanking.model.Account;
import com.eteration.simplebanking.services.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/account")
public class AccountController {

    private final AccountService accountService;

    @GetMapping("v1/{accountNumber}")
    public Account account(@PathVariable String accountNumber) {
        return accountService.account(accountNumber);
    }

    @PostMapping("v1/credit/{accountNumber}")
    public TransactionResponseDTO credit(@PathVariable String accountNumber, @RequestBody TransactionRequestDTO requestDTO) {
        return accountService.credit(accountNumber, requestDTO);
    }

    @PostMapping("v1/debit/{accountNumber}")
    public TransactionResponseDTO debit(@PathVariable String accountNumber, @RequestBody TransactionRequestDTO requestDTO) {
        return accountService.debit(accountNumber, requestDTO);
    }

}