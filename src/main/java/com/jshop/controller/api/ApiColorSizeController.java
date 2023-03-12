package com.jshop.controller.api;

import com.jshop.dto.ColorSizeDto;
import com.jshop.dto.ProductColorDto;
import com.jshop.dto.SizeDto;
import com.jshop.model.ColorSize;
import com.jshop.service.ColorSizeService;
import com.jshop.service.ProductColorService;
import com.jshop.service.SizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/colorSizes")
public class ApiColorSizeController {
    @Autowired
    ColorSizeService colorSizeService;
    @Autowired
    ProductColorService productColorService;
    @Autowired
    SizeService sizeService;

    @GetMapping("check")
    public ColorSizeDto checkQuantity(@ModelAttribute(name = "productColorId") int productColorId
            , @ModelAttribute(name = "sizeId") int sizeId){
        ColorSizeDto colorSizeDto = this.colorSizeService.findByProductColorAndSize(productColorId, sizeId);
        return colorSizeDto;
    }
}
