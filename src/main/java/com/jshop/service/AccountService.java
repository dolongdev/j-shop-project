package com.jshop.service;

import com.jshop.dto.AccountDto;

import java.util.List;

public interface AccountService {
    AccountDto create(AccountDto accountDto);

    AccountDto update(String username, AccountDto accountDto);

    AccountDto findByUsername(String username);

    void delete(String username);

    List<AccountDto> findAll();
}
