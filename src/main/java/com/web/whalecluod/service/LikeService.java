package com.web.whalecluod.service;

import com.web.whalecluod.mapper.LikeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LikeService {

    @Autowired
    private LikeMapper likeMapper;

    // 添加点赞
    public void addLike(int userId, int commentId) {
        likeMapper.addLike(userId, commentId);
        likeMapper.updateLikeCount(commentId, 1); // 增加 like_count 字段值
    }

    // 取消点赞
    public void cancelLike(int userId, int commentId) {
        likeMapper.cancelLike(userId, commentId);
        likeMapper.updateLikeCount(commentId, -1); // 减少 like_count 字段值
    }

    public boolean hasLiked(int userId, int commentId) {
        int count = likeMapper.hasLiked(userId, commentId);
        return count > 0;
    }
}
