package com.fudan.thomasshopping.service;

import com.alibaba.fastjson.JSONObject;
import com.fudan.thomasshopping.entity.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface dataService {
    boolean checkParams(JSONObject jsonObject,List<String> params);
    boolean checkParams2(HttpServletRequest request,List<String> params);
    Comment simplifyComment(Comment comment);
    Order simplifyOrder(Order order);
    Product simplifyProduct(Product product);
    Shop simplifyShop(Shop shop);
    User simplifyUser(User user);
    Logistic simplifyLogistic(Logistic logistic);
}
