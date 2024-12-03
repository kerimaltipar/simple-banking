package com.eteration.simplebanking.dto;

import lombok.Data;

import java.util.Date;
import java.util.Set;

@Data
public class BankAccountDTO {

    private String accountNumber;
    private String owner;
    private double balance;
    private Date createDate;
    private Set<TransactionDTO> transactions;
}
