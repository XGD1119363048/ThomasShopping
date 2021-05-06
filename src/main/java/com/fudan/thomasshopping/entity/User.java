package com.fudan.thomasshopping.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NonNull;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "t_user")
public class User {
    @Id
    private String userName;//用户名
    @NotNull
    private String password;//密码
    private String gender;//性别
    private Integer age;//年龄
    private String address;//地址
    @JSONField(format="MM-dd")
    private Date birthday;//生日
    private String email;//邮箱
    private String phoneNumber;//手机号
    private Integer status;//用户类型
    private Integer coin;//用户积分
    @OneToMany(mappedBy = "user")
    private List<Order> orders;//订单
}
