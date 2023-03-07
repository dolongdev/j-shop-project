package com.jshop.respository;

import com.jshop.model.Order;
import com.jshop.model.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderDetailRepo extends JpaRepository<OrderDetail, Integer> {
    List<OrderDetail> findAllByOrder(Order order);
}
