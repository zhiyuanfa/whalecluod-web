package com.web.whalecluod.dto;

import lombok.Data;

@Data
public class SearchResultDTO {
    private Integer id;
    private String title;
    private String description;
    private String tag;
    private Long gmtCreate;
    private Long gmtModified;
    private Integer creator;
    private Integer viewCount;
    private Integer commentCount;
    private Integer likeCount;
    private String name;
    private String avatarurl;
}