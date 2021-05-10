package com.fudan.thomasshopping.service;

import com.fudan.thomasshopping.entity.Order;
import com.fudan.thomasshopping.entity.Product;
import com.fudan.thomasshopping.entity.Shop;

import java.util.List;

public interface productService {
    Product addProduct(Product product);
    Product updateProduct(Product product);
    Product getProductById(Long id);
    List<Product> getProductByShop(Shop shop);
    Boolean deleteProductById(Long id);
    List<Product> getProductByCategory(Long categoryId);
    List<Product> getProductByCategoryChildId(Long categoryChildId);
    List<Product> getProductByCount(int count);
}
