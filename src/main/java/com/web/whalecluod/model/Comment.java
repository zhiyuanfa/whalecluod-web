package com.web.whalecluod.model;

import lombok.Data;

@Data
public class Comment {
    private Long id;
    private Long parentId;
    private Integer type;
    private String name;
    private String content;
    private Long gmtCreate;
    private Long gmtModified;
    private Long likeCount;
    private String avatarUrl;

    // 省略getter和setter方法
}
