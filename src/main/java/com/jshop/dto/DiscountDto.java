package com.jshop.dto;

import com.jshop.model.Account;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DiscountDto {
    private int discountId;
    @NotNull
    private String codeDiscount;
    @NotNull
    private float discount;
    @NotNull
    private int useCount;
    @NotNull
    private Boolean status;
    private Account account;

    public DiscountDto(String codeDiscount, float discount, int useCount, Boolean status) {
        this.codeDiscount = codeDiscount;
        this.discount = discount;
        this.useCount = useCount;
        this.status = status;
    }
}
