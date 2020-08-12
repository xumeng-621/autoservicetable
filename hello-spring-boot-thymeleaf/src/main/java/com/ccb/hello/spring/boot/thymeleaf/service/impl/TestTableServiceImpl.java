package com.ccb.hello.spring.boot.thymeleaf.service.impl;
import com.ccb.hello.spring.boot.thymeleaf.entity.Testtable;
import com.ccb.hello.spring.boot.thymeleaf.service.TestTableService;
import org.springframework.beans.factory.annotation.Autowired;
import com.ccb.hello.spring.boot.thymeleaf.dao.TesttableMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class TestTableServiceImpl implements TestTableService {
    @Autowired
    private TesttableMapper testtableMapper ;
    public void testAdd(String systemname,String deplymentname,String unittype,String hostname){
        Testtable testtable = new Testtable();
        testtable.setId(UUID.randomUUID().toString());
        if(!StringUtils.isEmpty(systemname)){
            testtable.setSystemname(systemname);
        }
        if(!StringUtils.isEmpty(deplymentname)){
            testtable.setDeplymentname(deplymentname);
        }
        if(!StringUtils.isEmpty(unittype)){
            testtable.setUnittype(unittype);
        }
        if(!StringUtils.isEmpty(hostname)){
            testtable.setHostname(hostname);
        }
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateTime=df.format(new Date());
        testtable.setEntertime(dateTime);
        testtableMapper.insert(testtable);
    }

    @Override
    public List<Testtable> getTestTypeByNameIsDepname(String deplymentname) {
        return testtableMapper.getTestTypeByNameIsDepname(deplymentname);
    }


}
