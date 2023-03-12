package com.jshop.dto;

import com.jshop.model.Account;
import com.jshop.model.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    private int productId;
    private String name;
    private Date createDate;
    private String description;
    private String detail;
    private int viewCounts;
    private float price;
    private String image;

    AccountDto account;
    CategoryDto category;
}
