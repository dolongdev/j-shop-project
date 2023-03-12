package com.jshop.controller.admin;

import com.jshop.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/administration/accounts")
public class AccountControllerMng {
    @Autowired
    AccountService accountService;

    @GetMapping
    public String listAccount(Model model){

        return "/admin/list-account";
    }

    @GetMapping("/addOrEdit")
    public String addOrEdit(Model model){

        return "/admin/addOrEditAccount";
    }
}
