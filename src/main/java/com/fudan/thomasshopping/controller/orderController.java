//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

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
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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

    public orderController() {
    }

    @PostMapping({"/addOrder"})
    private JSONObject addOrder(@RequestBody JSONObject jsonObject) {
        String userName = jsonObject.getString("userName");
        User user = this.userService.getUser(userName);
        List<Product> products = new ArrayList();
        JSONArray jsonArray = jsonObject.getJSONArray("productId");
        List<Shop> shops = new ArrayList();
        double cost = 0.0D;
        Order order = (Order)JSON.toJavaObject(jsonObject, Order.class);
        List<Integer> quantity = order.getQuantity();

        for(int i = 0; i < jsonArray.size(); ++i) {
            Long id = jsonArray.getLong(i);
            Product product = this.productService.getProductById(id);
            products.add(product);
            cost += product.getPrice() * (double)(Integer)quantity.get(i);
            shops.add(product.getShop());
        }

        order.setUser(user);
        order.setShops(shops);
        order.setProducts(products);
        order.setCost(cost);
        JSONObject res = new JSONObject();
        order = this.orderService.addOrder(order);
        if (order != null) {
            int coin = order.getCost().intValue();
            user.setCoin(coin);
            List<Order> orders = user.getOrders();
            orders.add(order);
            user.setOrders(orders);
            this.userService.updateUser(user);
            res.put("id", order.getId());
            res.put("error", "");
        } else {
            res.put("id", (Object)null);
            res.put("error", "添加订单信息失败");
        }

        return res;
    }

    @PostMapping({"/addProductInOrder"})
    private JSONObject addProductInOrder(@RequestBody JSONObject jsonObject) {
        JSONObject res = new JSONObject();
        Long orderId = jsonObject.getLong("orderId");
        Long productId = jsonObject.getLong("productId");
        int count = jsonObject.getInteger("count");
        Order order = this.orderService.getOrderById(orderId);
        List<Product> products = order.getProducts();
        List<Integer> quantity = order.getQuantity();
        List<Shop> shops = order.getShops();
        Product product = this.productService.getProductById(productId);
        if (order.getStatus() == 0) {
            boolean flag = false;

            for(int i = 0; i < products.size(); ++i) {
                Product product2 = (Product)products.get(i);
                if (product2.getId().equals(productId)) {
                    quantity.set(i, (Integer)quantity.get(i) + count);
                    flag = true;
                    break;
                }
            }

            if (!flag) {
                products.add(product);
                shops.add(product.getShop());
                quantity.add(count);
            }

            order.setProducts(products);
            order.setShops(shops);
            order.setQuantity(quantity);
            order.setCost(order.getCost() + product.getPrice() * (double)count);
            this.orderService.updateOrder(order);
            res.put("error", "");
        } else {
            res.put("error", "向购物车添加商品失败");
        }

        return res;
    }

    @PostMapping({"/deleteProductInOrder"})
    private JSONObject deleteProductInOrder(@RequestBody JSONObject jsonObject) {
        JSONObject res = new JSONObject();
        Long orderId = jsonObject.getLong("orderId");
        Long productId = jsonObject.getLong("productId");
        int count = jsonObject.getInteger("count");
        Order order = this.orderService.getOrderById(orderId);
        List<Product> products = order.getProducts();
        List<Integer> quantity = order.getQuantity();
        List<Shop> shops = order.getShops();
        Product product = this.productService.getProductById(productId);
        if (order.getStatus() == 0) {
            for(int i = 0; i < products.size(); ++i) {
                Product product2 = (Product)products.get(i);
                if (product2.getId().equals(productId)) {
                    quantity.set(i, (Integer)quantity.get(i) - count);
                    if ((Integer)quantity.get(i) == 0) {
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
            order.setCost(order.getCost() - product.getPrice() * (double)count);
            this.orderService.updateOrder(order);
            res.put("error", "");
        } else {
            res.put("error", "向购物车删除商品失败");
        }

        return res;
    }

    @GetMapping({"/getOrderById"})
    private JSONObject getOrderById(HttpServletRequest request) {
        JSONObject res = new JSONObject();
        Long id = Long.parseLong(request.getParameter("orderId"));
        Order order = this.orderService.getOrderById(id);
        if (order != null) {
            order.setShops((List)null);
            User user = order.getUser();
            user.setOrders((List)null);
            user.setCoin((Integer)null);
            user.setStatus((Integer)null);
            user.setAge((Integer)null);
            user.setAddress((String)null);
            user.setBirthday((Date)null);
            user.setPassword((String)null);
            user.setGender((String)null);
            order.setUser(user);
            List<Product> products = order.getProducts();
            Iterator var7 = products.iterator();

            while(var7.hasNext()) {
                Product product = (Product)var7.next();
                Shop shop = product.getShop();
                shop.setOrders((List)null);
                shop.setUser((User)null);
                shop.setProducts((List)null);
            }

            res.put("order", JSONObject.parseObject(JSON.toJSONString(order)));
            res.put("error", "");
        } else {
            res.put("order", (Object)null);
            res.put("error", "查询订单信息失败");
        }

        return res;
    }

    @GetMapping({"/getOrderByUser"})
    private JSONObject getOrderByUser(HttpServletRequest request) {
        JSONObject res = new JSONObject();
        String userName = request.getParameter("userName");
        User user = this.userService.getUser(userName);
        List<Order> orders = user.getOrders();
        if (orders != null) {
            Iterator var6 = orders.iterator();

            while(var6.hasNext()) {
                Order order = (Order)var6.next();
                List<Product> products = order.getProducts();
                Iterator var9 = products.iterator();

                while(var9.hasNext()) {
                    Product product = (Product)var9.next();
                    Shop shop = product.getShop();
                    shop.setOrders((List)null);
                    shop.setProducts((List)null);
                    shop.setUser((User)null);
                }

                order.setShops((List)null);
                user.setOrders((List)null);
                user.setCoin((Integer)null);
                user.setStatus((Integer)null);
                user.setAge((Integer)null);
                user.setAddress((String)null);
                user.setBirthday((Date)null);
                user.setPassword((String)null);
                user.setGender((String)null);
            }

            res.put("orders", JSONArray.parseArray(JSON.toJSONString(orders)));
            res.put("error", "");
        } else {
            res.put("orders", (Object)null);
            res.put("error", "获取订单信息失败");
        }

        return res;
    }

    @GetMapping({"/getOrderByShop"})
    private JSONObject getOrderByShop(HttpServletRequest request) {
        JSONObject res = new JSONObject();
        Long shopId = Long.parseLong(request.getParameter("shopId"));
        Shop shop = this.shopService.getShop(shopId);
        List<Order> orders = shop.getOrders();
        if (orders != null) {
            for(int i = 0; i < orders.size(); ++i) {
                Order order = (Order)orders.get(i);
                if (order.getStatus() == 0) {
                    orders.remove(i);
                    --i;
                } else {
                    User user = order.getUser();
                    user.setOrders((List)null);
                    user.setCoin((Integer)null);
                    user.setStatus((Integer)null);
                    user.setAge((Integer)null);
                    user.setAddress((String)null);
                    user.setBirthday((Date)null);
                    user.setPassword((String)null);
                    user.setGender((String)null);
                    order.setShops((List)null);
                    List<Product> products = order.getProducts();
                    Iterator var10 = products.iterator();

                    while(var10.hasNext()) {
                        Product product = (Product)var10.next();
                        shop = product.getShop();
                        shop.setProducts((List)null);
                        shop.setUser((User)null);
                        shop.setOrders((List)null);
                    }
                }
            }

            res.put("orders", JSONArray.parseArray(JSON.toJSONString(orders)));
            res.put("error", "");
        } else {
            res.put("orders", (Object)null);
            res.put("error", "获取订单信息失败");
        }

        return res;
    }

    @PostMapping({"/payOrder"})
    private JSONObject payOrder(@RequestBody JSONObject jsonObject) {
        JSONObject res = new JSONObject();
        Long orderId = jsonObject.getLong("orderId");
        Integer useCoin = jsonObject.getInteger("useCoin");
        Order order = this.orderService.getOrderById(orderId);
        order.setCreated_time(new Date());
        order.setStatus(1);
        order.setType("已支付订单");
        User user = order.getUser();
        order.setPay(order.getCost() - (double)useCoin / 1000.0D);
        order = this.orderService.updateOrder(order);
        if (order != null) {
            int coin = user.getCoin() - useCoin + order.getPay().intValue();
            user.setCoin(coin);
            this.userService.updateUser(user);
            List<Product> products = order.getProducts();
            List<Integer> quantity = order.getQuantity();

            Product product;
            for(int i = 0; i < products.size(); ++i) {
                product = (Product)products.get(i);
                product.setStock(product.getStock() - (Integer)quantity.get(i));
                this.productService.updateProduct(product);
            }

            order.setShops((List)null);
            user.setOrders((List)null);
            user.setCoin((Integer)null);
            user.setStatus((Integer)null);
            user.setAge((Integer)null);
            user.setAddress((String)null);
            user.setBirthday((Date)null);
            user.setPassword((String)null);
            user.setGender((String)null);
            order.setUser(user);
            Iterator var13 = products.iterator();

            while(var13.hasNext()) {
                product = (Product)var13.next();
                Shop shop = product.getShop();
                shop.setOrders((List)null);
                shop.setUser((User)null);
                shop.setProducts((List)null);
            }

            res.put("order", JSONObject.parseObject(JSON.toJSONString(order)));
            res.put("error", "");
        } else {
            res.put("order", (Object)null);
            res.put("error", "支付订单失败");
        }

        return res;
    }

    @PostMapping({"/cancelOrder"})
    private JSONObject cancelOrder(@RequestBody JSONObject jsonObject) {
        JSONObject res = new JSONObject();
        Long orderId = jsonObject.getLong("orderId");
        Order order = this.orderService.getOrderById(orderId);
        order.setStatus(2);
        order.setType("已取消订单");
        order = this.orderService.updateOrder(order);
        if (order != null) {
            User user = order.getUser();
            int coin = (int)(order.getCost() - order.getPay());
            user.setCoin(user.getCoin() + coin * 1000 - order.getPay().intValue());
            this.userService.updateUser(user);
            res.put("error", "");
        } else {
            res.put("error", "取消订单失败");
        }

        return res;
    }
}
