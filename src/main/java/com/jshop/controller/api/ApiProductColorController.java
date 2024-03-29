package com.jshop.controller.api;

import com.jshop.dto.ApiResponsive;
import com.jshop.dto.ColorDto;
import com.jshop.dto.ProductColorDto;
import com.jshop.dto.ProductDto;
import com.jshop.model.Product;
import com.jshop.service.ColorService;
import com.jshop.service.ColorSizeService;
import com.jshop.service.ProductColorService;
import com.jshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productColors")
public class ApiProductColorController {
    @Autowired
    ProductColorService productColorService;
    @Autowired
    ProductService productService;
    @Autowired
    ColorSizeService colorSizeService;
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

    @DeleteMapping("/delete/{productColorId}")
    public ApiResponsive deletePC(@PathVariable int productColorId){
        this.productColorService.delete(productColorId);
        return new ApiResponsive("Deleted PC ID: "+productColorId, true);
    }
}
