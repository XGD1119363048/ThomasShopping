package com.fudan.thomasshopping.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fudan.thomasshopping.entity.Logistic;
import com.fudan.thomasshopping.entity.Order;
import com.fudan.thomasshopping.service.dataService;
import com.fudan.thomasshopping.service.logisticService;
import com.fudan.thomasshopping.service.orderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@RestController
public class logisticController {
    @Autowired
    private logisticService logisticService;

    @Autowired
    private dataService dataService;

    @Autowired
    private orderService orderService;

    @PostMapping("/addLogistic")
    private JSONObject addLogistic(@RequestBody JSONObject jsonObject){
        JSONObject res = new JSONObject();
        if(!dataService.checkParams(jsonObject, Arrays.asList("orderId","status","date"))){
            res.put("error","存在参数为空");
            res.put("id",null);
            return res;
        }
        Long orderId = jsonObject.getLong("orderId");
        Order order = orderService.getOrderById(orderId);
        if(order!=null&&order.getLogistic()==null){
            Logistic logistic = new Logistic();
            logistic.setOrder(order);
            logistic.setDates(Collections.singletonList(jsonObject.getDate("date")));
            logistic.setStatus(Collections.singletonList(jsonObject.getString("status")));
            logistic = logisticService.addLogistic(logistic);
            if(logistic!=null){
                order.setLogistic(logistic);
                orderService.updateOrder(order);
                res.put("error","");
                res.put("id",logistic.getId());
                return res;
            }
        }
        res.put("error","添加物流信息失败");
        res.put("id",null);
        return res;
    }

    @PostMapping("/updateLogistic")
    private JSONObject updateLogistic(@RequestBody JSONObject jsonObject){
        JSONObject res = new JSONObject();
        if(!dataService.checkParams(jsonObject,Arrays.asList("orderId","status","date"))){
            res.put("error","存在参数为空");
            return res;
        }
        Long orderId = jsonObject.getLong("orderId");
        Order order = orderService.getOrderById(orderId);

        if(order!=null&&order.getLogistic()!=null){
            Logistic logistic = order.getLogistic();
            List<String> status = logistic.getStatus();
            List<Date> dates = logistic.getDates();
            status.add(jsonObject.getString("status"));
            dates.add(jsonObject.getDate("date"));
            logistic.setStatus(status);
            logistic.setDates(dates);
            logistic = logisticService.updateLogistic(logistic);
            if(logistic!=null){
                res.put("error","");
                return res;
            }
        }
        res.put("error","更新物流信息失败");
        return res;
    }

    @GetMapping("/getLogisticByOrder")
    private JSONObject getLogisticByOrder(HttpServletRequest request){
        JSONObject res = new JSONObject();
        if(!dataService.checkParams2(request,Collections.singletonList("orderId"))){
            res.put("error","存在参数为空");
            res.put("logistic",null);
            return res;
        }
        Long orderId = Long.parseLong(request.getParameter("orderId"));
        Order order = orderService.getOrderById(orderId);
        if(order!=null){
            Logistic logistic = logisticService.getLogistic(order);
            if(logistic!=null){
                logistic = dataService.simplifyLogistic(logistic);
                res.put("logistic",JSONObject.parseObject(JSON.toJSONString(logistic)));
                res.put("error","");
                return res;
            }
        }
        res.put("logistic",null);
        res.put("error","查询物流信息失败");
        return res;
    }
}
