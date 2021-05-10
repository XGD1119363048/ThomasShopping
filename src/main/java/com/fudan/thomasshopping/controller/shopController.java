package com.fudan.thomasshopping.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fudan.thomasshopping.entity.Comment;
import com.fudan.thomasshopping.entity.Product;
import com.fudan.thomasshopping.entity.Shop;
import com.fudan.thomasshopping.entity.User;
import com.fudan.thomasshopping.service.dataService;
import com.fudan.thomasshopping.service.shopService;
import com.fudan.thomasshopping.service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RestController
public class shopController {
    @Autowired
    private shopService shopService;

    @Autowired
    private userService userService;

    @Autowired
    private dataService dataService;

    @PostMapping("/addShop")
    private JSONObject addShop(@RequestBody JSONObject jsonObject){
        JSONObject res = new JSONObject();
        if(!dataService.checkParams(jsonObject, Arrays.asList("userName","shopName"))){
            res.put("id",null);
            res.put("error","存在参数为空");
            return res;
        }
        String userName =jsonObject.getString("userName");
        String shopName = jsonObject.getString("shopName");
        Shop shop = new Shop();
        shop.setName(shopName);
        shop.setUser(userService.getUser(userName));
        if(shop.getUser()!=null){
            shop = shopService.addShop(shop);
            if(shop!=null){
                User user = shop.getUser();
                user.setStatus(2);
                userService.updateUser(user);
                res.put("error","");
                res.put("id",shop.getId());
                return res;
            }
        }
        res.put("id",null);
        res.put("error","添加店铺信息失败，权限不足");
        return res;
    }

    @PostMapping("/updateShop")
    private JSONObject updateShop(@RequestBody JSONObject jsonObject){
        JSONObject res = new JSONObject();
        if(!dataService.checkParams(jsonObject,Arrays.asList("userName","shopName"))){
            res.put("error","存在参数为空");
            return res;
        }
        String userName =jsonObject.getString("userName");
        String shopName = jsonObject.getString("shopName");
        User user = userService.getUser(userName);
        if(user!=null){
            Shop shop = shopService.getShopByUser(user);
            if(shop!=null){
                shop.setName(shopName);
                if(shopService.updateShop(shop)!=null){
                    res.put("error","");
                    return res;
                }
            }

        }
        res.put("error","更新店铺信息失败");
        return res;
    }

    @GetMapping("/getShopById")
    private JSONObject getShopById(HttpServletRequest request){
        JSONObject res = new JSONObject();
        if(!dataService.checkParams2(request, Collections.singletonList("shopId"))){
            res.put("shop",null);
            res.put("error","存在参数为空");
            return res;
        }
        Long id = Long.parseLong(request.getParameter("shopId"));
        Shop shop = shopService.getShop(id);
        if(shop!=null){
            dataService.simplifyShop(shop);
            res.put("shop",JSONObject.parseObject(JSON.toJSONString(shop)));
            res.put("error","");
        }
        else{
            res.put("shop",null);
            res.put("error","查询店铺信息失败");
        }
        return res;
    }

    @GetMapping("/getAllShops")
    private JSONObject getAllShops(){
        JSONObject res = new JSONObject();
        List<Shop> shops = shopService.getAllShop();
        if(shops!=null){
            for(Shop shop:shops){
               dataService.simplifyShop(shop);
            }
            res.put("shops", JSONArray.parseArray(JSON.toJSONString(shops)));
            res.put("error","");
        }
        else{
            res.put("shops",null);
            res.put("error","查询店铺信息失败");
        }
        return res;
    }

    @GetMapping("/getShopByUserName")
    private JSONObject getShopByUserName(HttpServletRequest request){
        JSONObject res = new JSONObject();
        if(!dataService.checkParams2(request,Collections.singletonList("userName"))){
            res.put("shop",null);
            res.put("error","存在参数为空");
            return res;
        }
        String userName = request.getParameter("userName");
        User user = userService.getUser(userName);
        if(user!=null){
            Shop shop = shopService.getShopByUser(user);
            if(shop!=null){
                dataService.simplifyShop(shop);
                res.put("shop",JSONObject.parseObject(JSON.toJSONString(shop)));
                res.put("error","");
                return res;
            }
        }
        res.put("shop",null);
        res.put("error","查询店铺信息失败");
        return res;
    }

    @GetMapping("/getShopByShopName")
    private JSONObject getShopByShopName(HttpServletRequest request){
        JSONObject res = new JSONObject();
        if(!dataService.checkParams2(request,Collections.singletonList("shopName"))){
            res.put("shop",null);
            res.put("error","存在参数为空");
            return res;
        }
        String name = request.getParameter("shopName");
        Shop shop = shopService.getShopByName(name);
        if(shop!=null){
            dataService.simplifyShop(shop);
            res.put("shop",JSONObject.parseObject(JSON.toJSONString(shop)));
            res.put("error","");
        }
        else{
            res.put("shop",null);
            res.put("error","查询店铺信息失败");
        }
        return res;
    }
}
