package com.duy.accountservice.service.impl;

import com.duy.accountservice.dto.AccountDTO;
import com.duy.accountservice.service.AccountService;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {
    @Override
    public AccountDTO createAccount(AccountDTO accountDTO) {
        System.out.println("Account created successfully.");
        return accountDTO;
    }
}
