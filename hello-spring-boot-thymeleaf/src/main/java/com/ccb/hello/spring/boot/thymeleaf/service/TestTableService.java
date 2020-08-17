package com.ccb.hello.spring.boot.thymeleaf.service;

import com.ccb.hello.spring.boot.thymeleaf.dao.TesttableMapper;
import com.ccb.hello.spring.boot.thymeleaf.entity.Testtable;
import com.ccb.hello.spring.boot.thymeleaf.util.ResponseEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

public interface TestTableService {
    public ResponseEntity testAdd(String systemname, String deplymentname, String unittype, String hostname);
    public ResponseEntity testUpdate(String id,String systemname, String deplymentname, String unittype, String hostname);
    public ResponseEntity testDelete(String id);
    public ResponseEntity testSelect(String id,String systemname, String deplymentname, String unittype, String hostname);

    List<Testtable> getTestTypeByNameIsDepname(String deplymentname);
}
