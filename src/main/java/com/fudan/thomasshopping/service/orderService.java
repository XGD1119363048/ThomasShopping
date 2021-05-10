package com.fudan.thomasshopping.service;

import com.fudan.thomasshopping.entity.Order;
import com.fudan.thomasshopping.entity.Product;
import com.fudan.thomasshopping.entity.User;
import org.aspectj.weaver.ast.Or;

import java.util.List;

public interface orderService {
    Order addOrder(Order order);
    Order updateOrder(Order order);
    Order getOrderById(Long id);
    Order getUnpaidOrder(User user);
    List<Product> getProductsByOrder(Long orderId);
    List<Order> getOrdersByShop(String shopName);
    List<Order> getOrderByUser(String userName);
}
