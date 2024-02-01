package com.web.whalecluod.service;

import com.web.whalecluod.dto.CommentDTO;
import com.web.whalecluod.exception.CustomerException;
import com.web.whalecluod.mapper.CommentMapper;
import com.web.whalecluod.mapper.UserMapper;
import com.web.whalecluod.model.Comment;
import com.web.whalecluod.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private UserMapper userMapper;

    public void createComment(CommentDTO commentDTO) {
        Comment comment = new Comment();
        comment.setName(commentDTO.getName());
        comment.setAvatarUrl(commentDTO.getAvatarUrl());
        comment.setParentId(commentDTO.getParentId());
        comment.setContent(commentDTO.getContent());
        comment.setType(commentDTO.getType());
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setGmtModified(comment.getGmtCreate());
        comment.setLikeCount(0L);
        System.out.println(comment);

        commentMapper.insert(comment);
    }

    public void updateComment(CommentDTO commentDTO) {
        Comment comment = commentMapper.findById(commentDTO.getId());
        if (comment == null) {
            throw new CustomerException("评论不存在");
        }
        comment.setContent(commentDTO.getContent());
        comment.setGmtModified(System.currentTimeMillis());

        commentMapper.update(comment);
    }

    public void deleteComment(Long commentId) {
        Comment comment = commentMapper.findById(commentId);
        if (comment == null) {
            throw new CustomerException("评论不存在");
        }

        commentMapper.deleteById(commentId);
    }

    public List<Comment> getCommentsByParentId(Long parentId) {
        return commentMapper.findByParentId(parentId);
    }

    public Comment getCommentById(Long commentId) {
        return commentMapper.findById(commentId);
    }

    public List<User> findUserByid(int commentaror){
        return commentMapper.findUser(commentaror);
    }

}
