package com.fudan.thomasshopping.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fudan.thomasshopping.entity.Order;
import com.fudan.thomasshopping.entity.User;
import com.fudan.thomasshopping.service.dataService;
import com.fudan.thomasshopping.service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RestController
public class userController {
    @Autowired
    private userService userService;

    @Autowired
    private dataService dataService;

    DecimalFormat df = new DecimalFormat("0.00");

    @PostMapping("/addUser")
    private JSONObject addUser(@RequestBody JSONObject jsonObject) {
        JSONObject res = new JSONObject();
        if(!dataService.checkParams(jsonObject, Arrays.asList("userName","password"))){
            res.put("error","存在参数为空");
            return res;
        }
        User user = JSON.toJavaObject(jsonObject,User.class);
        if(userService.addUser(user)!=null)
            res.put("error","");
        else
            res.put("error","添加人员失败");
        return res;
    }

    @PostMapping("/updateUser")
    private JSONObject updateUser(@RequestBody JSONObject jsonObject) {
        JSONObject res = new JSONObject();
        if(!dataService.checkParams(jsonObject, Collections.singletonList("userName"))){
            res.put("error","存在参数为空");
            return res;
        }
        User user = JSON.toJavaObject(jsonObject,User.class);

        if(userService.updateUser(user)!=null)
            res.put("error","");
        else
            res.put("error","更新人员失败");
        return res;
    }

    @GetMapping("/queryUser")
    private JSONObject queryUser(HttpServletRequest request){
        JSONObject res = new JSONObject();
        if(!dataService.checkParams2(request,Collections.singletonList("userName"))){
            res.put("user",null);
            res.put("error","存在参数为空");
            return res;
        }
        String userName = request.getParameter("userName");
        User user = userService.getUser(userName);
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
    private JSONObject checkUser(@RequestBody JSONObject jsonObject) {
        JSONObject res = new JSONObject();
        if(!dataService.checkParams(jsonObject,Arrays.asList("userName","password"))){
            res.put("error","存在参数为空");
            return res;
        }
        String userName = jsonObject.getString("userName");
        String password = jsonObject.getString("password");

        if(userService.checkUser(userName,password))
            res.put("error","");
        else
            res.put("error","登录失败，账号或密码错误");
        return res;
    }

    @PostMapping("/addBalance")
    private JSONObject addBalance(@RequestBody JSONObject jsonObject){
        JSONObject res = new JSONObject();
        if(!dataService.checkParams(jsonObject,Arrays.asList("userName","balance"))){
            res.put("error","存在参数为空");
            return res;
        }
        String userName = jsonObject.getString("userName");
        Long balance = jsonObject.getLong("balance");
        User user = userService.getUser(userName);
        if(user!=null){
            Double balance2 = balance+user.getBalance();
            balance2 = Double.parseDouble(df.format(balance2));
            user.setBalance(balance2);
            if(userService.updateUser(user)!=null){
                res.put("error","");
                return res;
            }

        }
        res.put("error","充值失败");
        return res;
    }
}
