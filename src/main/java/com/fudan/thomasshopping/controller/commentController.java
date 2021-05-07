//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.fudan.thomasshopping.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fudan.thomasshopping.entity.Comment;
import com.fudan.thomasshopping.service.commentService;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class commentController {
    @Autowired
    private commentService commentService;

    public commentController() {
    }

    @PostMapping({"/addComment"})
    private JSONObject addComment(@RequestBody JSONObject jsonObject) {
        Comment comment = (Comment)JSON.toJavaObject(jsonObject, Comment.class);
        comment.setCreated_time(new Date());
        JSONObject res = new JSONObject();
        if (this.commentService.addComment(comment) != null) {
            res.put("error", "");
        } else {
            res.put("error", "添加评论失败");
        }

        return res;
    }

    @PostMapping({"/replyComment"})
    private JSONObject updateComment(@RequestBody JSONObject jsonObject) {
        Long id = jsonObject.getLong("commentId");
        String reply = jsonObject.getString("reply");
        Comment comment = this.commentService.getComment(id);
        comment.setReply(reply);
        comment.setReply_time(new Date());
        JSONObject res = new JSONObject();
        if (this.commentService.updateComment(comment) != null) {
            res.put("error", "");
        } else {
            res.put("error", "更新评论失败");
        }

        return res;
    }

    @GetMapping({"/getComments"})
    private JSONObject getComments() {
        List<Comment> comments = this.commentService.getAllComments();
        JSONObject res = new JSONObject();
        if (comments != null) {
            res.put("comments", JSONArray.parseArray(JSON.toJSONString(comments)));
            res.put("error", "");
        } else {
            res.put("comments", (Object)null);
            res.put("error", "查询评论信息失败");
        }

        return res;
    }

    @GetMapping({"deleteCommentById"})
    private JSONObject deleteCommentById(HttpServletRequest request) {
        Long id = Long.parseLong(request.getParameter("commentId"));
        JSONObject res = new JSONObject();
        if (this.commentService.deleteComment(id)) {
            res.put("error", "");
        } else {
            res.put("error", "删除评论失败");
        }

        return res;
    }
}
