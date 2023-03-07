package com.jshop.dto;

import com.jshop.model.Color;
import com.jshop.model.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductColorDto {
    private int productColorId;
    private String image;
    private boolean active;
    Color color;
    Product product;
}
