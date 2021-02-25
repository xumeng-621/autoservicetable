package com.ccb.hello.spring.boot.thymeleaf.service.impl;

import com.ccb.hello.spring.boot.thymeleaf.dao.ToexamineMapper;
import com.ccb.hello.spring.boot.thymeleaf.entity.Toexamine;
import com.ccb.hello.spring.boot.thymeleaf.service.CheckEditionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

@Service
@Transactional(rollbackForClassName={"Exception"},value = "firstDataSourceTransactionManger")
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
        List<Toexamine> list = toexamineMapper.findData(map);
        return list;
    }
    public void addToexamine(Toexamine toexamine){
        toexamineMapper.insert(toexamine);
    }
    public void updateToexamine(Toexamine toexamine){
        toexamineMapper.updateByPrimaryKey(toexamine);
    }
    public void deleteInIds(String Ids){
        if(!StringUtils.isEmpty(Ids)){
            String idstr[] = Ids.split(",");
            //toexamineMapper.deleteInIds(idstr);
            for(String id :idstr){
                toexamineMapper.deleteInIds(id);
            }
        }

    }
    public Toexamine selectToexamineById(String id){
        return toexamineMapper.selectByPrimaryKey(id);
    }
    public  int findTotalNumberBy(Map map){
        return toexamineMapper.findTotalNumberBy(map);
    }
    public List<String> findVersionDate(){
        return toexamineMapper.findVersionDate();
    }
}
