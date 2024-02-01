package com.web.whalecluod.service;

import com.web.whalecluod.dto.SearchResultDTO;
import com.web.whalecluod.mapper.SearchMapper;
import com.web.whalecluod.mapper.UserMapper;
import com.web.whalecluod.model.Search;
import com.web.whalecluod.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SearchService {
    @Autowired
    private SearchMapper searchMapper;
    @Autowired
    private UserMapper userMapper;

    public List<SearchResultDTO> search(String keyword) {
        List<Search> searches = searchMapper.search(keyword);
        List<SearchResultDTO> results = new ArrayList<>();
        for (Search search : searches) {
            SearchResultDTO result = new SearchResultDTO();
            User user = userMapper.findById(search.getCreator());
            result.setGmtCreate(search.getGmtCreate());
            result.setName(user.getName());
            result.setTag(search.getTag());
            result.setViewCount(search.getViewCount());
            result.setLikeCount(search.getLikeCount());
            result.setCommentCount(search.getCommentCount());
            result.setAvatarurl(user.getAvatarUrl());
            result.setId(search.getId());
            result.setTitle(search.getTitle());
            results.add(result);
        }
        return results;
    }
}

