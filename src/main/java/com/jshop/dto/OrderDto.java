package com.jshop.dto;

import com.jshop.model.Account;
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
    private boolean status;
    private float amount;
    private Account account;
}
