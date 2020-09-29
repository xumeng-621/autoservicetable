package com.ccb.hello.spring.boot.thymeleaf.entity;

import org.springframework.boot.convert.DataSizeUnit;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    /**
     * 获取唯一标识
     *
     * @return id - 唯一标识
     */
    public int getId() {
        return id;
    }

    /**
     * 设置唯一标识
     *
     * @param id 唯一标识
     */
    public void setId(int id) {
        this.id = id;
    }
    /**
     * 获取名
     *
     * @return name - 名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置名
     *
     * @param name 名
     */
    public void setName(String name) {
        this.name = name;
    }

}
