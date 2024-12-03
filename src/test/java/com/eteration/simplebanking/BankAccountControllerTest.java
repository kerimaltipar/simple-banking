package com.eteration.simplebanking;

import com.eteration.simplebanking.controller.BankAccountController;
import com.eteration.simplebanking.dto.BankAccountDTO;
import com.eteration.simplebanking.dto.TransactionRequestDTO;
import com.eteration.simplebanking.dto.TransactionResponseDTO;
import com.eteration.simplebanking.services.BankAccountService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BankAccountControllerTest {

    @Mock
    private BankAccountService bankAccountService;

    @InjectMocks
    private BankAccountController bankAccountController;

    @Test
    void getAccount_when_accountNumberIsValid_then_returnBankAccountDTO() {
        String accountNumber = "123-4567";
        BankAccountDTO bankAccountDTO = new BankAccountDTO();
        ResponseEntity<BankAccountDTO> expected = new ResponseEntity<>(bankAccountDTO, HttpStatus.OK);

        when(bankAccountService.getBankAccountDTOByNumber(accountNumber)).thenReturn(bankAccountDTO);

        ResponseEntity<BankAccountDTO> actual = bankAccountController.getAccount(accountNumber);

        assertEquals(expected, actual);
    }

    @Test
    void credit_when_accountNumberAndAmountValid_then_returnTransactionResponseDTO() {
        String accountNumber = "123-4567";
        TransactionRequestDTO requestDTO = new TransactionRequestDTO();
        requestDTO.setAmount(100.0);
        TransactionResponseDTO responseDTO = TransactionResponseDTO.builder()
                .status("OK")
                .approvalCode("8214701247018")
                .build();
        ResponseEntity<TransactionResponseDTO> expected = new ResponseEntity<>(responseDTO, HttpStatus.OK);

        when(bankAccountService.processDeposit(accountNumber, requestDTO)).thenReturn(responseDTO);

        ResponseEntity<TransactionResponseDTO> actual = bankAccountController.credit(accountNumber, requestDTO);

        assertEquals(expected, actual);
    }

    @Test
    void debit_when_accountNumberAndAmountValid_then_returnTransactionResponseDTO() {
        String accountNumber = "123-4567";
        TransactionRequestDTO requestDTO = new TransactionRequestDTO();
        requestDTO.setAmount(100.0);
        TransactionResponseDTO responseDTO = TransactionResponseDTO.builder()
                .status("OK")
                .approvalCode("8214701247018")
                .build();
        ResponseEntity<TransactionResponseDTO> expected = new ResponseEntity<>(responseDTO, HttpStatus.OK);

        when(bankAccountService.processWithdrawal(accountNumber, requestDTO)).thenReturn(responseDTO);

        ResponseEntity<TransactionResponseDTO> actual = bankAccountController.debit(accountNumber, requestDTO);

        assertEquals(expected, actual);
    }

}
