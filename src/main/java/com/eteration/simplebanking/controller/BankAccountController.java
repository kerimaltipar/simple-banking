package com.eteration.simplebanking.controller;

import com.eteration.simplebanking.dto.BankAccountDTO;
import com.eteration.simplebanking.dto.TransactionRequestDTO;
import com.eteration.simplebanking.dto.TransactionResponseDTO;
import com.eteration.simplebanking.services.BankAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/account")
public class BankAccountController {

    private final BankAccountService bankAccountService;

    @GetMapping("v1/{accountNumber}")
    public ResponseEntity<BankAccountDTO> getAccount(@PathVariable String accountNumber) {
        return new ResponseEntity<>(bankAccountService.getBankAccountDTOByNumber(accountNumber), HttpStatus.OK);
    }

    @PostMapping("v1/credit/{accountNumber}")
    public ResponseEntity<TransactionResponseDTO> credit(@PathVariable String accountNumber, @RequestBody TransactionRequestDTO requestDTO) {
        return new ResponseEntity<>(bankAccountService.processDeposit(accountNumber, requestDTO), HttpStatus.OK);
    }

    @PostMapping("v1/debit/{accountNumber}")
    public ResponseEntity<TransactionResponseDTO> debit(@PathVariable String accountNumber, @RequestBody TransactionRequestDTO requestDTO) {
        return new ResponseEntity<>(bankAccountService.processWithdrawal(accountNumber, requestDTO), HttpStatus.OK);
    }

}