package com.fudan.thomasshopping.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fudan.thomasshopping.entity.Product;
import com.fudan.thomasshopping.entity.Shop;
import com.fudan.thomasshopping.service.productService;
import com.fudan.thomasshopping.service.shopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class productController {
    @Autowired
    private productService productService;

    @Autowired
    private shopService shopService;

    @PostMapping("/addProduct")
    private JSONObject addProduct(@RequestBody JSONObject jsonObject){
        String shopName = jsonObject.getString("shopName");
        Shop shop = shopService.getShopByName(shopName);
        Product product = JSON.toJavaObject(jsonObject,Product.class);
        if(shop!=null)
            product.setShop(shop);
        JSONObject res = new JSONObject();
         product = productService.addProduct(product);
        if(shop!=null&&product!=null){
            res.put("error","");
            res.put("id",product.getId());
        }
        else{
            res.put("error","添加商品信息失败");
            res.put("id",null);
        }

        return res;
    }

    @PostMapping("/updateProduct")
    private JSONObject updateProduct(@RequestBody JSONObject jsonObject){
        String shopName = jsonObject.getString("shopName");
        Shop shop = shopService.getShopByName(shopName);
        Product product = JSON.toJavaObject(jsonObject,Product.class);
        if(shop!=null)
            product.setShop(shop);
        JSONObject res = new JSONObject();
        if(shop!=null&&productService.updateProduct(product)!=null){
            res.put("error","");
        }
        else
            res.put("error","更新商品信息失败");
        return res;
    }

    @GetMapping("/getProductsByShopName")
    private JSONObject getProductsByShopName(@RequestBody JSONObject jsonObject){
        String shopName = jsonObject.getString("shopName");
        Shop shop = shopService.getShopByName(shopName);
        JSONObject res = new JSONObject();
        if(shop!=null){
            List<Product>products = productService.getProductByShop(shop);
            for(Product product:products){
                product.setShop(null);
            }
            res.put("product", JSONArray.parseArray(JSON.toJSONString(products)));
            res.put("error","");
            return res;
        }
        res.put("product",null);
        res.put("error","查询商品失败");
        return res;
    }

    @GetMapping("/getProductById")
    private JSONObject getProductById(@RequestBody JSONObject jsonObject){
        Long id = jsonObject.getLong("productId");
        JSONObject res = new JSONObject();
        Product product = productService.getProductById(id);
        if(product!=null){
            Shop shop = product.getShop();
            if(shop!=null){
                shop.setProducts(null);
                shop.setOrders(null);
                shop.setUser(null);
            }

            res.put("product",JSONObject.parseObject(JSON.toJSONString(product)));
            res.put("error","");
        }
        else{
            res.put("product",null);
            res.put("error","查询商品信息失败");
        }
        return res;
    }

    @GetMapping("/deleteProductById")
    private JSONObject deleteProductById(@RequestBody JSONObject jsonObject){
        Long id = jsonObject.getLong("productId");
        JSONObject res = new JSONObject();
        if(productService.deleteProductById(id))
            res.put("error","");
        else
            res.put("error","删除商品信息失败");
        return res;
    }
}
