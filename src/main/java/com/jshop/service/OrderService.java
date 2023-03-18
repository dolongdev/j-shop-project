package com.jshop.service;

import com.jshop.dto.OrderDto;

import java.util.List;

public interface OrderService {
    OrderDto create(OrderDto item);

    OrderDto update(int orderId, OrderDto item);

    OrderDto findById(int orderId);

    void delete(int orderId);

    List<OrderDto> findAll();

    List<OrderDto> findAllSort(int pageNumber, int pageSize, String sortBy, String sortDir);

    List<OrderDto> findAllByUsername(String username);
}
