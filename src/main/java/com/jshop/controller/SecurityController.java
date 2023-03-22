package com.jshop.controller;

import com.jshop.dto.AccountDto;
import com.jshop.service.AccountService;
import com.jshop.utils.DateUtils;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.text.ParseException;
import java.util.Date;

@Controller
@RequestMapping("/security")
public class SecurityController {
    @Autowired
    AccountService accountService;

    @GetMapping("/login")
    public String login(){
        return "/home/login";
    }

    @GetMapping("/register")
    public String register(Model model) throws ParseException {
        DateUtils dateUtils = new DateUtils();
        AccountDto accountDto = new AccountDto();
        String date = dateUtils.formatDate(new Date(), "dd/MM/yyyy");
        model.addAttribute("date", date);
        model.addAttribute("account", accountDto);
        return "/home/register";
    }

    @GetMapping("/changePass")
    public String changePassForm(){
        return "/home/change-pass";
    }

    @PostMapping("/register")
    public String registerPost(Model model
            , @ModelAttribute(name = "rePassword") String rePassword
            , @ModelAttribute(name = "account") AccountDto account){
        account.setCreateDate(new Date());
        AccountDto item = this.accountService.create(account);
        model.addAttribute("error", "Tạo tài khoản thành công!");
        return "/home/login";
    }

    @PostMapping("/changePass")
    public String changPass(Model model, Principal principal
            , @ModelAttribute(name = "oldPass") String oldPass
            , @ModelAttribute(name = "newPass") String newPass
            , @ModelAttribute(name = "reNewPass") String reNewPass){
        AccountDto accountDto = this.accountService.findByUsername(principal.getName());
        if (accountDto.getPassword().equals(oldPass)){

        }
        return "/home/login";
    }
}
