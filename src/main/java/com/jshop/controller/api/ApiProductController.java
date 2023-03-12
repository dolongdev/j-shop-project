package com.jshop.controller.api;

import com.jshop.config.AppConstants;
import com.jshop.dto.ColorSizeDto;
import com.jshop.dto.ProductColorDto;
import com.jshop.dto.ProductDto;
import com.jshop.service.ColorSizeService;
import com.jshop.service.ProductColorService;
import com.jshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ApiProductController {
    @Autowired
    ProductService productService;
    @Autowired
    ProductColorService productColorService;
    @Autowired
    ColorSizeService colorSizeService;

    @GetMapping
    private List<ProductDto> listProduct(){
        List<ProductDto> list = this.productService.findAll();
        return list;
    }

    @GetMapping("/checkQuantity")
    private int checkQuantity(@RequestParam int productId
            , @RequestParam int sizeId
            , @RequestParam int colorId){
        ProductColorDto productColorDto = this.productColorService.findByProductAndColor(productId, colorId);
        ColorSizeDto colorSizeDto = this.colorSizeService
                .findByProductColorAndSize(productColorDto.getProductColorId(), sizeId);
        int result = colorSizeDto.getQuantity();
        return result;
    }

    @GetMapping("/findByCate")
    private List<ProductDto> findByCate(){
        List<ProductDto> list = this
                .productService.findAllByCategorySort(2, 0
                        , AppConstants.PAGE_SIZE, "productId", "asc");
        return list;
    }
}

