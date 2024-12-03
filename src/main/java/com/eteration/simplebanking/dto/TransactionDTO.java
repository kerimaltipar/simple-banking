package com.eteration.simplebanking.dto;

import lombok.Data;

import java.util.Date;

@Data
public class TransactionDTO {

    private Date date;
    private double amount;
    private String type;
    private String approvalCode;

}