package com.jshop.dto;

import com.jshop.model.Account;
import com.jshop.model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorityDto {
    private int authorityId;
    private AccountDto account;
    private RoleDto role;
}
