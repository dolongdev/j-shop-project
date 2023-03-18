package com.jshop.controller.api;

import com.jshop.dto.OrderDto;
import com.jshop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class ApiOrderController {
    @Autowired
    OrderService orderService;

    @GetMapping("/{orderId}")
    public OrderDto findById(@PathVariable int orderId){
        OrderDto item = this.orderService.findById(orderId);
        return item;
    }

    @GetMapping("/update/{orderId}/{address}/{status}")
    public OrderDto update(@PathVariable(name = "orderId") String orderId
            , @PathVariable(name = "address") String address
            , @PathVariable(name = "status") String status){
        OrderDto orderDto = this.orderService.findById(Integer.valueOf(orderId));
        orderDto.setAddress(address);
        orderDto.setStatus(Integer.valueOf(status));
        this.orderService.update(Integer.valueOf(orderId), orderDto);
        return orderDto;
    }

    @DeleteMapping("/delete/{orderId}")
    public boolean deleteOrder(@PathVariable int orderId){
        try {
            this.orderService.delete(orderId);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
