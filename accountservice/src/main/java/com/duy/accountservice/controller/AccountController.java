package com.duy.accountservice.controller;

import com.duy.accountservice.dto.AccountDTO;
import com.duy.accountservice.producer.AccountProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/account")
public class AccountController {
    private final AccountProducer accountProducer;

    @Autowired
    public AccountController(AccountProducer accountProducer) {
        this.accountProducer = accountProducer;
    }

    @PostMapping
    public AccountDTO create(@RequestBody AccountDTO account) {
        return accountProducer.createAccount(account);
    }

}
