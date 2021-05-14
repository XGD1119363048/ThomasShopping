package com.fudan.thomasshopping.service;

import com.fudan.thomasshopping.dao.logisticRepository;
import com.fudan.thomasshopping.entity.Logistic;
import com.fudan.thomasshopping.entity.Order;
import com.fudan.thomasshopping.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class logisticServiceImpl implements logisticService{
    @Autowired
    private logisticRepository logisticRepository;

    @Override
    public Logistic addLogistic(Logistic logistic) {
        if(logistic.getId()==null)
            return logisticRepository.save(logistic);
        return null;
    }

    @Override
    public Logistic updateLogistic(Logistic logistic) {
        if(logistic.getId()!=null&&logisticRepository.existsById(logistic.getId()))
            return logisticRepository.save(logistic);
        return null;
    }

    @Override
    public Logistic getLogistic(Order order) {
        return order.getLogistic();
    }
}
