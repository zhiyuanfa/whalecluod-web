package com.web.whalecluod.controller;

import com.web.whalecluod.dto.SearchResultDTO;
import com.web.whalecluod.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/search")
public class SearchController {
    @Autowired
    private SearchService searchService;

    @PostMapping
    public String search(@RequestParam("keyword") String keyword,
                         Model model) {
        List<SearchResultDTO> results = searchService.search(keyword);
        System.out.println(results);
        model.addAttribute("results", results);
        return "search";
    }
}
