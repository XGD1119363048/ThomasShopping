package com.fudan.thomasshopping.service;

import com.fudan.thomasshopping.dao.productRepository;
import com.fudan.thomasshopping.entity.Product;
import com.fudan.thomasshopping.entity.Shop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
@Service
public class productServiceImpl implements productService{
    @Autowired
    private productRepository productRepository;

    @Override
    public Product addProduct(Product product) {
        if(product.getId()==null)
            return productRepository.save(product);
        return null;
    }

    @Override
    public Product updateProduct(Product product) {
        if(product.getId()!=null)
            return productRepository.save(product);
        return null;
    }

    @Override
    public Product getProductById(Long id) {
        if(productRepository.existsById(id))
            return productRepository.getOne(id);
        return null;
    }

    @Override
    public List<Product> getProductByShop(Shop shop) {
        return productRepository.findAllByShop(shop);
    }

    @Override
    public Boolean deleteProductById(Long id) {
        if(productRepository.existsById(id)){
            productRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public List<Product> getProductByCategory(Long categoryId) {
        return productRepository.findAllByCategoryId(categoryId);
    }

    @Override
    public List<Product> getProductByCategoryChildId(Long categoryChildId) {
        return productRepository.findAllByCategoryChildId(categoryChildId);
    }

    @Override
    public List<Product> getProductByCount(int count) {
        List<Product> products = productRepository.findAll();
        if(products.size()<=count)
            return products;
        return products.subList(0,count);
    }
}
