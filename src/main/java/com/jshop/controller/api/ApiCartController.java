package com.jshop.controller.api;

import com.jshop.dto.Cart;
import com.jshop.service.CartService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ApiCartController {
    @Autowired
    CartService cartService;

    @GetMapping("/cart")
    public List<Cart> cartList(HttpSession session){
        List<Cart> carts = new ArrayList<>();
        Map<Integer, Cart> cartMap = (Map<Integer, Cart>) session.getAttribute("carts");

        if (cartMap != null){
            for(Cart c : cartMap.values()){
                carts.add(c);
            }
        }

        return carts;
    }

    @PostMapping("/cart")
    public int addToCart(@RequestBody Cart cartItem, HttpSession session){
        Map<Integer, Cart> cartMap = (Map<Integer, Cart>) session.getAttribute("carts");
        if (cartMap == null){
            cartMap = new HashMap<>();
        }

        int productId = cartItem.getProductId();
        if (cartMap.containsKey(productId)){
            Cart c = cartMap.get(productId);
            c.setQuantity(c.getQuantity() + 1);
        }else {
            cartMap.put(productId, cartItem);
        }
        session.setAttribute("carts", cartMap);

        return cartService.countCart(cartMap);
    }

    @PutMapping("/cart")
    public int updateCart(@RequestBody Cart cartItem, HttpSession session){
        Map<Integer, Cart> cartMap = (Map<Integer, Cart>) session.getAttribute("carts");

        int productId = cartItem.getProductId();
        if (cartMap.containsKey(productId)){
            Cart c = cartMap.get(productId);
            c.setQuantity(c.getQuantity() + 1);
        }

        session.setAttribute("carts", cartMap);

        return cartService.countCart(cartMap);
    }

    @DeleteMapping("/cart/{productId}")
    public int deleteCart(@PathVariable int productId, HttpSession session){
        Map<Integer, Cart> cartMap = (Map<Integer, Cart>) session.getAttribute("carts");
        if (cartMap != null && cartMap.containsKey(productId)){
            cartMap.remove(productId);
            session.setAttribute("carts", cartMap);
        }
        return cartService.countCart(cartMap);
    }
}
