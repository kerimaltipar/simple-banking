package com.eteration.simplebanking.exception;


public class InsufficientBalanceException extends RuntimeException {

    public InsufficientBalanceException(String errorMessage) {
        super(errorMessage);
    }
}
