package com.fudan.thomasshopping.service;

import com.fudan.thomasshopping.entity.User;

public interface userService {
    User addUser(User user);
    User updateUser(User user);
    User getUser(String userName);
    boolean checkUser(String userName,String password);
}
