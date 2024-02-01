package com.web.whalecluod.controller;

import com.web.whalecluod.dto.PaginationDTO;
import com.web.whalecluod.mapper.UserMapper;
import com.web.whalecluod.model.User;
import com.web.whalecluod.service.QuestionService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
public class ProfileController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/profile/{action}")
    public String profile(HttpServletRequest request,
                          @PathVariable(name = "action")String action,
                          Model model,
                          @RequestParam(name = "page", defaultValue = "1") Integer page,
                          @RequestParam(name = "size", defaultValue = "5") Integer size) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null){
            return "redirect:/";
        }

        if ("questions".equals(action)){
            model.addAttribute("section","questions");
            model.addAttribute("sectionName","我的提问");
        } else if ("replies".equals(action)){
            model.addAttribute("section","replies");
            model.addAttribute("sectionName","最新回复");
        }else if("home".equals(action)){
            model.addAttribute("section","home");
            model.addAttribute("sectionName","我的资料");
        }

        PaginationDTO paginationDTO = questionService.list(user.getId(), page, size);
        if (paginationDTO.getQuestions().isEmpty()) {
            paginationDTO.setQuestions(new ArrayList<>());
        }
        model.addAttribute("pagination",paginationDTO);
        return "profile";
    }
}
