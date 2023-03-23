package com.jshop.service;

import com.jshop.dto.*;
import com.jshop.model.Category;
import com.jshop.model.Product;

import java.util.List;

public interface ProductService {
    ProductDto create(ProductDto item);

    ProductDto update(int productId, ProductDto item);

    void delete(int productId);

    void sell(int procId, int colorId, int sizeId);

    ProductDto findById(int productId);

    ProductDto findByPcAndCs(int pcId, int csId);

    boolean checkByColorAndSize(int cId, int sId, int pId);

    List<ProductDto> findAll();

    List<ProductDto> findAllByCategory(CategoryDto category);

    List<ProductDto> findAllByCategorySort(int categoryId
            , int pageNumber, int pageSize, String sortBy, String sortDir);

    List<ProductDto> findAllSort(int pageNumber, int pageSize, String sortBy, String sortDir);

    List<ProductDto> searchProc(String keyword, int pageNumber, int pageSize, String sortBy, String sortDir);

    List<IdName> getSizes(int id);

    List<IdName> getColors(int id);

    List<CS> getSizesAndColors(int id);

    List<ProductDto> top10sold();

    int getQuantity(int productId, String color, String size);

    Statistical getStatistical();
}
