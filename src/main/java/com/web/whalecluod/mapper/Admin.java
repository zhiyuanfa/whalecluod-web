package com.web.whalecluod.mapper;

import com.web.whalecluod.model.Adminlogin;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface Admin {
    @Select("SELECT * FROM admin WHERE name = #{username} AND passwd = #{password}")
    Adminlogin findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);
}
