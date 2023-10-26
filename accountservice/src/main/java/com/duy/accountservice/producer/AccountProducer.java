package com.duy.accountservice.producer;

import com.duy.accountservice.dto.AccountDTO;

public interface AccountProducer {
    AccountDTO createAccount(AccountDTO account);
}
