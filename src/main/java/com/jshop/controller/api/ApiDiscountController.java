package com.jshop.controller.api;

import com.jshop.dto.DiscountDto;
import com.jshop.service.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/discounts")
public class ApiDiscountController {
    @Autowired
    DiscountService discountService;

    @GetMapping
    public ResponseEntity<List<DiscountDto>> findALl(){
        List<DiscountDto> list = this.discountService.findAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{discountId}")
    public ResponseEntity<DiscountDto> findById(@PathVariable int discountId){
        DiscountDto item = this.discountService.findById(discountId);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<DiscountDto> create(@ModelAttribute DiscountDto dto){
        DiscountDto item = this.discountService.create(dto);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<DiscountDto> update(){
        DiscountDto item = this.discountService.findById(1);
        item.setUseCount(item.getUseCount() - 1);
        this.discountService.update(1, item);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @GetMapping("/checkCode/{code}")
    public float checkCode(@PathVariable String code){
        DiscountDto discountDto = this.discountService.checkCode(code);
        if (discountDto != null){
            return discountDto.getDiscount();
        }else {
            return 0;
        }
    }

    @GetMapping("/usedCode")
    public Boolean usedCode(@ModelAttribute(name = "code") String code){
        return this.discountService.usedCode(code);
    }
}
