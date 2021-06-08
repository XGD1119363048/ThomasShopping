package com.fudan.thomasshopping.service;

import com.fudan.thomasshopping.dao.userRepository;
import com.fudan.thomasshopping.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class userServiceImpl implements userService{

    @Autowired
    private userRepository userRepository;

    @Override
    public User addUser(User user) {
        user.setBalance(0.0);
        return userRepository.save(user);
    }

    @Override
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getUser(String userName) {
        if(userRepository.existsById(userName))
            return userRepository.getOne(userName);
        return null;
    }

    @Override
    public boolean checkUser(String userName, String password) {
        User user = getUser(userName);
        return user!=null && user.getPassword().equals(password);
    }
}
