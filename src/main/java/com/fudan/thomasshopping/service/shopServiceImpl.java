package com.fudan.thomasshopping.service;

import com.fudan.thomasshopping.dao.shopRepository;
import com.fudan.thomasshopping.entity.Shop;
import com.fudan.thomasshopping.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class shopServiceImpl implements shopService{
    @Autowired
    private shopRepository shopRepository;

    @Override
    public Shop addShop(Shop shop) {
        if(shop.getId()==null)
            return shopRepository.save(shop);
        return null;
    }

    @Override
    public Shop updateShop(Shop shop) {
        if(shop.getId()!=null)
            return shopRepository.save(shop);
        return null;
    }

    @Override
    public Shop getShop(Long id) {
        if(shopRepository.existsById(id))
            return shopRepository.getOne(id);
        return null;
    }

    @Override
    public List<Shop> getAllShop() {
        return shopRepository.findAll();
    }

    @Override
    public Shop getShopByUser(User user) {
        return shopRepository.getShopByUser(user);
    }

    @Override
    public Shop getShopByName(String name) {
        return shopRepository.getShopByName(name);
    }
}
