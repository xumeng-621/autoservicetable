package com.ccb.hello.spring.boot.thymeleaf.entity;

import java.io.Serializable;

public class User implements Serializable {
    private String username;

    private String age;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}