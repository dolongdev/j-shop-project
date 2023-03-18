package com.jshop.controller.admin;

import com.jshop.dto.OrderDto;
import com.jshop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/administration/orders")
public class OrderControllerMng {
    @Autowired
    OrderService orderService;

    @GetMapping
    public String findAllOrder(Model model){
        List<OrderDto> list = this.orderService.findAll();
        List<OrderDto> orders = this.orderService
                .findAllSort(0, list.size(), "createDate", "desc");
        model.addAttribute("orders", orders);
        return "/admin/orders";
    }
}
