package com.jshop.service;

import com.jshop.dto.ProductColorDto;

import java.util.List;

public interface ProductColorService {

    ProductColorDto findById(int id);
    List<ProductColorDto> findAll();
}
