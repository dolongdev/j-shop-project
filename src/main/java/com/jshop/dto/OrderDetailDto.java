package com.jshop.dto;

import com.jshop.model.Order;
import com.jshop.model.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailDto {
    private int id;
    private int quantity;
    private String color;
    private String size;
    private ProductDto product;
    private OrderDto order;
}
