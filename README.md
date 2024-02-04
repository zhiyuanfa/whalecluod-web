//2024.1.16
项目创建，完成了页面导航栏布局样式，创建表
sql语句:
create table web.user(
id INT AUTO_INCREMENT PRIMARY KEY,
account_id VARCHAR(100),
name VARCHAR(50),
token CHAR(36),
gmt_create BIGINT,
gmt_modified BIGINT
);

ALTER TABLE USER ADD bio VARCHAR(256) NULL;

ALTER TABLE USER ADD avatar_url VARCHAR(100) NULL;


//2024.1.17
加入github授权登录

//2024.1.18
加入token验证登录状态，实现导航栏登录与未登录样式变化

//2024.1.19
实现发布问题，创建表
sql语句:
create table web.question(
id INT AUTO_INCREMENT PRIMARY KEY,
title VARCHAR(50),
description TEXT,
gmt_create BIGINT,
gmt_modified BIGINT,
creator INT,
comment_count INT DEFAULT 0,
view_count INT DEFAULT 0,
like_count INT DEFAULT 0,
tag VARCHAR(256)
);


//2024.1.20
实现问题展示列表

//2024.1.21
去除github授权登录，实现登录注册功能，创建表login

//2024.1.22
实现问题回复功能，创建表
sql语句:
CREATE TABLE web.comment (
id BIGINT AUTO_INCREMENT PRIMARY KEY,
parent_id BIGINT NOT NULL,
type INT NOT NULL,
commentator INT NOT NULL,
content TEXT,
gmt_create BIGINT NOT NULL,
gmt_modified BIGINT NOT NULL,
like_count BIGINT DEFAULT 0
);

//2024.1.24
开始写二级回复，创建表
    sub_comment_id 代表父问题的id
    sql语句:
CREATE TABLE web.sub_comment (
id BIGINT AUTO_INCREMENT PRIMARY KEY,
comment_id BIGINT NOT NULL,
sub_content TEXT,
gmt_create BIGINT NOT NULL,
gmt_modified BIGINT NOT NULL
);

//2024.2.4
加入icp备案号