package com.fudan.thomasshopping.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fudan.thomasshopping.entity.Comment;
import com.fudan.thomasshopping.entity.Product;
import com.fudan.thomasshopping.service.commentService;
import com.fudan.thomasshopping.service.dataService;
import com.fudan.thomasshopping.service.productService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@RestController
public class commentController {
    @Autowired
    private commentService commentService;

    @Autowired
    private productService productService;

    @Autowired
    private dataService dataService;

    @PostMapping("/addComment")
    private JSONObject addComment(@RequestBody JSONObject jsonObject){
        JSONObject res = new JSONObject();
        if(!dataService.checkParams(jsonObject, Collections.singletonList("productId"))){
           res.put("id",null);
           res.put("error","存在参数为空");
           return res;
        }
        Long id = jsonObject.getLong("productId");
        Product product = productService.getProductById(id);
        Comment comment = JSON.toJavaObject(jsonObject,Comment.class);
        comment.setProduct(product);
        comment.setCreated_time(new Date());
        comment = commentService.addComment(comment);
        if(product!=null&&comment!=null){
            res.put("id",comment.getId());
            res.put("error","");
        }
        else{
            res.put("id",null);
            res.put("error","添加评论失败");
        }

        return res;
    }

    @PostMapping("/replyComment")
    private JSONObject updateComment(@RequestBody JSONObject jsonObject){
        JSONObject res = new JSONObject();
        if(!dataService.checkParams(jsonObject,Arrays.asList("commentId","reply"))){
            res.put("error","存在参数为空");
            return res;
        }
        Long id = jsonObject.getLong("commentId");
        String reply = jsonObject.getString("reply");
        Comment comment = commentService.getComment(id);
        if(comment!=null){
            comment.setReply(reply);
            comment.setReply_time(new Date());
            if(commentService.updateComment(comment)!=null){
                res.put("error","");
                return res;
            }
        }
        res.put("error","更新评论失败");
        return res;
    }

    @GetMapping("/getComments")
    private JSONObject getComments() {
        List<Comment> comments = commentService.getAllComments();
        JSONObject res = new JSONObject();
        if(comments!=null){
            for(Comment comment:comments){
                  dataService.simplifyComment(comment);
            }
            res.put("comments", JSONArray.parseArray(JSON.toJSONString(comments)));
            res.put("error","");
        }
        else{
            res.put("comments",null);
            res.put("error","查询评论信息失败");
        }
        return res;
    }

    @GetMapping("deleteCommentById")
    private JSONObject deleteCommentById(HttpServletRequest request){
        JSONObject res = new JSONObject();
        if(!dataService.checkParams2(request,Collections.singletonList("commentId"))){
            res.put("error","存在参数为空");
            return res;
        }
        Long id = Long.parseLong(request.getParameter("commentId"));
        if(commentService.deleteComment(id))
            res.put("error","");
        else
            res.put("error","删除评论失败");
        return res;
    }

    @GetMapping("getCommentsByProduct")
    private JSONObject getCommentsByProduct(HttpServletRequest request){
        JSONObject res = new JSONObject();
        if(!dataService.checkParams2(request,Collections.singletonList("productId"))){
            res.put("comments",null);
            res.put("error","存在参数为空");
            return res;
        }
        Long id = Long.parseLong(request.getParameter("productId"));
        Product product = productService.getProductById(id);
        if(product!=null){
            List<Comment> comments = commentService.getCommentByProduct(product);
            if(comments!=null){
                for(Comment comment:comments){
                    dataService.simplifyComment(comment);
                }
                res.put("comments",JSONArray.parseArray(JSON.toJSONString(comments)));
                res.put("error","");
                return res;
            }
        }
        res.put("comments",null);
        res.put("error","查询评论信息失败");
        return res;
    }
}
