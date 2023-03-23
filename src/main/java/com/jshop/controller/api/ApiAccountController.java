package com.jshop.controller.api;

import com.jshop.dto.AccountDto;
import com.jshop.dto.ApiResponsive;
import com.jshop.dto.AuthorityDto;
import com.jshop.dto.RoleDto;
import com.jshop.service.AccountService;
import com.jshop.service.AuthorityService;
import com.jshop.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Date;

@RestController
@RequestMapping("/api/accounts")
public class ApiAccountController {
    @Autowired
    AccountService accountService;
    @Autowired
    AuthorityService authorityService;
    @Autowired
    RoleService roleService;

    @GetMapping
    public ResponseEntity<AccountDto> findById(@ModelAttribute(name = "username") String username){
        AccountDto item = this.accountService.findByUsername(username);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @GetMapping("/{username}")
    public ResponseEntity<AccountDto> findByUsername(@PathVariable String username){
        AccountDto item = this.accountService.findByUsername(username);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @GetMapping("/auth/{username}")
    public ResponseEntity<AuthorityDto> getAuthByUsername(@PathVariable String username){
        AuthorityDto authorityDto = this.authorityService.findByUsername(username);
        return new ResponseEntity<>(authorityDto, HttpStatus.OK);
    }

    @GetMapping("/check/{username}")
    public Boolean check(@PathVariable String username){
        return this.accountService.checkUsername(username);
    }

    @PostMapping("/update/{username}")
    public ResponseEntity<AccountDto> updateAccount(@PathVariable String username
            , @ModelAttribute(name = "phone") String phone
            , @ModelAttribute(name = "firstName") String firstName
            , @ModelAttribute(name = "lastName") String lastName
            , @ModelAttribute(name = "email") String email
            , @ModelAttribute(name = "password") String password){
        AccountDto accountDto = new AccountDto();
        accountDto.setPhone(phone);
        accountDto.setFirstName(firstName);
        accountDto.setLastName(lastName);
        accountDto.setEmail(email);
        accountDto.setPassword(password);
        AccountDto item = this.accountService.update(username, accountDto);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<AccountDto> addAccount(@ModelAttribute(name = "username") String username
            , @ModelAttribute(name = "phone") String phone
            , @ModelAttribute(name = "firstName") String firstName
            , @ModelAttribute(name = "lastName") String lastName
            , @ModelAttribute(name = "email") String email
            , @ModelAttribute(name = "password") String password){
        AccountDto accountDto = new AccountDto();
        accountDto.setUsername(username);
        accountDto.setCreateDate(new Date());
        accountDto.setPhone(phone);
        accountDto.setFirstName(firstName);
        accountDto.setLastName(lastName);
        accountDto.setEmail(email);
        accountDto.setPassword(password);
        AccountDto item = this.accountService.create(accountDto);
        this.setDefaultRole(item);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @GetMapping("/changePass/{oldPass}/{newPass}")
    public Boolean changPass(Model model, Principal principal
            , @PathVariable String oldPass
            , @PathVariable String newPass){
        AccountDto accountDto = this.accountService.findByUsername(principal.getName());
        System.out.println(oldPass + ", " + newPass);
        if (accountDto.getPassword().equals(oldPass)){
            accountDto.setPassword(newPass);
            AccountDto updateAcc = this
                    .accountService.update(accountDto.getUsername(), accountDto);
            return true;
        }else {
            return false;
        }
    }

    @DeleteMapping("/delete/{username}")
    public ApiResponsive deleteAccount(@PathVariable String username){
        this.accountService.delete(username);
        return new ApiResponsive("Deleted Account with username:"+ username, true);
    }

    private void setDefaultRole(AccountDto accountDto){
        RoleDto roleDto = this.roleService.findById("CUST");
        AuthorityDto authorityDto = new AuthorityDto();
        authorityDto.setAccount(accountDto);
        authorityDto.setRole(roleDto);
        AuthorityDto newAuth = this.authorityService.create(authorityDto);
    }
}
