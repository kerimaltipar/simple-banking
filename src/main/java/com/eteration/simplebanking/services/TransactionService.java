package com.eteration.simplebanking.services;

import com.eteration.simplebanking.model.Transaction;

public interface TransactionService {

    void save(Transaction transaction);
}
