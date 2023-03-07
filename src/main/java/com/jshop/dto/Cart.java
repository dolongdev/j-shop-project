package com.jshop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cart {
    private int productId;
    private String product_name;
    private float price;
    private int quantity;
    private String color;
    private String size;
    private float total;
}
