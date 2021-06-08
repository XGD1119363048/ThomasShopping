package com.fudan.thomasshopping.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fudan.thomasshopping.entity.Order;
import com.fudan.thomasshopping.entity.Product;
import com.fudan.thomasshopping.entity.Shop;
import com.fudan.thomasshopping.entity.User;
import com.fudan.thomasshopping.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.In;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.text.DecimalFormat;
import java.util.*;

@RestController
public class orderController {
    @Autowired
    private orderService orderService;

    @Autowired
    private userService userService;

    @Autowired
    private productService productService;

    @Autowired
    private shopService shopService;

    @Autowired
    private dataService dataService;

    DecimalFormat df = new DecimalFormat("0.00");

    @PostMapping("/addOrder")
    private JSONObject addOrder(@RequestBody JSONObject jsonObject){
        JSONObject res = new JSONObject();
        if(!dataService.checkParams(jsonObject, Arrays.asList("userName","productId","quantity"))){
            res.put("id",null);
            res.put("error","存在参数为空");
            return res;
        }
        String userName = jsonObject.getString("userName");
        User user = userService.getUser(userName);
        if(user!=null){
            List<Product>products = new ArrayList<>();
            JSONArray jsonArray = jsonObject.getJSONArray("productId");
            List<Shop>shops = new ArrayList<>();
            double cost = 0;
            Order order = JSON.toJavaObject(jsonObject, Order.class);
            List<Integer> quantity = order.getQuantity();
            for(int i=0;i<jsonArray.size();i++){
                Long id = jsonArray.getLong(i);
                Product product = productService.getProductById(id);
                if(product!=null){
                    products.add(product);
                    cost += product.getPrice()*quantity.get(i);
                    shops.add(product.getShop());
                }
                else{
                    res.put("id",null);
                    res.put("error","添加订单信息失败");
                    return res;
                }
            }
            order.setUser(user);
            order.setShops(shops);
            order.setProducts(products);
            cost = Double.parseDouble(df.format(cost));
            order.setCost(cost);
            order = orderService.addOrder(order);
            if(order!=null){
                int coin = order.getCost().intValue();
                user.setCoin(coin);
                List<Order>orders = user.getOrders();
                orders.add(order);
                user.setOrders(orders);
                userService.updateUser(user);
                res.put("id",order.getId());
                res.put("error","");
                return res;
            }
        }
        res.put("id",null);
        res.put("error","添加订单信息失败");
        return res;
    }

    @PostMapping("/addProductInOrder")
    private JSONObject addProductInOrder(@RequestBody JSONObject jsonObject){
        JSONObject res = new JSONObject();
        if(!dataService.checkParams(jsonObject,Arrays.asList("userName","productId","count"))){
            res.put("error","存在参数为空");
            return res;
        }
        String  userName = jsonObject.getString("userName");
        User user = userService.getUser(userName);
        if(user==null){
            res.put("error","向购物车添加商品失败");
            return res;
        }
        Long orderId = orderService.getUnpaidOrder(user).getId();
        Long productId = jsonObject.getLong("productId");
        int count = jsonObject.getInteger("count");
        Order order = orderService.getOrderById(orderId);
        if(order!=null){
            List<Product> products = order.getProducts();
            List<Integer> quantity = order.getQuantity();
            List<Shop> shops = order.getShops();
            Product product = productService.getProductById(productId);
            if(product!=null&&order.getStatus()==0){
                boolean flag = false;
                for(int i=0;i<products.size();i++){
                    Product product2 = products.get(i);
                    if(product2.getId().equals(productId)){
                        quantity.set(i, quantity.get(i)+count);
                        flag = true;
                        break;
                    }
                }
                if(!flag){
                    products.add(product);
                    shops.add(product.getShop());
                    quantity.add(count);
                }
                order.setProducts(products);
                order.setShops(shops);
                order.setQuantity(quantity);
                order.setCost(order.getCost()+product.getPrice()*count);
                orderService.updateOrder(order);
                res.put("error","");
                return res;
            }
        }
        res.put("error","向购物车添加商品失败");
        return res;
    }

    @PostMapping("/deleteProductInOrder")
    private JSONObject deleteProductInOrder(@RequestBody JSONObject jsonObject){
        JSONObject res = new JSONObject();
        if(!dataService.checkParams(jsonObject,Arrays.asList("userName","productId","count"))){
            res.put("error","存在参数为空");
            return res;
        }
        String userName = jsonObject.getString("userName");
        User user =userService.getUser(userName);
        if(user==null){
            res.put("error","向购物车删除商品失败");
            return res;
        }
        Long orderId = orderService.getUnpaidOrder(user).getId();
        Long productId = jsonObject.getLong("productId");
        int count = jsonObject.getInteger("count");
        Order order = orderService.getOrderById(orderId);
        if(order!=null){
            List<Product> products = order.getProducts();
            List<Integer> quantity = order.getQuantity();
            List<Shop> shops = order.getShops();
            Product product = productService.getProductById(productId);
            if(product!=null&&order.getStatus()==0){
                for(int i=0;i<products.size();i++){
                    Product product2 = products.get(i);
                    if(product2.getId().equals(productId)){
                        quantity.set(i, Math.max(quantity.get(i) - count, 0));
                        if(quantity.get(i)==0){
                            products.remove(i);
                            shops.remove(i);
                            quantity.remove(i);
                        }
                        break;
                    }
                }
                order.setProducts(products);
                order.setShops(shops);
                order.setQuantity(quantity);
                order.setCost(order.getCost()-product.getPrice()*count);
                orderService.updateOrder(order);
                res.put("error","");
                return res;
            }
        }
        res.put("error","向购物车删除商品失败");
        return res;
    }

