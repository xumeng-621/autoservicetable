package com.ccb.hello.spring.boot.thymeleaf.service.impl;
import com.ccb.hello.spring.boot.thymeleaf.entity.Testtable;
import com.ccb.hello.spring.boot.thymeleaf.service.TestTableService;
import com.ccb.hello.spring.boot.thymeleaf.util.ResponseEntity;
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
    public ResponseEntity testAdd(String systemname, String deplymentname, String unittype, String hostname){
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
        ResponseEntity re = new ResponseEntity();
        try {
            testtableMapper.insert(testtable);
            re.setCode("200");
            re.setMessage("添加成功");
        }catch(Exception e){
           e.printStackTrace();
           re.setCode("500");
           re.setMessage("添加失败，网络异常");
        }
        return re;
    }

    @Override
    public ResponseEntity testUpdate(String id, String systemname, String deplymentname, String unittype, String hostname) {
        ResponseEntity re = new ResponseEntity();
        Testtable testtable = new Testtable();
        if("".equals(id)||id==null){
            re.setCode("500");
            re.setMessage("id异常");
            return re;
        }
        testtable.setId(id);
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
        testtableMapper.testUpdate(testtable);
        return re;
    }


    @Override
    public ResponseEntity testDelete(String id) {
        ResponseEntity re = new ResponseEntity();
        testtableMapper.testDeleteById(id);
        return re;
    }

    @Override
    public ResponseEntity testSelect(String id,String systemname, String deplymentname, String unittype, String hostname) {
        ResponseEntity re = new ResponseEntity();
        Testtable testtable = new Testtable();
        if(!StringUtils.isEmpty(id)){
            testtable.setId(id);
        }
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
        List<Testtable> testtableList = testtableMapper.testSelect(testtable);
        re.setResult(testtableList);
        return re;
    }


    @Override
    public List<Testtable> getTestTypeByNameIsDepname(String deplymentname) {
        return testtableMapper.getTestTypeByNameIsDepname(deplymentname);
    }


}
