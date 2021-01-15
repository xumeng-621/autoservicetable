package com.ccb.hello.spring.boot.thymeleaf.service.impl;

import com.ccb.hello.spring.boot.thymeleaf.dao.ToexamineMapper;
import com.ccb.hello.spring.boot.thymeleaf.entity.Toexamine;
import com.ccb.hello.spring.boot.thymeleaf.service.CheckEditionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CheckEditionServiceimpl implements CheckEditionService {
    @Autowired
    private ToexamineMapper toexamineMapper;
    public void saveToexamine(Toexamine toexamine){
        toexamineMapper.insert(toexamine);
    }
    public List<Toexamine> selectToexamine(Toexamine toexamine){
        return toexamineMapper.select(toexamine);
    }
}
