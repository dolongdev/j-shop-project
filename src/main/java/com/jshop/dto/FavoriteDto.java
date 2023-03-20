package com.jshop.dto;

import com.jshop.model.Account;
import com.jshop.model.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FavoriteDto {
    private int favoriteId;
    private Date createDate;
    private ProductDto product;
    private AccountDto account;

    public FavoriteDto(Date createDate, ProductDto product, AccountDto account) {
        this.createDate = createDate;
        this.product = product;
        this.account = account;
    }
}
