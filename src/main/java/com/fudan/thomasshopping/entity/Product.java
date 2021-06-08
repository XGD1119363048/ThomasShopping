package com.fudan.thomasshopping.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "t_product")
public class Product {
    @Id
    @GeneratedValue
    private Long id;//商品id
    private String name;//商品名称
    private String description;//商品描述
    private double price;//商品价格
    private Integer stock;//商品库存
    private Long categoryId;//商品分类id
    private Long categoryChildId;//商品子分类id
    private String imageAddress;//商品图片地址
    @ManyToOne(fetch = FetchType.LAZY)
    private Shop shop;//商店
    @OneToMany(mappedBy = "product")
    private List<Comment> comments;
    @ManyToMany(mappedBy = "products")
    private List<Order> orders;
}
