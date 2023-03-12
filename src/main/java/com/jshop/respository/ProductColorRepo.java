package com.jshop.respository;

import com.jshop.model.Color;
import com.jshop.model.Product;
import com.jshop.model.ProductColor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductColorRepo extends JpaRepository<ProductColor, Integer> {
    ProductColor findByProductAndColor(Product product, Color color);
}
