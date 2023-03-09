package com.jshop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cart {
    private int productId;
    private String productName;
    private float price;
    private int quantity;
    private int product_color_id;
    private int color_size_id;
    private float total;

    public double getTotal() {
        return price * quantity;
    }
}
