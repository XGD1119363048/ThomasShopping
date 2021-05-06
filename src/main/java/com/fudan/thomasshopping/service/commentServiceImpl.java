package com.fudan.thomasshopping.service;

import com.fudan.thomasshopping.dao.commentRepository;
import com.fudan.thomasshopping.entity.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class commentServiceImpl implements commentService{
    @Autowired
    private commentRepository commentRepository;

    @Override
    public Comment addComment(Comment comment) {
        if(comment.getId()==null)
            return commentRepository.save(comment);
        return null;
    }

    @Override
    public Comment updateComment(Comment comment) {
        if(comment.getId()!=null)
            return commentRepository.save(comment);
        return null;
    }


    @Override
    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    @Override
    public Boolean deleteComment(Long id) {
        if(commentRepository.existsById(id)){
            commentRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Comment getComment(Long id) {
        if(commentRepository.existsById(id))
            return commentRepository.getOne(id);
        return null;
    }
}
