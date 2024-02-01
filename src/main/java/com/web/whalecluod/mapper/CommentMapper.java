package com.web.whalecluod.mapper;

import com.web.whalecluod.model.Comment;
import com.web.whalecluod.model.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CommentMapper {
    @Insert("insert into `comment`(parent_id, type, name, content, gmt_create, gmt_modified, like_count,avatar_url) values (#{parentId}, #{type}, #{name}, #{content}, #{gmtCreate}, #{gmtModified}, #{likeCount},#{avatarUrl})")
    void insert(Comment comment);

    @Select("select * from `comment` where id=#{id}")
    Comment findById(@Param("id") Long id);

    @Update("update `comment` set content=#{content}, gmt_modified=#{gmtModified} where id=#{id}")
    void update(Comment comment);

    @Delete("delete from comment where id=#{id}")
    void deleteById(@Param("id") Long id);

    @Select("select * from comment where parent_id=#{parentId}")
    List<Comment> findByParentId(@Param("parentId") Long parentId);
   @Select("select * from user where id = #{commentator}")
    List<User> findUser(@Param("commentator")int commentator);

}
