package com.web.whalecluod.mapper;

import com.web.whalecluod.model.Search;
import com.web.whalecluod.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SearchMapper {
    /**
     * 搜索问题
     */
    @Select("SELECT id,title,description,creator,tag,gmt_create,view_count,like_count,comment_count FROM question WHERE (title LIKE CONCAT('%', #{keyword}, '%') OR description LIKE CONCAT('%', #{keyword}, '%')) AND title LIKE CONCAT('%', 'bug', '%')")
    List<Search> search(@Param("keyword") String keyword);
}
