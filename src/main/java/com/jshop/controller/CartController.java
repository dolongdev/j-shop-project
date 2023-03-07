package com.jshop.controller;

import com.jshop.dto.*;
import com.jshop.model.Account;
import com.jshop.service.*;
import jakarta.servlet.http.HttpSession;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
public class CartController {
    @Autowired
    AccountService accountService;
    @Autowired
    OrderDetailService orderDetailService;
    @Autowired
    CartService cartService;
    @Autowired
    OrderService orderService;
    @Autowired
    ProductService productService;
    @Autowired
    CategoryService categoryService;

    @Autowired
    ModelMapper modelMapper;

    @GetMapping("/cart")
    public String cart(Model model,  HttpSession session){
//        if (principal == null){
//            model.addAttribute("error"
//                    , "Vui lòng đăng nhập để xử dụng giỏ hàng!");
//            return "redirect:/security/login";
//
//        }
        List<CategoryDto> categoryDtos = this.categoryService.findAll();
        model.addAttribute("categories", categoryDtos);

        Map<Integer, Cart> cartMap = (Map<Integer, Cart>) session.getAttribute("carts");
        if (cartMap != null){
            model.addAttribute("carts", cartMap.values());
            Double totalCarts = 0d;

            for (Cart c : cartMap.values()){
                totalCarts += c.getTotal();
            }
            model.addAttribute("totalCars", totalCarts);
        }else{
            model.addAttribute("carts", null);
            System.out.println("Cart null");
        }

        return "/site/cart";
    }

    @GetMapping("/checkout")
    public String checkout(Model model, Principal principal, HttpSession session){
        if (principal == null){
            model.addAttribute("message"
                    , "Vui lòng đăng nhập để sử dụng chức năng này!");
            return "redirect:/security/login";
        }
        Map<Integer, Cart> cartMap = (Map<Integer, Cart>) session.getAttribute("carts");
        AccountDto account = accountService.findByUsername(principal.getName());
        model.addAttribute("user", account);
        model.addAttribute("amount", cartService.getAmount(cartMap));
        return "/site/checkout";
    }

    @PostMapping("/checkout")
    public String addReceipt(HttpSession session, Principal principal, Model model ){
        if (principal == null){
            model.addAttribute("message"
                    , "Vui lòng đăng nhập để xử dụng giỏ hàng!");
            return "redirect:/security/login";
        }

        RequestAttributes attr = RequestContextHolder.currentRequestAttributes();

        Map<Integer, Cart> cartMap = (Map<Integer, Cart>) session.getAttribute("carts");
        if (cartMap != null){
            AccountDto account = this.accountService.findByUsername(principal.getName());
            OrderDto order = new OrderDto();
            order.setAddress("address");
            order.setAccount(this.modelMapper.map(account, Account.class));
            order.setAmount(cartService.getAmount(cartMap));
            order.setCreateDate(new Date());
            OrderDto newOrder = this.orderService.create(order);

            for (Cart c : cartMap.values()){
                OrderDetailDto orderDetail = new OrderDetailDto();
                orderDetail.setQuantity(c.getQuantity());
                orderDetail.setOrder(order);
                orderDetail.setColor(c.getColor());
                orderDetail.setSize(c.getSize());
                orderDetail.setProduct(productService.findById(c.getProductId()));
                OrderDetailDto newOrderDetail = this.orderDetailService.create(orderDetail);
            }
        }

        session.removeAttribute("carts");

        return "redirect:/home";
    }
}
