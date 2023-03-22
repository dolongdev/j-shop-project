package com.jshop.service.impl;

import com.jshop.dto.ColorDto;
import com.jshop.dto.ProductColorDto;
import com.jshop.dto.ProductDto;
import com.jshop.model.Color;
import com.jshop.model.Product;
import com.jshop.model.ProductColor;
import com.jshop.respository.ProductColorRepo;
import com.jshop.service.ColorService;
import com.jshop.service.ProductColorService;
import com.jshop.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductColorServiceImpl implements ProductColorService {
    @Autowired
    ProductColorRepo productColorRepo;
    @Autowired
    ProductService productService;
    @Autowired
    ColorService colorService;
    @Autowired
    ModelMapper modelMapper;

    @Override
    public ProductColorDto create(ProductColorDto item) {
        ProductColor productColor = this.productColorRepo.save(this.modelMapper.map(item, ProductColor.class));
        return this.modelMapper.map(productColor, ProductColorDto.class);
    }

    @Override
    public ProductColorDto findById(int id) {
        ProductColor item = this.productColorRepo.getOne(id);
        return this.modelMapper.map(item, ProductColorDto.class);
    }

    @Override
    @Transactional(readOnly = true)
    public void delete(int id) {
        this.productColorRepo.deletepc(id);
    }

    @Override
    public ProductColorDto findByProductAndColor(int productId, int colorId) {
        ProductDto product = this.productService.findById(productId);
        ColorDto color = this.colorService.findById(colorId);
        ProductColor pc = this.productColorRepo
                .findByProductAndColor(this.modelMapper.map(product, Product.class)
                        , this.modelMapper.map(color, Color.class));
        return this.modelMapper.map(pc, ProductColorDto.class);
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
