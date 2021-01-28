package com.ccb.hello.spring.boot.thymeleaf.service.impl;

import com.ccb.hello.spring.boot.thymeleaf.dao.ToexamineMapper;
import com.ccb.hello.spring.boot.thymeleaf.entity.Toexamine;
import com.ccb.hello.spring.boot.thymeleaf.service.CheckEditionService;
import org.apache.commons.collections4.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
    public List<Toexamine> selectDate(Map map, int currPage, int showNum){
        map.put("currIndex", (currPage-1)*showNum);
        map.put("showNum", showNum);
        List<Toexamine> list = toexamineMapper.slectData(map);
        return list;
    }
    public void addToexamine(Toexamine toexamine){
        toexamineMapper.insert(toexamine);
    }
    public void updateToexamine(Toexamine toexamine){
        toexamineMapper.updateByPrimaryKey(toexamine);
    }
    public void deleteInIds(String Ids){
        String[] deleteId = Ids.split(",");
        toexamineMapper.deleteInIds(deleteId);
    }
    public Toexamine selectToexamineById(String id){
        return toexamineMapper.selectByPrimaryKey(id);
    }
}
