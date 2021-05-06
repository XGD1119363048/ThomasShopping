package com.fudan.thomasshopping.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fudan.thomasshopping.entity.Comment;
import com.fudan.thomasshopping.service.commentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
public class commentController {
    @Autowired
    private commentService commentService;

    @PostMapping("/addComment")
    private JSONObject addComment(@RequestBody JSONObject jsonObject){
        Comment comment = JSON.toJavaObject(jsonObject,Comment.class);
        comment.setCreated_time(new Date());
        JSONObject res = new JSONObject();
        if(commentService.addComment(comment)!=null)
            res.put("error","");
        else
            res.put("error","添加评论失败");
        return res;
    }

    @PostMapping("/replyComment")
    private JSONObject updateComment(@RequestBody JSONObject jsonObject){
        Long id = jsonObject.getLong("commentId");
        String reply = jsonObject.getString("reply");
        Comment comment = commentService.getComment(id);
        comment.setReply(reply);
        comment.setReply_time(new Date());
        JSONObject res = new JSONObject();
        if(commentService.updateComment(comment)!=null)
            res.put("error","");
        else
            res.put("error","更新评论失败");
        return res;
    }

    @GetMapping("/getComments")
    private JSONObject getComments() {
        List<Comment> comments = commentService.getAllComments();
        JSONObject res = new JSONObject();
        if(comments!=null){
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
    private JSONObject deleteCommentById(@RequestBody JSONObject jsonObject){
        Long id = jsonObject.getLong("commentId");
        JSONObject res = new JSONObject();
        if(commentService.deleteComment(id))
            res.put("error","");
        else
            res.put("error","删除评论失败");
        return res;
    }
}
