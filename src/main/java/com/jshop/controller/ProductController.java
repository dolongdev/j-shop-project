package com.jshop.controller;

import com.jshop.dto.ProductDto;
import com.jshop.service.ProductService;
import com.jshop.service.SizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {
    @Autowired
    ProductService productService;
    @Autowired
    SizeService sizeService;

    @GetMapping("/{productId}")
    public String getInfoProc(@PathVariable int productId, Model model){
        ProductDto item = this.productService.findById(productId);
        model.addAttribute("item", item);
        List<String> sizes = this.productService.getSizes(productId);
        List<String> colors = this.productService.getColors(productId);
        model.addAttribute("sizes", sizes);
        model.addAttribute("colors", colors);
        return "index";
    }
}
