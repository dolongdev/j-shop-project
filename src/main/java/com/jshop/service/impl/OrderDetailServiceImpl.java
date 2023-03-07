package com.jshop.service.impl;

import com.jshop.dto.OrderDetailDto;
import com.jshop.dto.OrderDto;
import com.jshop.exceptions.ResourceNotFoundException;
import com.jshop.model.Order;
import com.jshop.model.OrderDetail;
import com.jshop.respository.OrderDetailRepo;
import com.jshop.service.OrderDetailService;
import com.jshop.service.OrderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {
    @Autowired
    OrderDetailRepo orderDetailRepo;
    @Autowired
    ModelMapper modelMapper;

    @Autowired
    OrderService orderService;

    @Override
    public OrderDetailDto create(OrderDetailDto item) {
        OrderDetail orderDetail = this.orderDetailRepo.save(this.modelMapper.map(item, OrderDetail.class));
        return this.modelMapper.map(orderDetail, OrderDetailDto.class);
    }

    @Override
    public OrderDetailDto update(int id, OrderDetailDto item) {
        OrderDetail orderDetail = this.orderDetailRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order Detail", "ID", id));
        orderDetail.setQuantity(item.getQuantity());
        orderDetail.setColor(item.getColor());
        orderDetail.setSize(item.getSize());
        this.orderDetailRepo.save(orderDetail);
        return this.modelMapper.map(orderDetail, OrderDetailDto.class);
    }

    @Override
    public OrderDetailDto findById(int id) {
        OrderDetail orderDetail = this.orderDetailRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order Detail", "ID", id));
        return this.modelMapper.map(orderDetail, OrderDetailDto.class);
    }

    @Override
    public void delete(int id) {
        this.orderDetailRepo.deleteById(id);
    }

    @Override
    public List<OrderDetailDto> findAll() {
        List<OrderDetail> orderDetails = this.orderDetailRepo.findAll();

        List<OrderDetailDto> list = orderDetails.stream()
                .map((orderDetail) -> this.modelMapper.map(orderDetail, OrderDetailDto.class))
                .collect(Collectors.toList());
        return list;
    }

    @Override
    public List<OrderDetailDto> findAllByOrder(int orderId) {
        OrderDto orderDto = this.orderService.findById(orderId);

        List<OrderDetail> orderDetails = this.orderDetailRepo
                .findAllByOrder(this.modelMapper.map(orderDto, Order.class));

        List<OrderDetailDto> list = orderDetails.stream()
                .map((orderDetail) -> this.modelMapper.map(orderDetail, OrderDetailDto.class))
                .collect(Collectors.toList());
        return list;
    }
}
