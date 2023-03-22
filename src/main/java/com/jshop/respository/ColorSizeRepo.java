package com.jshop.respository;

import com.jshop.model.ColorSize;
import com.jshop.model.ProductColor;
import com.jshop.model.Size;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ColorSizeRepo extends JpaRepository<ColorSize, Integer> {
    <S extends ColorSize> S save(S entity);
    ColorSize findByColorSizeIdAndProductColor(int id, ProductColor productColor);
    ColorSize findByProductColorAndSize(ProductColor productColor, Size size);
}
