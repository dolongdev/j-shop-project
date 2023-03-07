package com.jshop.controller.api;

import com.jshop.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/accounts")
public class ApiAccountController {
    @Autowired
    AccountService accountService;
}
