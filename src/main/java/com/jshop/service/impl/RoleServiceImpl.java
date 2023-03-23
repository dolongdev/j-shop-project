package com.jshop.service.impl;

import com.jshop.dto.RoleDto;
import com.jshop.exceptions.ResourceNotFoundException;
import com.jshop.model.Role;
import com.jshop.respository.RoleRepo;
import com.jshop.service.RoleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleRepo roleRepo;
    @Autowired
    ModelMapper modelMapper;

    @Override
    public List<RoleDto> findAll() {
        List<Role> roles = this.roleRepo.findAll();

        List<RoleDto> list = roles.stream()
                .map((role) -> this.modelMapper.map(role, RoleDto.class)).collect(Collectors.toList());
        return list;
    }

    @Override
    public RoleDto findById(String id) {
        Role role = this.roleRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Role", "ID", id));
        return this.modelMapper.map(role, RoleDto.class);
    }
}
