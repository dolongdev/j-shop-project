package com.jshop.service.impl;

import com.jshop.dto.*;
import com.jshop.exceptions.ResourceNotFoundException;
import com.jshop.model.Category;
import com.jshop.model.Product;
import com.jshop.respository.ProductRepo;
import com.jshop.service.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepo productRepo;
    @Autowired
    ModelMapper modelMapper;

    @Autowired
    ColorService colorService;

    @Autowired
    SizeService sizeService;
    @Autowired
    ProductColorService productColorService;
    @Autowired
    ColorSizeService colorSizeService;
    @Autowired
    CategoryService categoryService;

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
    public void sell(int procId, int colorId, int sizeId) {
        ProductColorDto productColorDto = this.productColorService
                .findByProductAndColor(procId, colorId);
        ColorSizeDto colorSizeDto = this.colorSizeService
                .findByProductColorAndSize(productColorDto.getProductColorId(), sizeId);
        colorSizeDto.setQuantity(colorSizeDto.getQuantity() - 1);
        this.colorSizeService.update(colorSizeDto.getColorSizeId(), colorSizeDto);
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
    public List<ProductDto> findAllByCategorySort(int categoryId
            , int pageNumber, int pageSize, String sortBy, String sortDir) {
        CategoryDto category = this.categoryService.findById(categoryId);

        Sort sort = (sortDir.equalsIgnoreCase("asc") ?
                Sort.by(sortBy).ascending() : Sort.by(sortBy).descending());

        Pageable p = PageRequest.of(pageNumber, pageSize, sort);

        Page<Product> productPage = this.productRepo
                .findAllByCategory(this.modelMapper.map(category, Category.class), p);

        List<Product> products = productPage.getContent();

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
    public List<IdName> getSizes(int id) {
        List<Object[]> sizes = this.productRepo.get_sizes_by_id(id);
        List<IdName> idNames = new ArrayList<>();
        for (Object[] size : sizes) {
            IdName idName = new IdName(size[0].toString(), size[1].toString());
            idNames.add(idName);
        }
        return idNames;
    }

    @Override
    @Transactional(readOnly = true)
    public List<IdName> getColors(int id) {
        List<Object[]> colors = this.productRepo.get_colors_by_id(id);
        List<IdName> idNames = new ArrayList<>();
        for (Object[] color : colors) {
            IdName idName = new IdName(color[0].toString(), color[1].toString());
            idNames.add(idName);
        }
        return idNames;
    }

    @Override
    @Transactional(readOnly = true)
    public List<CS> getSizesAndColors(int id) {
        List<CS> csList = new ArrayList<>();

        List<Object[]> result = productRepo.get_cs_product(id);
        for (Object[] record : result) {
            ColorDto color = this.colorService.findById((int) record[3]);
            ProductDto product = this.findById((int) record[4]);
            SizeDto size = this.sizeService.findById((int) record[7]);
            ProductColorDto pcDto = new ProductColorDto((Integer) record[0]
                    , (String) record[1], (Boolean) record[2], color, product);
            ColorSizeDto csDto = new ColorSizeDto(
                    (Integer) record[5], (Integer) record[6], size, pcDto);
            CS cs = new CS(pcDto, csDto);
            csList.add(cs);
        }
        return csList;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductDto> top10sold() {
        List<Product> products = this.productRepo.top10sold();

        List<ProductDto> list = products.stream()
                .map((product) -> this.modelMapper.map(product, ProductDto.class)).collect(Collectors.toList());
        return list;
    }

    @Override
    public int getQuantity(int productId, String color, String size) {

        return 0;
    }

}
