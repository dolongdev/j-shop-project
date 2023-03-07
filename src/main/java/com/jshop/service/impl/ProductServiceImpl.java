package com.jshop.service.impl;

import com.jshop.dto.CategoryDto;
import com.jshop.dto.ProductDto;
import com.jshop.exceptions.ResourceNotFoundException;
import com.jshop.model.Category;
import com.jshop.model.Product;
import com.jshop.respository.ProductRepo;
import com.jshop.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepo productRepo;
    @Autowired
    ModelMapper modelMapper;

    @Override
    public ProductDto create(ProductDto item) {
        Product product = this.productRepo.save(this.modelMapper.map(item, Product.class));
        return this.modelMapper.map(product, ProductDto.class);
    }

    @Override
    public ProductDto update(int productId, ProductDto item) {
        Product product = this.productRepo.findById(productId)
                .orElseThrow(()-> new ResourceNotFoundException("Product", "Product ID", productId));
        product.setName(item.getName());
        product.setDescription(item.getDescription());
        product.setDetail(item.getDetail());
        product.setCategory(this.modelMapper.map(item.getCategory(), Category.class));
        this.productRepo.save(product);
        return this.modelMapper.map(product, ProductDto.class);
    }

    @Override
    public void delete(int productId) {
        this.productRepo.deleteById(productId);
    }

    @Override
    public ProductDto findById(int productId) {
        Product product = this.productRepo.findById(productId)
                .orElseThrow(()-> new ResourceNotFoundException("Product", "Product ID", productId));
        return this.modelMapper.map(product, ProductDto.class);
    }

    @Override
    public List<ProductDto> findAll() {
        List<Product> products = this.productRepo.findAll();

        List<ProductDto> list = products.stream()
                .map((product) -> this.modelMapper.map(product, ProductDto.class)).collect(Collectors.toList());
        return list;
    }

    @Override
    public List<ProductDto> findAllByCategory(CategoryDto category) {
        List<Product> products = this.productRepo
                .findAllByCategory(this.modelMapper.map(category, Category.class));

        List<ProductDto> list = products.stream()
                .map((product) -> this.modelMapper.map(product, ProductDto.class)).collect(Collectors.toList());
        return list;
    }

    @Override
    public List<ProductDto> findAllSort(int pageNumber, int pageSize, String sortBy, String sortDir) {
        Sort sort = (sortDir.equalsIgnoreCase("asc") ?
                Sort.by(sortBy).ascending() : Sort.by(sortBy).descending());

        Pageable p = PageRequest.of(pageNumber, pageSize, sort);

        Page<Product> productPage = this.productRepo.findAll(p);

        List<Product> products = productPage.getContent();

        List<ProductDto> list = products.stream()
                .map((product) -> this.modelMapper.map(product, ProductDto.class)).collect(Collectors.toList());
        return list;
    }

    @Override
    public List<ProductDto> searchProc(String keyword, int pageNumber, int pageSize, String sortBy, String sortDir) {
        Sort sort = (sortDir.equalsIgnoreCase("asc") ?
                Sort.by(sortBy).ascending() : Sort.by(sortBy).descending());

        Pageable p = PageRequest.of(pageNumber, pageSize, sort);

        Page<Product> productPage = this.productRepo.findByNameContaining(keyword, p);

        List<Product> products = productPage.getContent();

        List<ProductDto> list = products.stream()
                .map((product) -> this.modelMapper.map(product, ProductDto.class)).collect(Collectors.toList());
        return list;
    }

    @Override
    @Transactional(readOnly = true)
    public List<String> getSizes(int id) {
        List<String> sizes = this.productRepo.get_sizes_by_id(id);
        return sizes;
    }

    @Override
    @Transactional(readOnly = true)
    public List<String> getColors(int id) {
        List<String> colors = this.productRepo.get_colors_by_id(id);
        return colors;
    }
}
