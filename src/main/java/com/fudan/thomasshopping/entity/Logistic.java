package com.fudan.thomasshopping.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "t_logistic")
public class Logistic {
    @Id
    @GeneratedValue
    private Long id;
    @OneToOne
    private Order order;
    @ElementCollection
    private List<String> status;
    @ElementCollection
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private List<Date> dates;
}
