package com.web.whalecluod.mapper;

import com.web.whalecluod.model.Login;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface LoginMapper {
    @Insert("INSERT INTO login (username, password, avatar_url) VALUES (#{username}, #{password}, #{avatarUrl})")
    void insertLogin(Login login);

    @Select("SELECT * FROM login WHERE username = #{username} AND password = #{password}")
    Login findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);
}
