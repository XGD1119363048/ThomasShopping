package com.fudan.thomasshopping.service;

import com.fudan.thomasshopping.entity.Logistic;
import com.fudan.thomasshopping.entity.Order;
import com.fudan.thomasshopping.entity.Product;

public interface logisticService {
    Logistic addLogistic(Logistic logistic);
    Logistic updateLogistic(Logistic logistic);
    Logistic getLogistic(Order order);
}
