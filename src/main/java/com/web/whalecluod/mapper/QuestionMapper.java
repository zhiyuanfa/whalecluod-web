package com.web.whalecluod.mapper;

import com.web.whalecluod.model.Question;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface QuestionMapper {

    @Insert("insert into `question`(title,description,gmt_create,gmt_modified,creator,tag) values (#{title},#{description},#{gmtCreate},#{gmtModified},#{creator},#{tag})")
    void create(Question question);
    @Select("select * from question limit #{offset},#{size}")
    List<Question> list(@Param(value = "offset") Integer offset, @Param(value = "size") Integer size);

    @Select("select count(1) from question")
    Integer count();
    @Select("select * from question where creator = #{userId} limit #{offset},#{size}")
    List<Question> listByUserId(@Param(value = "userId") Integer userId,@Param(value = "offset") Integer offset, @Param(value = "size") Integer size);
    @Select("select count(1) from question where  creator = #{userId}")
    Integer countByUserId(@Param("userId") Integer userId);
    @Select("select * from question where id = #{Id}")
    Question getById(@Param("Id")Integer id);

    @Update("update question set view_count = #{viewCount} where id = #{id}")
    void updateViewCount(@Param("id") Integer id, @Param("viewCount") Integer viewCount);
    @Update("update question set comment_count = #{commentCount} where id = #{id}")
    void updateComment(@Param("id")Long id,@Param("commentCount") Integer commentCount);

    @Select("select count(1) from comment where parent_id = #{parentId}")
    Integer countCommentsByParentId(@Param("parentId") Long parentId);

}
