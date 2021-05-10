package com.fudan.thomasshopping.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "t_comment")
public class Comment {
    @Id
    @GeneratedValue
    private Long id;//评论id
    private String content;//评论内容
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date created_time;//评论时间
    private String reply;//回复
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date reply_time;//回复时间
    private String nickname;//评论昵称
    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;
}
