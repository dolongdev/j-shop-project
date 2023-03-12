package com.jshop.respository;

import com.jshop.model.Discount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiscountRepo extends JpaRepository<Discount, Integer> {
    Discount findByCodeDiscount(String code);

}
