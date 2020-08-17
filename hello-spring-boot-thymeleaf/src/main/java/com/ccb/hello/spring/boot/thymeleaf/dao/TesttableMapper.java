package com.ccb.hello.spring.boot.thymeleaf.dao;

import com.ccb.hello.spring.boot.thymeleaf.entity.Testtable;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.MyMapper;

import java.util.List;

public interface TesttableMapper extends MyMapper<Testtable> {

    List<Testtable> getTestTypeByNameIsDepname(@Param("deplymentname") String deplymentname);
    public void testUpdate(Testtable testtable);
    public void testDeleteById(String id);
    public List<Testtable> testSelect(Testtable testtable);

}