package com.jshop.controller.api;

import com.jshop.dto.OrderDetailDto;
import com.jshop.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/orderDetails")
public class ApiOrderDetailController {
    @Autowired
    OrderDetailService orderDetailService;

    @GetMapping("/{orderId}")
    public List<OrderDetailDto> findAllByOrder(@PathVariable int orderId){
        List<OrderDetailDto> list = this.orderDetailService.findAllByOrder(orderId);
        return list;
    }
}
