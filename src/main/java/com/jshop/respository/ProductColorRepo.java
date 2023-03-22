package com.jshop.respository;

import com.jshop.model.Color;
import com.jshop.model.Product;
import com.jshop.model.ProductColor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductColorRepo extends JpaRepository<ProductColor, Integer> {
    ProductColor findByProductAndColor(Product product, Color color);

    @Procedure(name = "deletepc")
    void deletepc(@Param("pcId") int pcId);
}
