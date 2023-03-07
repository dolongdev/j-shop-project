package com.jshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/security")
public class SecurityController {

    @GetMapping("/login")
    public String login(){
        return "/home/login";
    }

    @GetMapping("register")
    public String register(){
        return "/home/register";
    }
}
