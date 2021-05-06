package com.fudan.thomasshopping.service;

import com.fudan.thomasshopping.dao.orderRepository;
import com.fudan.thomasshopping.dao.shopRepository;
import com.fudan.thomasshopping.dao.userRepository;
import com.fudan.thomasshopping.entity.Order;
import com.fudan.thomasshopping.entity.Product;
import com.fudan.thomasshopping.entity.Shop;
import com.fudan.thomasshopping.entity.User;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class orderServiceImpl implements orderService{
    @Autowired
    private orderRepository orderRepository;

    @Autowired
    private shopService shopService;

    @Autowired
    private userService userService;

    @Override
    public Order addOrder(Order order) {
        if(order.getId()==null)
            return orderRepository.save(order);
        return null;
    }

    @Override
    public Order updateOrder(Order order) {
        if(orderRepository.existsById(order.getId()))
            return orderRepository.save(order);
        return null;
    }

    @Override
    public Order getOrderById(Long id) {
        if(orderRepository.existsById(id))
            return orderRepository.getOne(id);
        return null;
    }

    @Override
    public List<Product> getProductsByOrder(Long orderId) {
        if(orderRepository.existsById(orderId)){
            Order order = orderRepository.getOne(orderId);
            return order.getProducts();
        }
        return null;
    }

    @Override
    public List<Order> getOrdersByShop(String shopName) {
        Shop shop = shopService.getShopByName(shopName);
        if(shop!=null)
            return shop.getOrders();
        return null;
    }

    @Override
    public List<Order> getOrderByUser(String userName) {
        User user = userService.getUser(userName);
        if(user!=null)
            return user.getOrders();
        return null;
    }
}
