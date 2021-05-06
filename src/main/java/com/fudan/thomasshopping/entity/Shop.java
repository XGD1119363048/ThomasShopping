package com.fudan.thomasshopping.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "t_shop")
public class Shop {
    @Id
    @GeneratedValue
    private Long id;//商店id
    private String name;//商店名称
    @OneToOne
    private User user;//店主
    @OneToMany(mappedBy = "shop")
    private List<Product> products;//商品
    @ManyToMany(mappedBy = "shops")
    private List<Order> orders;//订单
}
