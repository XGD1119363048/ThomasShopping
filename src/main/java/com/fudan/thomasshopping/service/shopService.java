package com.fudan.thomasshopping.service;

import com.fudan.thomasshopping.entity.Shop;
import com.fudan.thomasshopping.entity.User;

import java.util.List;

public interface shopService {
    Shop addShop(Shop shop);
    Shop updateShop(Shop shop);
    Shop getShop(Long id);
    List<Shop> getAllShop();
    Shop getShopByUser(User user);
    Shop getShopByName(String name);
}
