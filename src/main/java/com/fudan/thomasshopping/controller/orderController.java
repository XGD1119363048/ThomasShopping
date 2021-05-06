package com.fudan.thomasshopping.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fudan.thomasshopping.entity.Order;
import com.fudan.thomasshopping.entity.Product;
import com.fudan.thomasshopping.entity.Shop;
import com.fudan.thomasshopping.entity.User;
import com.fudan.thomasshopping.service.orderService;
import com.fudan.thomasshopping.service.productService;
import com.fudan.thomasshopping.service.shopService;
import com.fudan.thomasshopping.service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.In;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    @PostMapping("/addOrder")
    private JSONObject addOrder(@RequestBody JSONObject jsonObject){
        String userName = jsonObject.getString("userName");
        User user = userService.getUser(userName);
        List<Product>products = new ArrayList<>();
        JSONArray jsonArray = jsonObject.getJSONArray("productId");
        List<Shop>shops = new ArrayList<>();
        double cost = 0;
        Order order = JSON.toJavaObject(jsonObject, Order.class);
        List<Integer> quantity = order.getQuantity();
        for(int i=0;i<jsonArray.size();i++){
            Long id = jsonArray.getLong(i);
            Product product = productService.getProductById(id);
            products.add(product);
            cost += product.getPrice()*quantity.get(i);
            shops.add(product.getShop());
        }
        order.setUser(user);
        order.setShops(shops);
        order.setProducts(products);
        order.setCost(cost);
        JSONObject res = new JSONObject();
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
        }
        else{
            res.put("id",null);
            res.put("error","添加订单信息失败");
        }
        return res;
    }

    @PostMapping("/addProductInOrder")
    private JSONObject addProductInOrder(@RequestBody JSONObject jsonObject){
        JSONObject res = new JSONObject();
        Long orderId = jsonObject.getLong("orderId");
        Long productId = jsonObject.getLong("productId");
        int count = jsonObject.getInteger("count");
        Order order = orderService.getOrderById(orderId);
        List<Product> products = order.getProducts();
        List<Integer> quantity = order.getQuantity();
        List<Shop> shops = order.getShops();
        Product product = productService.getProductById(productId);
        if(order.getStatus()==0){
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
        }
        else{
            res.put("error","向购物车添加商品失败");
        }
        return res;
    }

    @PostMapping("/deleteProductInOrder")
    private JSONObject deleteProductInOrder(@RequestBody JSONObject jsonObject){
        JSONObject res = new JSONObject();
        Long orderId = jsonObject.getLong("orderId");
        Long productId = jsonObject.getLong("productId");
        int count = jsonObject.getInteger("count");
        Order order = orderService.getOrderById(orderId);
        List<Product> products = order.getProducts();
        List<Integer> quantity = order.getQuantity();
        List<Shop> shops = order.getShops();
        Product product = productService.getProductById(productId);
        if(order.getStatus()==0){
            for(int i=0;i<products.size();i++){
                Product product2 = products.get(i);
                if(product2.getId().equals(productId)){
                    quantity.set(i, quantity.get(i)-count);
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
        }
        else{
            res.put("error","向购物车删除商品失败");
        }
        return res;
    }

    @GetMapping("/getOrderById")
    private JSONObject getOrderById(@RequestBody JSONObject jsonObject){
        JSONObject res = new JSONObject();
        Long id = jsonObject.getLong("orderId");
        Order order = orderService.getOrderById(id);
        if(order!=null){
            order.setShops(null);
            User user = order.getUser();
            user.setOrders(null);
            user.setCoin(null);
            user.setStatus(null);
            user.setAge(null);
            user.setAddress(null);
            user.setBirthday(null);
            user.setPassword(null);
            user.setGender(null);
            order.setUser(user);
            List<Product>products = order.getProducts();
            for(Product product:products){
                Shop shop = product.getShop();
                shop.setOrders(null);
                shop.setUser(null);
                shop.setProducts(null);
            }
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
    private JSONObject getOrderByUser(@RequestBody JSONObject jsonObject){
        JSONObject res = new JSONObject();
        String userName = jsonObject.getString("userName");
        User user = userService.getUser(userName);
        List<Order> orders = user.getOrders();
        if(orders!=null){
            for(Order order:orders){
                List<Product> products = order.getProducts();
                for(Product product:products){
                    Shop shop = product.getShop();
                    shop.setOrders(null);
                    shop.setProducts(null);
                    shop.setUser(null);
                }
                order.setShops(null);
                user.setOrders(null);
                user.setCoin(null);
                user.setStatus(null);
                user.setAge(null);
                user.setAddress(null);
                user.setBirthday(null);
                user.setPassword(null);
                user.setGender(null);
            }
            res.put("orders",JSONArray.parseArray(JSON.toJSONString(orders)));
            res.put("error","");
        }
        else{
            res.put("orders",null);
            res.put("error","获取订单信息失败");
        }
        return res;
    }

    @GetMapping("/getOrderByShop")
    private JSONObject getOrderByShop(@RequestBody JSONObject jsonObject){
        JSONObject res = new JSONObject();
        Long shopId = jsonObject.getLong("shopId");
        Shop shop = shopService.getShop(shopId);
        List<Order>orders = shop.getOrders();
        if(orders!=null){
            for(int i=0;i<orders.size();i++){
                Order order = orders.get(i);
                if(order.getStatus()==0){
                    orders.remove(i);
                    i--;
                    continue;
                }
                User user = order.getUser();
                user.setOrders(null);
                user.setCoin(null);
                user.setStatus(null);
                user.setAge(null);
                user.setAddress(null);
                user.setBirthday(null);
                user.setPassword(null);
                user.setGender(null);
                order.setShops(null);
                List<Product>products = order.getProducts();
                for(Product product:products){
                   shop = product.getShop();
                   shop.setProducts(null);
                   shop.setUser(null);
                   shop.setOrders(null);
                }
            }
            res.put("orders",JSONArray.parseArray(JSON.toJSONString(orders)));
            res.put("error","");
        }
        else{
            res.put("orders",null);
            res.put("error","获取订单信息失败");
        }
        return res;
    }

    @PostMapping("/payOrder")
    private JSONObject payOrder(@RequestBody JSONObject jsonObject){
        JSONObject res = new JSONObject();
        Long orderId = jsonObject.getLong("orderId");
        Integer useCoin = jsonObject.getInteger("useCoin");
        Order order = orderService.getOrderById(orderId);
        order.setCreated_time(new Date());
        order.setStatus(1);
        order.setType("已支付订单");
        User user = order.getUser();
        order.setPay(order.getCost()-useCoin/1000.0);
        order = orderService.updateOrder(order);
        if(order!=null) {
            int coin = user.getCoin() - useCoin +order.getPay().intValue();
            user.setCoin(coin);
            userService.updateUser(user);
            List<Product> products = order.getProducts();
            List<Integer> quantity = order.getQuantity();
            for (int i = 0; i < products.size(); i++) {
                Product product = products.get(i);
                product.setStock(product.getStock() - quantity.get(i));
                productService.updateProduct(product);
            }
            order.setShops(null);
            user.setOrders(null);
            user.setCoin(null);
            user.setStatus(null);
            user.setAge(null);
            user.setAddress(null);
            user.setBirthday(null);
            user.setPassword(null);
            user.setGender(null);
            order.setUser(user);
            for (Product product : products) {
                Shop shop = product.getShop();
                shop.setOrders(null);
                shop.setUser(null);
                shop.setProducts(null);
            }
            res.put("order",JSONObject.parseObject(JSON.toJSONString(order)));
            res.put("error","");
        }
        else{
            res.put("order",null);
            res.put("error","支付订单失败");
        }
        return res;
    }

    @PostMapping("/cancelOrder")
    private JSONObject cancelOrder(@RequestBody JSONObject jsonObject){
        JSONObject res = new JSONObject();
        Long orderId = jsonObject.getLong("orderId");
        Order order = orderService.getOrderById(orderId);
        order.setStatus(2);
        order.setType("已取消订单");
        order = orderService.updateOrder(order);
        if(order!=null){
            User user = order.getUser();
            int coin = (int) (order.getCost()-order.getPay());
            user.setCoin(user.getCoin()+coin*1000-order.getPay().intValue());
            userService.updateUser(user);
            res.put("error","");
        }
        else{
            res.put("error","取消订单失败");
        }
        return res;
    }
}
