package com.web.whalecluod.controller;

import com.web.whalecluod.model.User;
import com.web.whalecluod.service.LikeService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/like")
public class LikeController {

    @Autowired
    private LikeService likeService;

    @PostMapping("/add")
    public ResponseEntity<String> addLike(@RequestParam int commentId, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null){
            return ResponseEntity.ok("login");
        } else {
            int userId = user.getId();
            if (likeService.hasLiked(userId, commentId)) {
                likeService.cancelLike(userId, commentId);
                return ResponseEntity.ok("取消点赞成功");
            } else {
                likeService.addLike(userId, commentId);
                return ResponseEntity.ok("点赞成功");
            }
        }
    }
}
