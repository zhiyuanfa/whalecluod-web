package com.web.whalecluod.controller;

import com.web.whalecluod.dto.PaginationDTO;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.web.whalecluod.model.Login;
import com.web.whalecluod.model.User;
import com.web.whalecluod.service.LoginService;
import com.web.whalecluod.service.QuestionService;
import com.web.whalecluod.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Controller
public class LoginController {
    private LoginService loginService;
    private UserService userService;


    @Autowired
    public LoginController(LoginService loginService, UserService userService) {
        this.loginService = loginService;
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login(HttpServletRequest request) {
        // 在登录页面中获取当前页面的 URL 并存储到 session 中
        String referer = request.getHeader("Referer");
        request.getSession().setAttribute("referer", referer);
        return "login";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/register")
    public String register(Login login, @RequestParam("avatar") MultipartFile file) {
        String avatarUrl = uploadToOSS(file);
        loginService.register(login.getUsername(), login.getPassword(), avatarUrl);
        return "redirect:/login";
    }

    private String uploadToOSS(MultipartFile file) {
        String endpoint = "";  // 替换为您的阿里云 OSS 地域节点
        String accessKeyId = "";  // 替换为您的阿里云访问密钥 ID
        String accessKeySecret = "";  // 替换为您的阿里云访问密钥 Secret
        String bucketName = "";  // 替换为您的阿里云 OSS 存储桶名称

        String folderName = "";  // 指定要上传的文件夹名称
        String fileName = folderName + "/" + UUID.randomUUID().toString() + ".jpg";  // 使用文件夹名称作为文件的前缀

        try (InputStream inputStream = file.getInputStream()) {
            OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
            ossClient.putObject(bucketName, fileName, inputStream);
            ossClient.shutdown();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "https://" + bucketName + "." + endpoint + "/" + fileName;
    }


    @PostMapping("/login")
    public String login(Login login, Model model, HttpServletRequest request) {


        User user = loginService.login(login.getUsername(), login.getPassword());
        if (user != null) {

            // 登录成功，将用户对象保存到 session 中
            request.getSession().setAttribute("user", user);
            String referer = (String) request.getSession().getAttribute("referer");
            if (referer != null && !referer.contains("/login")) {
                return "redirect:" + referer;
            } else {
                return "redirect:/";
            }
        } else {
            // 登录失败，返回错误消息
            String errorMessage = "用户名或密码错误";
            model.addAttribute("error", errorMessage);
            return "login";
        }
    }
}
