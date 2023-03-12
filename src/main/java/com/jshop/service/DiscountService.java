package com.jshop.service;

import com.jshop.dto.DiscountDto;

import java.util.List;

public interface DiscountService {
    DiscountDto create(DiscountDto dto);

    DiscountDto update(int id, DiscountDto dto);

    DiscountDto findById(int id);

    DiscountDto checkCode(String code);

    void delete(int id);

    boolean usedCode(String code);

    List<DiscountDto> findAll();
}
