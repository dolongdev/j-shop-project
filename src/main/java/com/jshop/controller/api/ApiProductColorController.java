package com.jshop.controller.api;

import com.jshop.dto.ColorDto;
import com.jshop.dto.ProductColorDto;
import com.jshop.dto.ProductDto;
import com.jshop.model.Product;
import com.jshop.service.ColorService;
import com.jshop.service.ProductColorService;
import com.jshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/productColors")
public class ApiProductColorController {
    @Autowired
    ProductColorService productColorService;
    @Autowired
    ProductService productService;
    @Autowired
    ColorService colorService;

    @GetMapping
    public List<ProductColorDto> list(){
        List<ProductColorDto> list = this.productColorService.findAll();
        return list;
    }
    @GetMapping("/check")
    public ProductColorDto check(@ModelAttribute(name = "productId") int productId
            , @ModelAttribute(name = "colorId") int colorId){

        ProductColorDto item = this.productColorService.findByProductAndColor(productId, colorId);
        return item;
    }
}
