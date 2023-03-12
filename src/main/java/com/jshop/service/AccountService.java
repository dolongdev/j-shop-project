package com.jshop.service;

import com.jshop.dto.AccountDto;
import com.jshop.model.Account;

import java.util.List;

public interface AccountService {
    AccountDto create(AccountDto accountDto);

    AccountDto update(String username, AccountDto accountDto);

    AccountDto findByUsername(String username);

    Account getAccount(String username);

    void delete(String username);

    Boolean checkUsername(String username);

    List<AccountDto> findAll();
}
