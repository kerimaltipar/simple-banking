package com.eteration.simplebanking.mapper;

import com.eteration.simplebanking.dto.BankAccountDTO;
import com.eteration.simplebanking.dto.TransactionDTO;
import com.eteration.simplebanking.model.BankAccount;
import com.eteration.simplebanking.model.base.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface BankAccountMapper {

    @Mapping(target = "transactions", qualifiedByName = "mapTransactionDTO")
    BankAccountDTO bankAccountToDTO(BankAccount bankAccount);

    @Named("mapTransactionDTO")
    static Set<TransactionDTO> mapTransactionDTO(Set<Transaction> transactions) {
        Set<TransactionDTO> transactionDTOS = new HashSet<>(Collections.emptySet());
        transactions.forEach(transaction -> {
            TransactionDTO transactionDTO = new TransactionDTO();
            transactionDTO.setDate(transaction.getDate());
            transactionDTO.setAmount(transaction.getAmount());
            transactionDTO.setApprovalCode(transaction.getApprovalCode());
            transactionDTO.setType(transaction.getType());
            transactionDTOS.add(transactionDTO);
        });
        return transactionDTOS;
    }
}
