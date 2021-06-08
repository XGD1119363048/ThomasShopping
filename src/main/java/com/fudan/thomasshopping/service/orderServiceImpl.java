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

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Service
public class orderServiceImpl implements orderService{
    @Autowired
    private orderRepository orderRepository;

    @Autowired
    private shopService shopService;

    @Autowired
    private productService productService;

    @Autowired
    private userService userService;

    DecimalFormat df = new DecimalFormat("0.00");

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
    public Order getUnpaidOrder(User user) {
        List<Order> orders = user.getOrders();
        if(orders.size()>0){
            Order order = orders.get(orders.size()-1);
            if(order.getStatus()==0)
                return order;
        }
        Order order = new Order();
        order.setType("未支付订单");
        order.setStatus(0);
        order.setUser(user);
        order.setCost(0.0);
        order.setShops(new ArrayList<>());
        order.setQuantity(new ArrayList<>());
        order.setProducts(new ArrayList<>());
        order = orderRepository.save(order);
        return order;
    }

    @Override
    public Order payOrder(Order order,Integer useCoin) {
        useCoin-=useCoin%10;
        order.setCreated_time(new Date());
        order.setStatus(1);
        order.setType("已支付订单");
        User user = order.getUser();
        double balance = user.getBalance();
        double pay = order.getCost()-useCoin/1000.0;
        pay = Double.parseDouble(df.format(pay));
        order.setPay(pay);
        if(balance<order.getPay())
            return null;
        order = updateOrder(order);
        if(order!=null) {
            int coin = user.getCoin() - useCoin + order.getPay().intValue();
            user.setCoin(coin);
            user.setBalance(user.getBalance() - order.getPay());
            userService.updateUser(user);
            List<Product> products = order.getProducts();
            List<Integer> quantity = order.getQuantity();
            for (int i = 0; i < products.size(); i++) {
                Product product = products.get(i);
                product.setStock(product.getStock() - quantity.get(i));
                productService.updateProduct(product);
            }
        }
        return order;
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
