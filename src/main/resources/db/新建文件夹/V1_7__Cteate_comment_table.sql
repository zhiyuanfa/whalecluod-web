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
