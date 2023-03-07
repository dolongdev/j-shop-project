package com.jshop.service.impl;

import com.jshop.dto.OrderDto;
import com.jshop.exceptions.ResourceNotFoundException;
import com.jshop.model.Order;
import com.jshop.respository.OrderRepo;
import com.jshop.service.OrderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderRepo orderRepo;
    @Autowired
    ModelMapper modelMapper;

    @Override
    public OrderDto create(OrderDto item) {
        Order order = this.orderRepo.save(this.modelMapper.map(item, Order.class));
        return this.modelMapper.map(order, OrderDto.class);
    }

    @Override
    public OrderDto update(int orderId, OrderDto item) {
        Order existOrder = this.orderRepo.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order", "Order ID", orderId));
        existOrder.setAddress(item.getAddress());
        existOrder.setStatus(item.isStatus());
        this.orderRepo.save(existOrder);
        return this.modelMapper.map(existOrder, OrderDto.class);
    }

    @Override
    public OrderDto findById(int orderId) {
        Order existOrder = this.orderRepo.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order", "Order ID", orderId));
        return this.modelMapper.map(existOrder, OrderDto.class);
    }

    @Override
    public void delete(int orderId) {
        this.orderRepo.deleteById(orderId);
    }

    @Override
    public List<OrderDto> findAll() {
        List<Order> orders = this.orderRepo.findAll();

        List<OrderDto> list = orders.stream()
                .map((order) -> this.modelMapper.map(order, OrderDto.class)).collect(Collectors.toList());
        return list;
    }

    @Override
    public List<OrderDto> findAllByUsername(String username) {
        List<Order> orders = this.orderRepo.findAllByAccount_Username(username);

        List<OrderDto> list = orders.stream()
                .map((order) -> this.modelMapper.map(order, OrderDto.class)).collect(Collectors.toList());
        return list;
    }
}
