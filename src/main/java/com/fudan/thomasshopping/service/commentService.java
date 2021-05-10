package com.fudan.thomasshopping.service;

import com.fudan.thomasshopping.entity.Comment;
import com.fudan.thomasshopping.entity.Product;

import java.util.List;

public interface commentService {
    Comment addComment(Comment comment);
    Comment updateComment(Comment comment);
    List<Comment> getAllComments();
    Boolean deleteComment(Long id);
    Comment getComment(Long id);
    List<Comment> getCommentByProduct(Product product);
}
