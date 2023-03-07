package com.jshop.service;

import com.jshop.dto.OrderDetailDto;
import com.jshop.model.Order;

import java.util.List;

public interface OrderDetailService {
    OrderDetailDto create(OrderDetailDto item);

    OrderDetailDto update(int id, OrderDetailDto item);

    OrderDetailDto findById(int id);

    void delete(int id);

    List<OrderDetailDto> findAll();

    List<OrderDetailDto> findAllByOrder(int orderId);
}
