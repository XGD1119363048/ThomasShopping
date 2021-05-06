package com.fudan.thomasshopping.dao;

import com.fudan.thomasshopping.entity.Shop;
import com.fudan.thomasshopping.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface shopRepository extends JpaRepository<Shop,Long> {
    Shop getShopByUser(User user);

    Shop getShopByName(String name);
}
