package com.ccb.hello.spring.boot.thymeleaf.dao;

import com.ccb.hello.spring.boot.thymeleaf.entity.Testtable;
import tk.mybatis.MyMapper;

import java.util.List;

public interface TesttableMapper extends MyMapper<Testtable> {
    public List<Testtable> getTestTypeByNameIsDepname(String str);
    public Testtable testUpdate(Testtable testtable);
    public String testDeleteById(String id);
    public List<Testtable> testSelect(Testtable testtable);
}