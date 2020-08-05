package com.ccb.hello.spring.boot.thymeleaf.controller;

import com.ccb.hello.spring.boot.thymeleaf.service.TestTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@Controller
public class TestTableController {
    @Autowired
    private TestTableService testTableService;
    @RequestMapping(value = "/testtablefront/totestadd",method = RequestMethod.GET)
    public String toTestAdd(Model model){

      return "/testtablefront/testadd";
    }
    @RequestMapping(value = "/testtablefront/testadd",method = RequestMethod.POST)
    public String testAdd(@RequestParam(value = "systemname")String systemname,
                          @RequestParam(value = "deplymentname")String deplymentname,
                          @RequestParam(value = "unittype")String unittype,
                          @RequestParam(value = "hostname")String hostname){

        testTableService.testAdd(systemname,deplymentname,unittype,hostname);
        return "/testtablefront/testadd";
    }
}
