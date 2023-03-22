package com.jshop.service;

import com.jshop.dto.ColorSizeDto;
import com.jshop.model.ProductColor;

public interface ColorSizeService {
    ColorSizeDto create(ColorSizeDto colorSizeDto);
    ColorSizeDto findById(int id);

    void delete(int id);

    ColorSizeDto update(int id, ColorSizeDto colorSizeDto);

    ColorSizeDto findByIdAndProductColor(int id, ProductColor productColor);

    ColorSizeDto findByProductColorAndSize(int productColorId, int sizeId);
}
