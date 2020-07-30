package com.ccb.hello.spring.boot.thymeleaf;

import com.ccb.hello.spring.boot.thymeleaf.dao.TesttableMapper;
import com.ccb.hello.spring.boot.thymeleaf.entity.Testtable;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest(classes = HelloSpringBootThymeleafApplication.class)
@Transactional
@Rollback
public class HelloSpringBootThymeleafApplicationTests {
   @Autowired
   private TesttableMapper testtableMapper;
   @Test
    public void testSelect(){
       List<Testtable> testtables = testtableMapper.selectAll();
       for (Testtable tt :testtables){
           System.out.println(tt.getDeplymentname()+">>>>>>>>>>"+tt.getSystemname());
       }
   }

}
