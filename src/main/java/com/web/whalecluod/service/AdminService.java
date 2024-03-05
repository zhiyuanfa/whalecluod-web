package com.web.whalecluod.service;

import com.web.whalecluod.mapper.Admin;
import com.web.whalecluod.model.Adminlogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    private Admin adminMapper;

    public Adminlogin login(String username, String password) {
        return adminMapper.findByUsernameAndPassword(username, password);
    }
}
