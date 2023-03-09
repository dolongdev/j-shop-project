package com.jshop.dto;

import com.jshop.model.ColorSize;
import com.jshop.model.Order;
import com.jshop.model.Product;
import com.jshop.model.ProductColor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailDto {
    private int id;
    private int quantity;
    private ProductDto product;
    private OrderDto order;
    private ColorSize colorSize;
    private ProductColor productColor;
}
