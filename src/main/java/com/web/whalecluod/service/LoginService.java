package com.web.whalecluod.service;

import com.web.whalecluod.mapper.LoginMapper;
import com.web.whalecluod.mapper.UserMapper;
import com.web.whalecluod.model.Login;
import com.web.whalecluod.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class LoginService {
    @Autowired
    private LoginMapper loginMapper;

    @Autowired
    private UserMapper userMapper;

    public void register(String username, String password, String avatarUrl) {
        // 创建登录信息
        Login login = new Login();
        login.setUsername(username);
        login.setPassword(password);
        login.setAvatarUrl(avatarUrl);

        // 插入登录表
        loginMapper.insertLogin(login);
    }


    public User login(String username, String password) {
        // 根据用户名和密码查询登录信息
        Login login = loginMapper.findByUsernameAndPassword(username, password);

        if (login != null) {
            // 登录成功，查询用户是否存在
            User dbUser = userMapper.findByAccountId(login.getId().toString());
            if (dbUser == null) {
                // 如果用户不存在，则创建用户
                User user = new User();
                user.setName(login.getUsername());
                user.setAvatarUrl(login.getAvatarUrl());
                user.setAccountId(login.getId().toString());
                user.setToken(UUID.randomUUID().toString());
                user.setGmtCreate(System.currentTimeMillis());
                user.setGmtModified(user.getGmtCreate());
                userMapper.insert(user);
                return user;
            } else {
                // 如果用户已经存在，则更新用户的登录信息
                dbUser.setGmtModified(System.currentTimeMillis());
                dbUser.setToken(UUID.randomUUID().toString());
                dbUser.setAvatarUrl(login.getAvatarUrl());
                dbUser.setName(login.getUsername());
                userMapper.update(dbUser);
                return dbUser;
            }
        }

        return null; // 登录失败
    }
}
