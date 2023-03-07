package com.jshop.controller;

import com.jshop.dto.OrderDetailDto;
import com.jshop.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/orderDetails")
public class OrderDetailController {
    @Autowired
    OrderDetailService orderDetailService;

    @GetMapping("/{orderId}")
    public String getList(Model model, @PathVariable int orderId){
        List<OrderDetailDto> list = this.orderDetailService.findAllByOrder(orderId);
        model.addAttribute("orderDetails", list);

        return "/home/order-detail-list";
    }
}
