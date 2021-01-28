package com.ccb.hello.spring.boot.thymeleaf.dao;

import com.ccb.hello.spring.boot.thymeleaf.entity.Toexamine;
import tk.mybatis.MyMapper;

import java.util.List;
import java.util.Map;

public interface ToexamineMapper extends MyMapper<Toexamine> {
    public List<Toexamine> slectData(Map map);
    public void deleteInIds(String[] ids);
}