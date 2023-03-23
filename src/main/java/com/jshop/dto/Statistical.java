package com.jshop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Statistical {
    private Integer countProduct;
    private Integer countNewAccount;
    private Integer countOrder;
    private float totalEarning;
}
