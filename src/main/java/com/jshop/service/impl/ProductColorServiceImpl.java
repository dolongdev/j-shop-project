package com.jshop.service.impl;

import com.jshop.dto.ProductColorDto;
import com.jshop.model.ProductColor;
import com.jshop.respository.ProductColorRepo;
import com.jshop.service.ProductColorService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductColorServiceImpl implements ProductColorService {
    @Autowired
    ProductColorRepo productColorRepo;
    @Autowired
    ModelMapper modelMapper;

    @Override
    public ProductColorDto findById(int id) {
        ProductColor item = this.productColorRepo.getOne(id);
        return this.modelMapper.map(item, ProductColorDto.class);
    }

    @Override
    public List<ProductColorDto> findAll() {
        List<ProductColor> productColors = this.productColorRepo.findAll();

        List<ProductColorDto> list = productColors.stream()
                .map((productColor) -> this.modelMapper.map(productColor, ProductColorDto.class))
                .collect(Collectors.toList());
        return list;
    }
}
