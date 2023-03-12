package com.jshop.controller;

import com.jshop.dto.Cart;
import com.jshop.dto.CategoryDto;
import com.jshop.dto.IdName;
import com.jshop.dto.ProductDto;
import com.jshop.service.CartService;
import com.jshop.service.CategoryService;
import com.jshop.service.FavoriteService;
import com.jshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.List;
import java.util.Map;

@Controller
public class HomeController {
    @Autowired
    CartService cartService;

    @Autowired
    ProductService productService;

    @Autowired
    CategoryService categoryService;
    @Autowired
    FavoriteService favoriteService;

    @ModelAttribute
    public void commonAttrs(Model model , HttpSession session, Principal principal){
        model.addAttribute("cartCounter",
                cartService.countCart((Map<Integer, Cart>) session.getAttribute("carts")));
        if (principal != null){
            int count = this.favoriteService.countByUsername(principal.getName());
            model.addAttribute("countFavorite", count);
        }
    }

    @GetMapping({"/home", "/"})
    public String getIndex(Model model){
        List<CategoryDto> categories = this.categoryService.findAll();
        List<ProductDto> productDtos = this.productService.top10sold();
        model.addAttribute("categories", categories);
        model.addAttribute("top10sold", productDtos);
        return "/home/index";
    }

    @GetMapping("/contact")
    public String getContact(){
        return "/home/contact";
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
