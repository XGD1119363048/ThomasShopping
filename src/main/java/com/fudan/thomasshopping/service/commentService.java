package com.fudan.thomasshopping.service;

import com.fudan.thomasshopping.entity.Comment;

import java.util.List;

public interface commentService {
    Comment addComment(Comment comment);
    Comment updateComment(Comment comment);
    List<Comment> getAllComments();
    Boolean deleteComment(Long id);
    Comment getComment(Long id);
}
