package com.fudan.thomasshopping.dao;

import com.fudan.thomasshopping.entity.Product;
import com.fudan.thomasshopping.entity.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface productRepository extends JpaRepository<Product,Long>, JpaSpecificationExecutor<Product> {
    List<Product> findAllByShop(Shop shop);
}
