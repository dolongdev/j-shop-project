package com.jshop.service;

import com.jshop.dto.ColorDto;
import com.jshop.dto.ProductColorDto;
import com.jshop.dto.ProductDto;
import com.jshop.model.Product;

import java.util.List;

public interface ProductColorService {

    ProductColorDto create(ProductColorDto item);
    ProductColorDto findById(int id);

    void delete(int id);

    ProductColorDto findByProductAndColor(int productId, int colorId);

    List<ProductColorDto> findAll();
}
