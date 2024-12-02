package com.eteration.simplebanking.dto;

import lombok.Data;

@Data
public class TransactionResponseDTO {

    private String status;
    private String approvalCode;
}
