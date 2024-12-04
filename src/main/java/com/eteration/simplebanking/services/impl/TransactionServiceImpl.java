package com.eteration.simplebanking.services.impl;

import com.eteration.simplebanking.model.base.Transaction;
import com.eteration.simplebanking.repository.TransactionRepository;
import com.eteration.simplebanking.services.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;

    @Override
    public void save(Transaction transaction) {
        transactionRepository.save(transaction);
    }
}
