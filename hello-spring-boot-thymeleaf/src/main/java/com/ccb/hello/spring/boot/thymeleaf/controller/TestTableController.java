package com.ccb.hello.spring.boot.thymeleaf.controller;

import com.ccb.hello.spring.boot.thymeleaf.service.TestTableService;
import com.ccb.hello.spring.boot.thymeleaf.service.UserService;
import com.ccb.hello.spring.boot.thymeleaf.util.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class TestTableController {
    @Autowired
    private TestTableService testTableService;
    @Autowired
    private UserService userService;
    @RequestMapping(value = "/testtablefront/totestadd",method = RequestMethod.GET)
    public String toTestAdd(Model model){

      return "/testtablefront/testadd";
    }
    @RequestMapping(value = "/testtablefront/testadd",method = RequestMethod.POST)
    public String testAdd(@RequestParam(value = "systemname")String systemname,
                          @RequestParam(value = "deplymentname")String deplymentname,
                          @RequestParam(value = "unittype")String unittype,
                          @RequestParam(value = "hostname")String hostname, Model model){

       ResponseEntity re = testTableService.testAdd(systemname,deplymentname,unittype,hostname);
       model.addAttribute("re",re);
        return "/testtablefront/testadd";
    }
    @RequestMapping(value = "/testtablefront/totestselect",method = RequestMethod.GET)
    public String toTestSelect(Model model){

        return "/testtablefront/testselect";
    }
    @RequestMapping(value = "/testtablefront/testselect",method = RequestMethod.POST)
    private String testSelect( @RequestParam(value = "id")String id,
                              @RequestParam(value = "systemname")String systemname,
                              @RequestParam(value = "deplymentname")String deplymentname,
                              @RequestParam(value = "unittype")String unittype,
                              @RequestParam(value = "hostname")String hostname,Model model){
        ResponseEntity re = testTableService.testSelect(id,systemname,deplymentname,unittype,hostname);
        model.addAttribute("re",re);
        return "/testtablefront/testselect";
    }
    @RequestMapping(value = "/testtablefront/testupdate",method = RequestMethod.GET)
    private String testUpdate( @RequestParam(value = "id")String id,
                               @RequestParam(value = "systemname")String systemname,
                               @RequestParam(value = "deplymentname")String deplymentname,
                               @RequestParam(value = "unittype")String unittype,
                               @RequestParam(value = "hostname")String hostname,Model model){
        ResponseEntity re = testTableService.testUpdate(id,systemname,deplymentname,unittype,hostname);
        model.addAttribute("re",re);
        return "/testtablefront/testupdate";
    }
    @RequestMapping(value = "/testtablefront/testdelete",method = RequestMethod.GET)
    private String testDelete(@RequestParam(value = "id")String id,Model model){
        ResponseEntity re = testTableService.testDelete(id);
        model.addAttribute("re",re);
        return "/testtablefront/testdelete";
    }
    @RequestMapping(value = "/testtablefront/useradd",method = RequestMethod.GET)
    private String userAdd(@RequestParam(value = "name")String name){
        userService.userAdd(name);
       return null;
    }
}
