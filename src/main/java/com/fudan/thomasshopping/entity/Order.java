package com.fudan.thomasshopping.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.sun.javafx.geom.transform.Identity;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "t_order")
public class Order {
    @Id
    @GeneratedValue
    private Long id;//订单id
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;//顾客
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date created_time;//订单创建时间
    private Double cost;//订单金额
    private Double pay;//实付金额
    private Integer status;//订单状态
    private String type;//订单类型
    @ManyToMany(cascade = {CascadeType.PERSIST})
    private List<Product> products;//商品
    @ElementCollection
    private List<Integer> quantity;//商品数量
    @ManyToMany(cascade = {CascadeType.PERSIST})
    private List<Shop>shops;//商店
    @OneToOne
    private Logistic logistic;
}

