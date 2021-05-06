package com.fudan.thomasshopping.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fudan.thomasshopping.entity.Order;
import com.fudan.thomasshopping.entity.User;
import com.fudan.thomasshopping.service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class userController {
    @Autowired
    private userService userService;

    @PostMapping("/addUser")
    private JSONObject addUser(@RequestBody JSONObject jsonObject) {
        User user = JSON.toJavaObject(jsonObject,User.class);
        JSONObject res = new JSONObject();
        if(userService.addUser(user)!=null)
            res.put("error","");
        else
            res.put("error","添加人员失败");
        return res;
    }

    @PostMapping("/updateUser")
    private JSONObject updateUser(@RequestBody JSONObject jsonObject) {
        User user = JSON.toJavaObject(jsonObject,User.class);
        JSONObject res = new JSONObject();
        if(userService.updateUser(user)!=null)
            res.put("error","");
        else
            res.put("error","更新人员失败");
        return res;
    }

    @GetMapping("/queryUser")
    private JSONObject queryUser(@RequestBody JSONObject jsonObject){
        String userName = jsonObject.getString("userName");
        User user = userService.getUser(userName);
        JSONObject res = new JSONObject();
        if(user!=null){
            user.setOrders(null);
            res.put("user",JSONObject.parseObject(JSON.toJSONString(user)));
            res.put("error","");
        }else{
            res.put("user",null);
            res.put("error","获取人员信息失败");
        }
        return res;
    }

    @PostMapping("/checkUser")
    private JSONObject login(@RequestBody JSONObject jsonObject) {
        String userName = jsonObject.getString("userName");
        String password = jsonObject.getString("password");
        JSONObject res = new JSONObject();
        if(userService.checkUser(userName,password))
            res.put("error","");
        else
            res.put("error","登录失败，账号或密码错误");
        return res;
    }
}
