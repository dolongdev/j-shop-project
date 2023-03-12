package com.jshop.controller.api;

import com.jshop.dto.ColorSizeDto;
import com.jshop.dto.ProductColorDto;
import com.jshop.dto.SizeDto;
import com.jshop.model.ColorSize;
import com.jshop.service.ColorSizeService;
import com.jshop.service.ProductColorService;
import com.jshop.service.SizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping("/update")
    public ResponseEntity<ColorSizeDto> update(){
        ColorSizeDto colorSizeDto = this.colorSizeService.findById(41);
        colorSizeDto.setQuantity(colorSizeDto.getQuantity() - 1);
        ColorSizeDto item = this.colorSizeService.update(41, colorSizeDto);

        return new ResponseEntity<>(item, HttpStatus.OK);
    }
}
