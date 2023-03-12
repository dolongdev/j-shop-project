package com.jshop.service.impl;

import com.jshop.dto.ColorSizeDto;
import com.jshop.dto.ProductColorDto;
import com.jshop.dto.SizeDto;
import com.jshop.exceptions.ResourceNotFoundException;
import com.jshop.model.ColorSize;
import com.jshop.model.ProductColor;
import com.jshop.model.Size;
import com.jshop.respository.ColorSizeRepo;
import com.jshop.service.ColorSizeService;
import com.jshop.service.ProductColorService;
import com.jshop.service.SizeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ColorSizeServiceImpl implements ColorSizeService {
    @Autowired
    ColorSizeRepo colorSizeRepo;
    @Autowired
    ProductColorService productColorService;
    @Autowired
    SizeService sizeService;
    @Autowired
    ModelMapper modelMapper;

    @Override
    public ColorSizeDto findById(int id) {
        ColorSize colorSize = this.colorSizeRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Color Size", "ID", id));
        return this.modelMapper.map(colorSize, ColorSizeDto.class);
    }

    @Override
    public ColorSizeDto findByIdAndProductColor(int id, ProductColor productColor) {
        ColorSize colorSize = this.colorSizeRepo.findByColorSizeIdAndProductColor(id, productColor);
        return this.modelMapper.map(colorSize, ColorSizeDto.class);
    }

    @Override
    public ColorSizeDto findByProductColorAndSize(int productColorId, int sizeId) {
        ProductColorDto productColor = this.productColorService.findById(productColorId);
        SizeDto size = this.sizeService.findById(sizeId);
        ColorSize item = this.colorSizeRepo
                .findByProductColorAndSize(this.modelMapper.map(productColor, ProductColor.class)
                        , this.modelMapper.map(size, Size.class));
        return this.modelMapper.map(item, ColorSizeDto.class);
    }
}
