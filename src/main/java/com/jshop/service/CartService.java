package com.jshop.service;


import com.jshop.dto.Cart;
import com.jshop.model.Account;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Service
public class CartService {

    public Map<Integer, Cart> addToCart(Cart cartItem, HttpSession session) {
        Map<Integer, Cart> cartMap = (Map<Integer, Cart>) session.getAttribute("carts");
        if (cartMap == null){
            cartMap = new HashMap<>();
        }

        int productId = cartItem.getProductId();
        if (cartMap.containsKey(productId) == true){
            Cart c = cartMap.get(productId);
            cartMap.get(productId).setQuantity(c.getQuantity() + 1);
        }else {
            cartMap.put(productId, cartItem);
        }
        return cartMap;
    }

    public int countCart(Map<Integer, Cart> cartMap){
        int count = 0;
        if (cartMap != null){
            for (Cart c : cartMap.values()){
                count += c.getQuantity();
            }
        }
        return count;
    }

    public float getAmount(Map<Integer, Cart> cartMap){
        int amount = 0;
        if (cartMap != null){
            for (Cart c : cartMap.values()){
                amount += (c.getPrice() * c.getQuantity());
            }
        }
        return amount;
    }
}
