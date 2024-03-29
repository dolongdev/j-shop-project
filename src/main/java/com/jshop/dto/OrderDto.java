package com.jshop.dto;

import com.jshop.model.Account;
import com.jshop.model.Discount;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
    private int order_id;
    private String address;
    private Date createDate;
    private int status;
    private float amount;
    private AccountDto account;
    private DiscountDto discount;
}
