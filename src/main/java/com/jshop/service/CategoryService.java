package com.jshop.service;

import com.jshop.dto.CategoryDto;

import java.util.List;

public interface CategoryService {
    CategoryDto create(CategoryDto item);

    CategoryDto update(int categoryId, CategoryDto item);

    CategoryDto findById(int categoryId);

    void delete(int categoryId);

    List<CategoryDto> findAll();
}
