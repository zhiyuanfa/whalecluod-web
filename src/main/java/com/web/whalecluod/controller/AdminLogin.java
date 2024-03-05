package com.web.whalecluod.controller;

import com.web.whalecluod.model.Adminlogin;
import com.web.whalecluod.service.AdminService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AdminLogin {

    @Autowired
    private AdminService adminService;
    @GetMapping("/whale-admin-login")
    public String login(HttpServletRequest request){
        String referer = request.getHeader("Referer");
        request.getSession().setAttribute("referer", referer);
        return "whale-admin-login";
    }
    @PostMapping("/whale-admin-login")
    public String login(HttpServletRequest request, String username, String password){
        Adminlogin adminlogin = adminService.login(username, password);

        if(adminlogin != null){
            // 登录成功，可以进行相关操作
            return "whale-control";
        } else {
            // 登录失败，可以返回错误页面或信息
            return "whale-admin-login";
        }
    }
}
