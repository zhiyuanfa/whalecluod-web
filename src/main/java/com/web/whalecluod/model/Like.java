package com.web.whalecluod.model;

import lombok.Data;

@Data
public class Like {
    private int id;
    private int commentId;
    private int userId;
}
