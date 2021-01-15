package com.ccb.hello.spring.boot.thymeleaf.service;

import com.ccb.hello.spring.boot.thymeleaf.entity.Toexamine;

import java.util.List;

public interface CheckEditionService{
    public void saveToexamine(Toexamine toexamine);
    public List<Toexamine> selectToexamine(Toexamine toexamine);
}
