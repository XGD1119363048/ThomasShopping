package com.fudan.thomasshopping.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "t_product")
public class Product {
    @Id
    @GeneratedValue
    private Long id;//商品id
    private String name;//商品名称
    private String description;//商品描述
    private Double price;//商品价格
    private Integer stock;//商品库存
    private String categoryId;//商品分类id
    private String categoryChildId;//商品子分类id
    private String imageAddress;//商品图片地址
    @ManyToOne(fetch = FetchType.LAZY)
    private Shop shop;//商店
}
