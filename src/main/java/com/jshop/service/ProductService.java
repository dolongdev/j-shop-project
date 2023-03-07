package com.jshop.service;

import com.jshop.dto.CategoryDto;
import com.jshop.dto.ProductDto;
import com.jshop.model.Category;
import com.jshop.model.Product;

import java.util.List;

public interface ProductService {
    ProductDto create(ProductDto item);

    ProductDto update(int productId, ProductDto item);

    void delete(int productId);

    ProductDto findById(int productId);

    List<ProductDto> findAll();

    List<ProductDto> findAllByCategory(CategoryDto category);

    List<ProductDto> findAllSort(int pageNumber, int pageSize, String sortBy, String sortDir);

    List<ProductDto> searchProc(String keyword, int pageNumber, int pageSize, String sortBy, String sortDir);

    List<String> getSizes(int id);

    List<String> getColors(int id);
}
