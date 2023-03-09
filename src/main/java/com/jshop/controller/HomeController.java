package com.jshop.controller;

import com.jshop.dto.Cart;
import com.jshop.dto.IdName;
import com.jshop.service.CartService;
import com.jshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
public class HomeController {
    @Autowired
    CartService cartService;

    @Autowired
    ProductService productService;

    @ModelAttribute
    public void commonAttrs(Model model , HttpSession session){
        model.addAttribute("cartCounter",
                cartService.countCart((Map<Integer, Cart>) session.getAttribute("carts")));
    }

    @GetMapping({"/home", "/"})
    public String getIndex(Model model){
        return "/home/index";
    }

    @GetMapping("/test")
    public String getTest(Model model){
        List<IdName> sizes = this.productService.getSizes(2);
        List<IdName> colors = this.productService.getColors(2);
        model.addAttribute("sizes", sizes);
        model.addAttribute("colors", colors);
        return "/index";
    }
}
