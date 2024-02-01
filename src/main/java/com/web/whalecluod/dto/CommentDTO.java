package com.web.whalecluod.dto;

import com.web.whalecluod.model.User;
import lombok.Data;

import java.util.List;

@Data
public class CommentDTO {
    private Long parentId;
    private String avatarUrl;
    private Integer type;
    private String name;
    private String content;
    private Long id;
    private Long questionId;
    private User user;
    private int commentCount;
    private long gmtCreate;
    private long getModified;

    // 省略getter和setter方法
}
