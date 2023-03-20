package com.jshop.controller;

import com.jshop.dto.*;
import com.jshop.model.*;
import com.jshop.service.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.Arrays;
import java.util.Date;
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
    ModelMapper modelMapper;
    @Autowired
    ColorSizeService colorSizeService;
    @Autowired
    ProductColorService productColorService;
    @Autowired
    FavoriteService favoriteService;
    @Autowired
    DiscountService discountService;

    @ModelAttribute
    public void commonAttrs(Model model , HttpSession session, Principal principal){
        model.addAttribute("cartCounter",
                cartService.countCart((Map<Integer, Cart>) session.getAttribute("carts")));
        if (principal != null){
            int count = this.favoriteService.countByUsername(principal.getName());
            model.addAttribute("countFavorite", count);
        }
    }

    @GetMapping("/cart")
    public String cart(Model model, Principal principal, HttpSession session){
        if (principal == null){
            model.addAttribute("error"
                    , "Vui lòng đăng nhập để xử dụng giỏ hàng!");
            return "/home/login";

        }

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

        return "/home/cart";
    }

    @GetMapping("/checkout")
    public String checkout(Model model, Principal principal, HttpSession session){
        if (principal == null){
            model.addAttribute("message"
                    , "Vui lòng đăng nhập để sử dụng chức năng này!");
            return "/home/login";
        }
        Map<Integer, Cart> cartMap = (Map<Integer, Cart>) session.getAttribute("carts");
        AccountDto account = accountService.findByUsername(principal.getName());
        model.addAttribute("user", account);
        model.addAttribute("amount", cartService.getAmount(cartMap));
        return "/home/checkout";
    }

    @PostMapping("/checkout")
    public String addReceipt(HttpSession session, Principal principal, Model model
            , @RequestParam("address") String address
            , @RequestParam(value = "code", required = false) String code){
        if (principal == null){
            model.addAttribute("message"
                    , "Vui lòng đăng nhập để xử dụng giỏ hàng!");
            return "redirect:/security/login";
        }
        Map<Integer, Cart> cartMap = (Map<Integer, Cart>) session.getAttribute("carts");
        if (cartMap != null){
            AccountDto account = this.accountService.findByUsername(principal.getName());
            OrderDto order = new OrderDto();
            order.setAddress(address);
            order.setAccount(account);
            order.setCreateDate(new Date());
            if (code != null){
                DiscountDto discountDto = this.discountService.checkCode(code);
                if (discountDto != null){
                    float amountCart = cartService.getAmount(cartMap);
                    order.setAmount(amountCart - (amountCart * discountDto.getDiscount()));
                    order.setDiscount(discountDto);
                    this.discountService.usedCode(code);
                }else {
                    model.addAttribute("errorDiscount"
                            , "Mã sai hoặc đã hết hiệu lực");
                    return "/home/checkout";
                }
            }


            OrderDto newOrder = this.orderService.create(order);

            for (Cart c : cartMap.values()){
                OrderDetailDto orderDetail = new OrderDetailDto();
                orderDetail.setQuantity(c.getQuantity());
                orderDetail.setOrder(newOrder);
                ProductColorDto productColorDto = this
                        .productColorService.findByProductAndColor(c.getProductId(), c.getProduct_color_id());
                ColorSizeDto colorSizeDto = this.colorSizeService
                        .findByProductColorAndSize(productColorDto.getProductColorId(), c.getColor_size_id());
                orderDetail
                        .setColorSize(colorSizeDto);
                orderDetail
                        .setProductColor(productColorDto);
                orderDetail.setProduct(productService.findById(c.getProductId()));
                colorSizeDto.setQuantity(colorSizeDto.getQuantity() - 1);
                this.colorSizeService.update(colorSizeDto.getColorSizeId(), colorSizeDto);
                OrderDetailDto newOrderDetail = this.orderDetailService.create(orderDetail);
            }
        }

        session.removeAttribute("carts");

        return "redirect:/cart";
    }


    public class MyFormModel {
        private String address;

        // getter and setter
        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }
    }
}
