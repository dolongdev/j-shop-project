package com.jshop.controller.api;

import com.jshop.dto.ProductColorDto;
import com.jshop.service.ProductColorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/productColors")
public class ApiProductColorController {
    @Autowired
    ProductColorService productColorService;

    @GetMapping
    public List<ProductColorDto> list(){
        List<ProductColorDto> list = this.productColorService.findAll();
        return list;
    }
}
