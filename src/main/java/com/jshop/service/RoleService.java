package com.jshop.service;

import com.jshop.dto.RoleDto;

import java.util.List;

public interface RoleService {
    List<RoleDto> findAll();

    RoleDto findById(String id);
}
