package com.web.whalecluod.model;

import lombok.Data;

@Data
public class Login {
    private Integer id;
    private String username;
    private String password;
    private String avatarUrl;
    private String captcha;
}
