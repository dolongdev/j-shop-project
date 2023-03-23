package com.jshop.service.impl;

import com.jshop.dto.AuthorityDto;
import com.jshop.model.Authority;
import com.jshop.respository.AuthorityRepo;
import com.jshop.service.AuthorityService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorityServiceImpl implements AuthorityService {
    @Autowired
    AuthorityRepo authorityRepo;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public AuthorityDto create(AuthorityDto authorityDto) {
        Authority authority = this.authorityRepo.save(this.modelMapper.map(authorityDto, Authority.class));
        return this.modelMapper.map(authority, AuthorityDto.class);
    }

    @Override
    public AuthorityDto findByUsername(String username) {
        Authority authority = this.authorityRepo.findByAccount_Username(username);
        return this.modelMapper.map(authority, AuthorityDto.class);
    }
}
