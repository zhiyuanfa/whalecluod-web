package com.web.whalecluod.mapper;

import com.web.whalecluod.model.User;
import org.apache.ibatis.annotations.*;

import java.io.Serial;
@Mapper
public interface UserMapper {
    @Insert("insert into `user`(name,account_id,token,gmt_create,gmt_modified,avatar_Url) values (#{name},#{accountId},#{token},#{gmtCreate},#{gmtModified}),#{avatarUrl}")
    void insert(User user);
    @Select("select * from `user` where token = #{token}")
    User findByToken(@Param("token") String token);
    @Select("select * from `user` where id = #{id}")
    User findById(Integer creator);
    @Select("select * from `user` where account_id = #{accountId}")
    User findByAccountId(@Param("accountId") String accountId);
    @Update("update user set name = #{name},token = #{token},gmt_modified = #{gmtModified},avatar_Url = #{avatarUrl} where id = #{id}")
    void update(User user);

    // 根据用户名和密码查询用户信息
    @Select("select * from `user` where name = #{username} and token = #{password}")
    User findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);
}
