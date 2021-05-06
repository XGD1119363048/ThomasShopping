package com.fudan.thomasshopping.dao;

import com.fudan.thomasshopping.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface commentRepository extends JpaRepository<Comment,Long> {
}
