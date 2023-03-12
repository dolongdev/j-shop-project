package com.jshop.service;

import com.jshop.dto.ColorSizeDto;
import com.jshop.dto.ProductColorDto;
import com.jshop.dto.SizeDto;
import com.jshop.model.ProductColor;

public interface ColorSizeService {
    ColorSizeDto findById(int id);

    ColorSizeDto update(int id, ColorSizeDto colorSizeDto);

    ColorSizeDto findByIdAndProductColor(int id, ProductColor productColor);

    ColorSizeDto findByProductColorAndSize(int productColorId, int sizeId);
}
