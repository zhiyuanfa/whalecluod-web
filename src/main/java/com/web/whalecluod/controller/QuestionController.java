package com.web.whalecluod.controller;

import com.web.whalecluod.dto.QuestionDTO;
import com.web.whalecluod.mapper.QuestionMapper;
import com.web.whalecluod.model.Comment;
import com.web.whalecluod.service.CommentService;
import com.web.whalecluod.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class QuestionController {
    @Autowired
    private QuestionService questionService;
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private CommentService commentService;

    @GetMapping("/question/{id}")
    public String question(@PathVariable(name = "id") Integer id,
                           Model model){
        QuestionDTO questionDTO = questionService.getById(id);
        System.out.println(questionDTO);

        List<Comment> comments = commentService.getCommentsByParentId(Long.valueOf(id));
        // 增加阅读数
        int updatedViewCount = questionDTO.getViewCount() + 1;
        questionDTO.setViewCount(updatedViewCount);

        // 更新数据库中的阅读数
        questionMapper.updateViewCount(id, updatedViewCount);

        model.addAttribute("question", questionDTO);
        System.out.println(questionDTO);
        model.addAttribute("comments", comments); // 将评论列表添加到模型中

        return "question";
    }


}

