package com.jshop.controller;

import com.jshop.dto.Cart;
import com.jshop.dto.OrderDetailDto;
import com.jshop.service.CartService;
import com.jshop.service.CategoryService;
import com.jshop.service.FavoriteService;
import com.jshop.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/orderDetails")
public class OrderDetailController {
    @Autowired
    OrderDetailService orderDetailService;
    @Autowired
    FavoriteService favoriteService;
    @Autowired
    CartService cartService;

    @ModelAttribute
    public void commonAttrs(Model model , HttpSession session, Principal principal){
        model.addAttribute("cartCounter",
                cartService.countCart((Map<Integer, Cart>) session.getAttribute("carts")));
        if (principal != null){
            int count = this.favoriteService.countByUsername(principal.getName());
            model.addAttribute("countFavorite", count);
        }
    }

    @GetMapping("/{orderId}")
    public String getList(Model model, @PathVariable int orderId){
        List<OrderDetailDto> list = this.orderDetailService.findAllByOrder(orderId);
        model.addAttribute("orderDetails", list);

        return "/home/order-detail-list";
    }
}
