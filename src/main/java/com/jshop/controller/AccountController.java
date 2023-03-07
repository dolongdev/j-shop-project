package com.jshop.controller;

import com.jshop.dto.OrderDto;
import com.jshop.service.AccountService;
import com.jshop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/accounts")
public class AccountController {
    @Autowired
    AccountService accountService;
    @Autowired
    OrderService orderService;

    @GetMapping("/orders")
    public String getOrders(Model model, Principal principal){
        if (principal == null){
            model.addAttribute("message"
                    , "Vui lòng đăng nhập để xem giỏ hàng!");
            return "/home/login";
        }

        List<OrderDto> list = this.orderService.findAllByUsername(principal.getName());
        model.addAttribute("orders", list);

        return "/home/order-list";
    }
}
