package com.web.whalecluod.mapper;

import org.apache.ibatis.annotations.*;

@Mapper
public interface LikeMapper {

    @Insert("INSERT INTO `user_like` (user_id, comment_id) VALUES (#{userId}, #{commentId})")
    void addLike(@Param("userId") int userId, @Param("commentId") int commentId);

    @Delete("DELETE FROM `user_like` WHERE user_id = #{userId} AND comment_id = #{commentId}")
    void cancelLike(@Param("userId") int userId, @Param("commentId") int commentId);

    @Select("SELECT COUNT(*) FROM `user_like` WHERE user_id = #{userId} AND comment_id = #{commentId}")
    int hasLiked(@Param("userId") int userId, @Param("commentId") int commentId);

    @Update("UPDATE `comment` SET like_count = like_count + #{count} WHERE id = #{commentId}")
    void updateLikeCount(@Param("commentId") int commentId, @Param("count") int count);
}