package com.ccb.hello.spring.boot.thymeleaf.service;

import com.ccb.hello.spring.boot.thymeleaf.entity.Toexamine;

import java.util.List;
import java.util.Map;

public interface CheckEditionService{
    public void saveToexamine(Toexamine toexamine);
    public List<Toexamine> selectToexamine(Toexamine toexamine);
    public List<Toexamine> selectDate(Map map, int currPage, int showNum);
    public void deleteInIds(String Ids);
    public void addToexamine(Toexamine toexamine);
    public void updateToexamine(Toexamine toexamine);
    public Toexamine selectToexamineById(String id);
}
