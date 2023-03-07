package com.jshop.service.impl;

import com.jshop.dto.CategoryDto;
import com.jshop.exceptions.ResourceNotFoundException;
import com.jshop.model.Category;
import com.jshop.respository.CategoryRepo;
import com.jshop.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryRepo categoryRepo;
    @Autowired
    ModelMapper modelMapper;

    @Override
    public CategoryDto create(CategoryDto item) {
        Category category = this.categoryRepo.save(this.modelMapper.map(item, Category.class));
        return this.modelMapper.map(category, CategoryDto.class);
    }

    @Override
    public CategoryDto update(int categoryId, CategoryDto item) {
        Category category = this.categoryRepo.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "Category ID", categoryId));
        category.setActive(item.getActive());
        category.setName(item.getName());
        this.categoryRepo.save(category);
        return this.modelMapper.map(category, CategoryDto.class);
    }

    @Override
    public CategoryDto findById(int categoryId) {
        Category category = this.categoryRepo.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "Category ID", categoryId));
        return this.modelMapper.map(category, CategoryDto.class);
    }

    @Override
    public void delete(int categoryId) {
        this.categoryRepo.deleteById(categoryId);
    }

    @Override
    public List<CategoryDto> findAll() {
        List<Category> categories = this.categoryRepo.findAll();

        List<CategoryDto> list = categories.stream()
                .map((category) -> this.modelMapper.map(category, CategoryDto.class)).collect(Collectors.toList());
        return list;
    }
}
