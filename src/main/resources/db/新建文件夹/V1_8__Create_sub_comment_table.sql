CREATE TABLE web.sub_comment (
                                 id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                 comment_id BIGINT NOT NULL,
                                 sub_content TEXT,
                                 gmt_create BIGINT NOT NULL,
                                 gmt_modified BIGINT NOT NULL
);