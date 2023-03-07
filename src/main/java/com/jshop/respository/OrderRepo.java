package com.jshop.respository;

import com.jshop.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepo extends JpaRepository<Order, Integer> {

    List<Order> findAllByAccount_Username(String username);
}
