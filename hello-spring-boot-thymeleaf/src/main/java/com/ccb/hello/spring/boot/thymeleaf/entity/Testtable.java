package com.ccb.hello.spring.boot.thymeleaf.entity;

import javax.persistence.*;

public class Testtable {
    /**
     * 唯一标识
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT LAST_INSERT_ID()")
    private String id;

    /**
     * 系统名
     */
    private String systemname;

    /**
     * 布署单元名
     */
    private String deplymentname;

    /**
     * 单元类型
     */
    private String unittype;

    /**
     * 主机名
     */
    private String hostname;

    /**
     * 录入时间
     */
    private String entertime;

    /**
     * 获取唯一标识
     *
     * @return id - 唯一标识
     */
    public String getId() {
        return id;
    }

    /**
     * 设置唯一标识
     *
     * @param id 唯一标识
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取系统名
     *
     * @return systemname - 系统名
     */
    public String getSystemname() {
        return systemname;
    }

    /**
     * 设置系统名
     *
     * @param systemname 系统名
     */
    public void setSystemname(String systemname) {
        this.systemname = systemname;
    }

    /**
     * 获取布署单元名
     *
     * @return deplymentname - 布署单元名
     */
    public String getDeplymentname() {
        return deplymentname;
    }

    /**
     * 设置布署单元名
     *
     * @param deplymentname 布署单元名
     */
    public void setDeplymentname(String deplymentname) {
        this.deplymentname = deplymentname;
    }

    /**
     * 获取单元类型
     *
     * @return unittype - 单元类型
     */
    public String getUnittype() {
        return unittype;
    }

    /**
     * 设置单元类型
     *
     * @param unittype 单元类型
     */
    public void setUnittype(String unittype) {
        this.unittype = unittype;
    }

    /**
     * 获取主机名
     *
     * @return hostname - 主机名
     */
    public String getHostname() {
        return hostname;
    }

    /**
     * 设置主机名
     *
     * @param hostname 主机名
     */
    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    /**
     * 获取录入时间
     *
     * @return entertime - 录入时间
     */
    public String getEntertime() {
        return entertime;
    }

    /**
     * 设置录入时间
     *
     * @param entertime 录入时间
     */
    public void setEntertime(String entertime) {
        this.entertime = entertime;
    }
}