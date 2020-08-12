package com.ccb.hello.spring.boot.thymeleaf.service;

import com.ccb.hello.spring.boot.thymeleaf.dao.TesttableMapper;
import com.ccb.hello.spring.boot.thymeleaf.entity.Testtable;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

public interface TestTableService {
    public void testAdd(String systemname,String deplymentname,String unittype,String hostname);

    List<Testtable> getTestTypeByNameIsDepname(String deplymentname);
}
