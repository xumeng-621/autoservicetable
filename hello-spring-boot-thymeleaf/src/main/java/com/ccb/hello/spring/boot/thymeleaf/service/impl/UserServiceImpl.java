package com.ccb.hello.spring.boot.thymeleaf.service.impl;

import com.ccb.hello.spring.boot.thymeleaf.dao2.UserMapper;
import com.ccb.hello.spring.boot.thymeleaf.entity.User;
import com.ccb.hello.spring.boot.thymeleaf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    public void userAdd(String name){
        User user = new User();
        if(!StringUtils.isEmpty(name)){
            user.setName(name);
        }
        userMapper.insert(user);
    }
}
