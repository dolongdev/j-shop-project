package com.jshop.service.impl;

import com.jshop.dto.AccountDto;
import com.jshop.exceptions.ResourceNotFoundException;
import com.jshop.model.Account;
import com.jshop.respository.AccountRepo;
import com.jshop.service.AccountService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    AccountRepo accountRepo;
    @Autowired
    ModelMapper modelMapper;

    @Override
    public AccountDto create(AccountDto accountDto) {
        Account account = this.accountRepo.save(this.modelMapper.map(accountDto, Account.class));
        return this.modelMapper.map(account, AccountDto.class);
    }

    @Override
    public AccountDto update(String username, AccountDto accountDto) {
        Account account = this.accountRepo.findById(username)
                .orElseThrow(() -> new ResourceNotFoundException("Account", "Username", username));
        return this.modelMapper.map(account, AccountDto.class);
    }

    @Override
    public AccountDto findByUsername(String username) {
        Account account = this.accountRepo.findById(username)
                .orElseThrow(() -> new ResourceNotFoundException("Account", "Username", username));
        return this.modelMapper.map(account, AccountDto.class);
    }

    @Override
    public void delete(String username) {
        Account account = this.accountRepo.findById(username)
                .orElseThrow(() -> new ResourceNotFoundException("Account", "Username", username));
        this.accountRepo.delete(account);
    }

    @Override
    public List<AccountDto> findAll() {
        List<Account> accounts = this.accountRepo.findAll();

        List<AccountDto> list = accounts.stream()
                .map((account) -> this.modelMapper.map(account, AccountDto.class)).collect(Collectors.toList());
        return list;
    }
}