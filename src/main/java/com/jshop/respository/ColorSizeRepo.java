package com.jshop.respository;

import com.jshop.model.ColorSize;
import com.jshop.model.ProductColor;
import com.jshop.model.Size;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ColorSizeRepo extends JpaRepository<ColorSize, Integer> {
     ColorSize findByColorSizeIdAndProductColor(int colorSizeId, ProductColor productColor);

     ColorSize findByProductColorAndSize(ProductColor productColor, Size size);
}
