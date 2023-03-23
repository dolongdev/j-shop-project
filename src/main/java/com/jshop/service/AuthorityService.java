package com.jshop.service;

import com.jshop.dto.AuthorityDto;

public interface AuthorityService {
    AuthorityDto create(AuthorityDto authorityDto);
    AuthorityDto findByUsername(String username);
}
