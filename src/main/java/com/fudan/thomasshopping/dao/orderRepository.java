package com.fudan.thomasshopping.dao;

import com.fudan.thomasshopping.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface orderRepository extends JpaRepository<Order,Long> {
}
