package com.ccb.hello.spring.boot.thymeleaf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@MapperScan(basePackages ="com.ccb.hello.spring.boot.thymeleaf.dao")
@SpringBootApplication
public class HelloSpringBootThymeleafApplication {

    public static void main(String[] args) {
        SpringApplication.run(HelloSpringBootThymeleafApplication.class, args);
    }

}
