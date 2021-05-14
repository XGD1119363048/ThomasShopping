package com.fudan.thomasshopping.service;

import com.alibaba.fastjson.JSONObject;
import com.fudan.thomasshopping.entity.*;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class dataServiceImpl implements dataService{

    @Override
    public boolean checkParams(JSONObject jsonObject, List<String> params) {
        for(String param:params)
            if(jsonObject.get(param)==null)
                return false;
        return true;
    }

    @Override
    public boolean checkParams2(HttpServletRequest request, List<String> params) {
        for(String param:params)
            if(request.getParameter(param)==null)
                return false;
        return true;
    }

    @Override
    public Comment simplifyComment(Comment comment) {
        Product product = comment.getProduct();
        product.setComments(null);
        product.setShop(null);
        product.setStock(null);
        product.setCategoryChildId(null);
        product.setCategoryId(null);
        product.setImageAddress(null);
        product.setOrders(null);
        return comment;
    }

    @Override
    public Order simplifyOrder(Order order) {
        order.setLogistic(null);
        User user = order.getUser();
        user.setCoin(null);
        user.setGender(null);
        user.setPassword(null);
        user.setBirthday(null);
        user.setAge(null);
        user.setBalance(null);
        user.setStatus(null);
        user.setOrders(null);
        user.setEmail(null);
        List<Product> products = order.getProducts();
        for(Product product:products){
            product.setComments(null);
            product.setDescription(null);
            product.setCategoryId(null);
            product.setCategoryChildId(null);
//            product.setStock(null);
            product.setOrders(null);
            Shop shop = product.getShop();
            shop.setOrders(null);
            shop.setUser(null);
            shop.setProducts(null);
        }
        return order;
    }

    @Override
    public Product simplifyProduct(Product product) {
        product.setOrders(null);
        product.setComments(null);
        Shop shop = product.getShop();
        shop.setProducts(null);
        shop.setOrders(null);
        shop.setUser(null);
        return product;
    }

    @Override
    public Shop simplifyShop(Shop shop) {
        shop.setOrders(null);
        User user = shop.getUser();
        user.setOrders(null);
        user.setAge(null);
        user.setBalance(null);
        user.setBirthday(null);
        user.setPassword(null);
        user.setGender(null);
        user.setCoin(null);
        user.setStatus(null);
        List<Product>products = shop.getProducts();
        for(Product product:products){
            product.setComments(null);
            product.setOrders(null);
            product.setShop(null);
        }
        return shop;
    }

    @Override
    public User simplifyUser(User user) {
        user.setOrders(null);
        return user;
    }

    @Override
    public Logistic simplifyLogistic(Logistic logistic) {
        logistic.setOrder(null);
        return logistic;
    }
}
