package com.web.whalecluod.controller;

import com.web.whalecluod.dto.CommentDTO;
import com.web.whalecluod.mapper.QuestionMapper;
import com.web.whalecluod.model.User;
import com.web.whalecluod.service.CommentService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.websocket.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class CommentController {
    @Autowired
    private CommentService commentService;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private QuestionMapper questionMapper;

    @PostMapping(value = "/comment", consumes = "application/json")
    @ResponseBody
    public ResponseEntity<?> post(@RequestBody CommentDTO commentDTO) {
        if (commentDTO.getContent() == null || commentDTO.getContent().isEmpty()) {
            return ResponseEntity.badRequest().body("评论内容不能为空");
        }
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("用户未登录");
        }
        // 处理评论数据
        commentDTO.setName(user.getName());
        System.out.println(user.getName());
        commentDTO.setAvatarUrl(user.getAvatarUrl());
        commentService.createComment(commentDTO);

        Long parentId = commentDTO.getParentId(); // 获取父级评论的ID
        if (parentId != null) { // 如果是回复
            Integer commentCount = questionMapper.countCommentsByParentId(parentId);
            questionMapper.updateComment(parentId, commentCount);
        } else { // 如果是新评论
            questionMapper.updateComment(commentDTO.getQuestionId(), 1);
        }

        return ResponseEntity.ok("回复成功");
}


}
