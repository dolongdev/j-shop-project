package com.jshop.service;

import com.jshop.dto.ProductDto;

import java.util.List;

public interface ProductService {
    ProductDto create(ProductDto item);

    ProductDto update(int productId, ProductDto item);

    void delete(int productId);

    ProductDto findById(int productId);

    List<ProductDto> findAll();

    List<String> getSizes(int id);

    List<String> getColors(int id);
}
