package com.web.whalecluod;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.imageio.ImageIO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class CaptchaServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private int width = 100; // 验证码宽度
    private int height = 40; // 验证码高度

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        try {
            // 生成随机字符串
            String code = generateCode();

            // 将验证码存储到 session 中
            HttpSession session = request.getSession();
            session.setAttribute("captcha", code);

            // 创建画布并绘制验证码
            BufferedImage image = generateCaptchaImage(code);

            // 输出图像
            response.setContentType("image/png");
            ImageIO.write(image, "png", response.getOutputStream());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public BufferedImage generateCaptchaImage(String code) {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, width, height);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.setColor(Color.BLACK);
        g.drawString(code, 25, 25);
        return image;
    }

    private String generateCode() {
        String chars = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder codeBuilder = new StringBuilder();
        Random rand = new Random();
        for (int i = 0; i < 6; i++) {
            int index = rand.nextInt(chars.length());
            codeBuilder.append(chars.charAt(index));
        }
        return codeBuilder.toString();
    }
}
