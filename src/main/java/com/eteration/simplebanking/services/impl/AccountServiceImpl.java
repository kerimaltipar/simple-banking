package com.eteration.simplebanking.services.impl;

import com.eteration.simplebanking.services.AccountService;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

    @Override
    public String hello() {
        return "hello!123!";
    }
}