    @GetMapping("/getOrderById")
    private JSONObject getOrderById(HttpServletRequest request){
        JSONObject res = new JSONObject();
        if(!dataService.checkParams2(request, Collections.singletonList("orderId"))){
            res.put("error","存在参数为空");
            res.put("order",null);
            return  res;
        }
        Long id = Long.parseLong(request.getParameter("orderId"));
        Order order = orderService.getOrderById(id);
        if(order!=null){
            dataService.simplifyOrder(order);
            res.put("order",JSONObject.parseObject(JSON.toJSONString(order)));
            res.put("error","");
        }
        else{
            res.put("order",null);
            res.put("error","查询订单信息失败");
        }
        return res;
    }

    @GetMapping("/getOrderByUser")
    private JSONObject getOrderByUser(HttpServletRequest request){
        JSONObject res = new JSONObject();
        if(!dataService.checkParams2(request,Collections.singletonList("userName"))){
            res.put("orders",null);
            res.put("error","存在参数为空");
            return res;
        }
        String userName = request.getParameter("userName");
        User user = userService.getUser(userName);
        if(user!=null){
            List<Order> orders = user.getOrders();
            if(orders!=null){
                for(Order order:orders){
                    dataService.simplifyOrder(order);
                }
                res.put("orders",JSONArray.parseArray(JSON.toJSONString(orders)));
                res.put("error","");
                return res;
            }
        }
        res.put("orders",null);
        res.put("error","获取订单信息失败");
        return res;
    }

    @GetMapping("/getOrderByShop")
    private JSONObject getOrderByShop(HttpServletRequest request){
        JSONObject res = new JSONObject();
        if(!dataService.checkParams2(request,Collections.singletonList("shopId"))){
            res.put("orders",null);
            res.put("error","存在参数为空");
            return res;
        }
        Long shopId = Long.parseLong(request.getParameter("shopId"));
        Shop shop = shopService.getShop(shopId);
        if(shop!=null){
            List<Order>orders = shop.getOrders();
            if(orders!=null){
                for(int i=0;i<orders.size();i++){
                    Order order = orders.get(i);
                    if(order.getStatus()==0){
                        orders.remove(i);
                        i--;
                        continue;
                    }
                    dataService.simplifyOrder(order);
                }
                res.put("orders",JSONArray.parseArray(JSON.toJSONString(orders)));
                res.put("error","");
                return res;
            }
        }
        res.put("orders",null);
        res.put("error","获取订单信息失败");
        return res;
    }

    @PostMapping("/payOrder")
    private JSONObject payOrder(@RequestBody JSONObject jsonObject){
        JSONObject res = new JSONObject();
        if(!dataService.checkParams(jsonObject,Arrays.asList("userName","useCoin"))){
            res.put("order",null);
            res.put("error","存在参数为空");
            return res;
        }
        String userName = jsonObject.getString("userName");
        User user = userService.getUser(userName);
        if(user==null){
            res.put("error","支付订单失败");
            res.put("order",null);
            return res;
        }
        Long orderId = orderService.getUnpaidOrder(user).getId();
        Integer useCoin = jsonObject.getInteger("useCoin");
        Order order = orderService.getOrderById(orderId);
        if(order!=null){
            order = orderService.payOrder(order,useCoin);
            if(order!=null) {
                dataService.simplifyOrder(order);
                res.put("order",JSONObject.parseObject(JSON.toJSONString(order)));
                res.put("error","");
                return res;
            }
        }
        res.put("order",null);
        res.put("error","支付订单失败");
        return res;
    }

    @PostMapping("/cancelOrder")
    private JSONObject cancelOrder(@RequestBody JSONObject jsonObject){
        JSONObject res = new JSONObject();
        if(!dataService.checkParams(jsonObject,Arrays.asList("orderId"))){
            res.put("error","存在参数为空");
            return res;
        }
        Long orderId = jsonObject.getLong("orderId");
        Order order = orderService.getOrderById(orderId);
        if(order!=null&&order.getStatus()==1){
            order.setStatus(2);
            order.setType("已取消订单");
            order = orderService.updateOrder(order);
            if(order!=null){
                User user = order.getUser();
                int coin = (int) (order.getCost()-order.getPay());
                user.setCoin(user.getCoin()+coin*1000-order.getPay().intValue());
                user.setBalance(user.getBalance()+order.getPay());
                userService.updateUser(user);
                res.put("error","");
                return res;
            }
        }
        res.put("error","取消订单失败");
        return res;
    }
}
