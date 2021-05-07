package com.fudan.thomasshopping.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fudan.thomasshopping.entity.Comment;
import com.fudan.thomasshopping.entity.Product;
import com.fudan.thomasshopping.entity.Shop;
import com.fudan.thomasshopping.entity.User;
import com.fudan.thomasshopping.service.shopService;
import com.fudan.thomasshopping.service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class shopController {
    @Autowired
    private shopService shopService;

    @Autowired
    private userService userService;

    @PostMapping("/addShop")
    private JSONObject addShop(@RequestBody JSONObject jsonObject){
        String userName =jsonObject.getString("userName");
        String shopName = jsonObject.getString("shopName");
        Shop shop = new Shop();
        shop.setName(shopName);
        shop.setUser(userService.getUser(userName));
        JSONObject res = new JSONObject();
        shop = shopService.addShop(shop);
        if(shop!=null){
            User user = shop.getUser();
            user.setStatus(2);
            userService.updateUser(user);
            res.put("error","");
            res.put("id",shop.getId());
        }
        else{
            res.put("id",null);
            res.put("error","添加店铺信息失败，权限不足");
        }

        return res;
    }

    @PostMapping("/updateShop")
    private JSONObject updateShop(@RequestBody JSONObject jsonObject){
        String userName =jsonObject.getString("userName");
        String shopName = jsonObject.getString("shopName");
        Shop shop = shopService.getShopByUser(userService.getUser(userName));
        shop.setName(shopName);
        JSONObject res = new JSONObject();
        if(shopService.updateShop(shop)!=null)
            res.put("error","");
        else
            res.put("error","更新店铺信息失败");
        return res;
    }

    @GetMapping("/getShopById")
    private JSONObject getShopById(HttpServletRequest request){
        Long id = Long.parseLong(request.getParameter("shopId"));
        Shop shop = shopService.getShop(id);
        JSONObject res = new JSONObject();
        if(shop!=null){
            shop.setOrders(null);
            List<Product> products = shop.getProducts();
            for(Product product:products){
                product.setShop(null);
            }
            User user = shop.getUser();
            user.setOrders(null);
            user.setCoin(null);
            user.setStatus(null);
            user.setAge(null);
            user.setAddress(null);
            user.setBirthday(null);
            user.setPassword(null);
            user.setGender(null);
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
        List<Shop> shops = shopService.getAllShop();
        JSONObject res = new JSONObject();
        if(shops!=null){
            for(Shop shop:shops){
                shop.setOrders(null);
                List<Product> products = shop.getProducts();
                for(Product product:products){
                    product.setShop(null);
                }
                User user = shop.getUser();
                user.setOrders(null);
                user.setCoin(null);
                user.setStatus(null);
                user.setAge(null);
                user.setAddress(null);
                user.setBirthday(null);
                user.setPassword(null);
                user.setGender(null);
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
        String userName = request.getParameter("userName");
        JSONObject res = new JSONObject();
        User user = userService.getUser(userName);
        if(user!=null){
            Shop shop = shopService.getShopByUser(user);
            if(shop!=null){
                shop.setOrders(null);
                List<Product> products = shop.getProducts();
                for(Product product:products){
                    product.setShop(null);
                }
                user.setOrders(null);
                user.setCoin(null);
                user.setStatus(null);
                user.setAge(null);
                user.setAddress(null);
                user.setBirthday(null);
                user.setPassword(null);
                user.setGender(null);
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
        String name = request.getParameter("shopName");
        JSONObject res = new JSONObject();
        Shop shop = shopService.getShopByName(name);
        if(shop!=null){
            shop.setOrders(null);
            List<Product> products = shop.getProducts();
            for(Product product:products){
                product.setShop(null);
            }
            User user = shop.getUser();
            user.setOrders(null);
            user.setCoin(null);
            user.setStatus(null);
            user.setAge(null);
            user.setAddress(null);
            user.setBirthday(null);
            user.setPassword(null);
            user.setGender(null);
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
