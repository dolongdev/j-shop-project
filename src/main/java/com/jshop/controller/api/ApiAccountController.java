package com.jshop.controller.api;

import com.jshop.dto.AccountDto;
import com.jshop.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/accounts")
public class ApiAccountController {
    @Autowired
    AccountService accountService;

    @GetMapping
    public ResponseEntity<AccountDto> findById(@ModelAttribute(name = "username") String username){
        AccountDto item = this.accountService.findByUsername(username);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @GetMapping("/check/{username}")
    public Boolean check(@PathVariable String username){
        return this.accountService.checkUsername(username);
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
}
