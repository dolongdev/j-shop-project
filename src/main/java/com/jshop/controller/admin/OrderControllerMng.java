package com.jshop.controller.admin;

import com.jshop.dto.OrderDto;
import com.jshop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/administration/orders")
public class OrderControllerMng {
    @Autowired
    OrderService orderService;

    @GetMapping
    public String findAllOrder(Model model
            , @ModelAttribute(name = "choose", binding = false) String choose
            , @ModelAttribute(name = "status", binding = false) String status
            , @ModelAttribute(name = "time", binding = false) String time){

        switch (choose){
            case "old" -> this.findAll(model, 0,  0, "createDate", "asc");
            case "status" -> this.findAllByStatus(model, Integer.valueOf(status)
                    , 0, 100, "createDate", "desc");
            case "both" -> this.findAllByStatus(model, Integer.valueOf(status)
                    , 0, 100, "createDate", time);
            default -> this.findAll(model, 0,  0, "createDate", "desc");
        }

        return "/admin/orders";
    }

    private void findAll(Model model, int pageNumber, int pageSize, String sortBy, String sortDir){
        List<OrderDto> list = this.orderService.findAll();
        List<OrderDto> orders = this.orderService
                .findAllSort(pageNumber, list.size(), sortBy, sortDir);
        model.addAttribute("orders", orders);
    }

    private void findAllByStatus(Model model, int status
            , int pageNumber, int pageSize, String sortBy, String sortDir){
        List<OrderDto> orders = this.orderService
                .findAllByStatus(status, 0, 10, sortBy, sortDir);
        model.addAttribute("orders", orders);
    }
}
