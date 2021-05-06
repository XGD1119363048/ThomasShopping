package com.fudan.thomasshopping.dao;

import com.fudan.thomasshopping.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface userRepository extends JpaRepository<User,String> {
}
