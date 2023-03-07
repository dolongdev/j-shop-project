package com.jshop.controller.api;

import com.jshop.dto.ProductDto;
import com.jshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ApiProductController {
    @Autowired
    ProductService productService;

    @GetMapping
    private List<ProductDto> listProduct(){
        List<ProductDto> list = this.productService.findAll();
        return list;
    }
}
