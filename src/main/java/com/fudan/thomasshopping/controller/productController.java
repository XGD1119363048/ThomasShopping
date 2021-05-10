package com.fudan.thomasshopping.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fudan.thomasshopping.entity.Product;
import com.fudan.thomasshopping.entity.Shop;
import com.fudan.thomasshopping.service.dataService;
import com.fudan.thomasshopping.service.productService;
import com.fudan.thomasshopping.service.shopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RestController
public class productController {
    @Autowired
    private productService productService;

    @Autowired
    private shopService shopService;

    @Autowired
    private dataService dataService;

    @PostMapping("/addProduct")
    private JSONObject addProduct(@RequestBody JSONObject jsonObject){
        JSONObject res = new JSONObject();
        if(!dataService.checkParams(jsonObject, Collections.singletonList("shopName"))){
            res.put("id",null);
            res.put("error","存在参数为空");
            return res;
        }
        String shopName = jsonObject.getString("shopName");
        Shop shop = shopService.getShopByName(shopName);
        Product product = JSON.toJavaObject(jsonObject,Product.class);
        if(shop!=null){
            product.setShop(shop);
            product = productService.addProduct(product);
            if(product!=null){
                res.put("error","");
                res.put("id",product.getId());
                return res;
            }
        }
        res.put("error","添加商品信息失败");
        res.put("id",null);
        return res;
    }

    @PostMapping("/updateProduct")
    private JSONObject updateProduct(@RequestBody JSONObject jsonObject){
        JSONObject res = new JSONObject();
        if(!dataService.checkParams(jsonObject,Arrays.asList("shopName","id"))){
            res.put("error","存在参数为空");
            return res;
        }
        String shopName = jsonObject.getString("shopName");
        Long id = jsonObject.getLong("id");
        Shop shop = shopService.getShopByName(shopName);
        Product product = JSON.toJavaObject(jsonObject,Product.class);
        if(shop!=null&&productService.getProductById(id)!=null){
            product.setShop(shop);
            if(productService.updateProduct(product)!=null){
                res.put("error","");
            }
            return res;
        }
        res.put("error","更新商品信息失败");
        return res;
    }

    @GetMapping("/getProductsByShopName")
    private JSONObject getProductsByShopName(HttpServletRequest request){
        JSONObject res = new JSONObject();
        if(!dataService.checkParams2(request,Collections.singletonList("shopName"))){
            res.put("product",null);
            res.put("error","存在参数为空");
            return res;
        }
        String shopName = request.getParameter("shopName");
        Shop shop = shopService.getShopByName(shopName);
        if(shop!=null){
            List<Product>products = productService.getProductByShop(shop);
            for(Product product:products){
               dataService.simplifyProduct(product);
            }
            res.put("product", JSONArray.parseArray(JSON.toJSONString(products)));
            res.put("error","");
            return res;
        }
        res.put("product",null);
        res.put("error","查询商品失败");
        return res;
    }

    @GetMapping("/getProductByCategoryId")
    private JSONObject getProductByCategoryId(HttpServletRequest request){
        JSONObject res = new JSONObject();
        if(!dataService.checkParams2(request,Collections.singletonList("categoryId"))){
            res.put("product",null);
            res.put("error","存在参数为空");
            return res;
        }
        Long categoryId = Long.parseLong(request.getParameter("categoryId"));
        List<Product> products = productService.getProductByCategory(categoryId);
        if(products!=null){
            for(Product product:products){
                dataService.simplifyProduct(product);
            }
            res.put("product", JSONArray.parseArray(JSON.toJSONString(products)));
            res.put("error","");
        }
        else{
            res.put("product",null);
            res.put("error","查询商品失败");
        }
        return res;
    }

    @GetMapping("/getProductByCategoryChildId")
    private JSONObject getProductByCategoryChildId(HttpServletRequest request){
        JSONObject res = new JSONObject();
        if(!dataService.checkParams2(request,Collections.singletonList("categoryChildId"))){
            res.put("product",null);
            res.put("error","存在参数为空");
            return res;
        }
        Long categoryChildId = Long.parseLong(request.getParameter("categoryChildId"));
        List<Product> products = productService.getProductByCategoryChildId(categoryChildId);
        if(products!=null){
            for(Product product:products){
                dataService.simplifyProduct(product);
            }
            res.put("product", JSONArray.parseArray(JSON.toJSONString(products)));
            res.put("error","");
        }
        else{
            res.put("product",null);
            res.put("error","查询商品失败");
        }
        return res;
    }

    @GetMapping("/getProductById")
    private JSONObject getProductById(HttpServletRequest request){
        JSONObject res = new JSONObject();
        if(!dataService.checkParams2(request,Collections.singletonList("productId"))){
            res.put("product",null);
            res.put("error","存在参数为空");
            return res;
        }
        Long id = Long.parseLong(request.getParameter("productId"));
        Product product = productService.getProductById(id);
        if(product!=null){
            dataService.simplifyProduct(product);
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
    private JSONObject deleteProductById(HttpServletRequest request){
        JSONObject res = new JSONObject();
        if(!dataService.checkParams2(request,Collections.singletonList("productId"))){
            res.put("error","存在参数为空");
            return res;
        }
        try {
            Long id = Long.parseLong(request.getParameter("productId"));
            if(productService.deleteProductById(id))
                res.put("error","");
            else
                res.put("error","删除商品信息失败");
        }catch (Exception e){
            res.put("error","删除商品失败");
        }
        return res;
    }

    @GetMapping("/getProductByCount")
    private JSONObject getProductByCount(HttpServletRequest request){
        JSONObject res = new JSONObject();
        if(!dataService.checkParams2(request,Collections.singletonList("count"))){
            res.put("product",null);
            res.put("error","存在参数为空");
            return res;
        }
        int count = Integer.parseInt(request.getParameter("count"));
        List<Product> products = productService.getProductByCount(count);
        if(products!=null){
            for(Product product:products)
                dataService.simplifyProduct(product);
            res.put("product",JSONArray.parseArray(JSON.toJSONString(products)));
            res.put("error","");
        }
        else{
            res.put("product",null);
            res.put("error","查询商品失败");
        }
        return res;
    }
}
