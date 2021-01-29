package com.ccb.hello.spring.boot.thymeleaf.entity;

import javax.persistence.*;

public class Toexamine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT LAST_INSERT_ID()")
    private Integer id;

    /**
     * 版本日期
     */
    private String versriondate;

    /**
     * 投产基线ID
     */
    private String baseline;

    /**
     * 基线系统名
     */
    @Column(name = "systemName")
    private String systemname;

    /**
     * 部署平台
     */
    private String deploymenplatform;

    /**
     * 系统中文名称
     */
    private String chinesename;

    /**
     * 物理子系统简称
     */
    private String physicalsubsystem;

    /**
     * 所属分行
     */
    private String branch;

    /**
     * 是否投产
     */
    private String productionstatus;

    /**
     * 开发任务
     */
    private String devtasks;

    /**
     * 需求文档情况
     */
    private String requirementdocument;

    /**
     * 功能点介绍
     */
    private String functionpoint;

    /**
     * 功能测试方案情况
     */
    @Column(name = "testPlan")
    private String testplan;

    /**
     * 案例执行过程跟踪记录(图片等方式)
     */
    private String process;

    /**
     * 初始案例总数
     */
    private Integer totalnumber;

    /**
     * 初始反案例数
     */
    @Column(name = "starteNumber")
    private Integer startenumber;

    /**
     * 功能点覆盖率
     */
    private String cover;

    /**
     * 最终案例总数
     */
    private Integer finalcase;

    /**
     * 最终反案例数
     */
    private Integer endnumber;

    /**
     * 功能测试检核结果
     */
    @Column(name = "resultStatus")
    private String resultstatus;

    /**
     * 审核意见(备注)
     */
    private String auditopinion;

    /**
     * 版本安装测试报告
     */
    private String installfiles;

    /**
     * 安全测试报告
     */
    private String securitydocuments;

    /**
     * 沟通记录
     */
    private String exchangenotes;

    /**
     * 测试中心投产检验结论
     */
    private String centerresult;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取版本日期
     *
     * @return versriondate - 版本日期
     */
    public String getVersriondate() {
        return versriondate;
    }

    /**
     * 设置版本日期
     *
     * @param versriondate 版本日期
     */
    public void setVersriondate(String versriondate) {
        this.versriondate = versriondate;
    }

    /**
     * 获取投产基线ID
     *
     * @return baseline - 投产基线ID
     */
    public String getBaseline() {
        return baseline;
    }

    /**
     * 设置投产基线ID
     *
     * @param baseline 投产基线ID
     */
    public void setBaseline(String baseline) {
        this.baseline = baseline;
    }

    /**
     * 获取基线系统名
     *
     * @return systemName - 基线系统名
     */
    public String getSystemname() {
        return systemname;
    }

    /**
     * 设置基线系统名
     *
     * @param systemname 基线系统名
     */
    public void setSystemname(String systemname) {
        this.systemname = systemname;
    }

    /**
     * 获取部署平台
     *
     * @return deploymenplatform - 部署平台
     */
    public String getDeploymenplatform() {
        return deploymenplatform;
    }

    /**
     * 设置部署平台
     *
     * @param deploymenplatform 部署平台
     */
    public void setDeploymenplatform(String deploymenplatform) {
        this.deploymenplatform = deploymenplatform;
    }

    /**
     * 获取系统中文名称
     *
     * @return chinesename - 系统中文名称
     */
    public String getChinesename() {
        return chinesename;
    }

    /**
     * 设置系统中文名称
     *
     * @param chinesename 系统中文名称
     */
    public void setChinesename(String chinesename) {
        this.chinesename = chinesename;
    }

    /**
     * 获取物理子系统简称
     *
     * @return physicalsubsystem - 物理子系统简称
     */
    public String getPhysicalsubsystem() {
        return physicalsubsystem;
    }

    /**
     * 设置物理子系统简称
     *
     * @param physicalsubsystem 物理子系统简称
     */
    public void setPhysicalsubsystem(String physicalsubsystem) {
        this.physicalsubsystem = physicalsubsystem;
    }

    /**
     * 获取所属分行
     *
     * @return branch - 所属分行
     */
    public String getBranch() {
        return branch;
    }

    /**
     * 设置所属分行
     *
     * @param branch 所属分行
     */
    public void setBranch(String branch) {
        this.branch = branch;
    }

    /**
     * 获取是否投产
     *
     * @return productionstatus - 是否投产
     */
    public String getProductionstatus() {
        return productionstatus;
    }

    /**
     * 设置是否投产
     *
     * @param productionstatus 是否投产
     */
    public void setProductionstatus(String productionstatus) {
        this.productionstatus = productionstatus;
    }

    /**
     * 获取开发任务
     *
     * @return devtasks - 开发任务
     */
    public String getDevtasks() {
        return devtasks;
    }

    /**
     * 设置开发任务
     *
     * @param devtasks 开发任务
     */
    public void setDevtasks(String devtasks) {
        this.devtasks = devtasks;
    }

    /**
     * 获取需求文档情况
     *
     * @return requirementdocument - 需求文档情况
     */
    public String getRequirementdocument() {
        return requirementdocument;
    }

    /**
     * 设置需求文档情况
     *
     * @param requirementdocument 需求文档情况
     */
    public void setRequirementdocument(String requirementdocument) {
        this.requirementdocument = requirementdocument;
    }

    /**
     * 获取功能点介绍
     *
     * @return functionpoint - 功能点介绍
     */
    public String getFunctionpoint() {
        return functionpoint;
    }

    /**
     * 设置功能点介绍
     *
     * @param functionpoint 功能点介绍
     */
    public void setFunctionpoint(String functionpoint) {
        this.functionpoint = functionpoint;
    }

    /**
     * 获取功能测试方案情况
     *
     * @return testPlan - 功能测试方案情况
     */
    public String getTestplan() {
        return testplan;
    }

    /**
     * 设置功能测试方案情况
     *
     * @param testplan 功能测试方案情况
     */
    public void setTestplan(String testplan) {
        this.testplan = testplan;
    }

    /**
     * 获取案例执行过程跟踪记录(图片等方式)
     *
     * @return process - 案例执行过程跟踪记录(图片等方式)
     */
    public String getProcess() {
        return process;
    }

    /**
     * 设置案例执行过程跟踪记录(图片等方式)
     *
     * @param process 案例执行过程跟踪记录(图片等方式)
     */
    public void setProcess(String process) {
        this.process = process;
    }

    /**
     * 获取初始案例总数
     *
     * @return totalnumber - 初始案例总数
     */
    public Integer getTotalnumber() {
        return totalnumber;
    }

    /**
     * 设置初始案例总数
     *
     * @param totalnumber 初始案例总数
     */
    public void setTotalnumber(Integer totalnumber) {
        this.totalnumber = totalnumber;
    }

    /**
     * 获取初始反案例数
     *
     * @return starteNumber - 初始反案例数
     */
    public Integer getStartenumber() {
        return startenumber;
    }

    /**
     * 设置初始反案例数
     *
     * @param startenumber 初始反案例数
     */
    public void setStartenumber(Integer startenumber) {
        this.startenumber = startenumber;
    }

    /**
     * 获取功能点覆盖率
     *
     * @return cover - 功能点覆盖率
     */
    public String getCover() {
        return cover;
    }

    /**
     * 设置功能点覆盖率
     *
     * @param cover 功能点覆盖率
     */
    public void setCover(String cover) {
        this.cover = cover;
    }

    /**
     * 获取最终案例总数
     *
     * @return finalcase - 最终案例总数
     */
    public Integer getFinalcase() {
        return finalcase;
    }

    /**
     * 设置最终案例总数
     *
     * @param finalcase 最终案例总数
     */
    public void setFinalcase(Integer finalcase) {
        this.finalcase = finalcase;
    }

    /**
     * 获取最终反案例数
     *
     * @return endnumber - 最终反案例数
     */
    public Integer getEndnumber() {
        return endnumber;
    }

    /**
     * 设置最终反案例数
     *
     * @param endnumber 最终反案例数
     */
    public void setEndnumber(Integer endnumber) {
        this.endnumber = endnumber;
    }

    /**
     * 获取功能测试检核结果
     *
     * @return resultStatus - 功能测试检核结果
     */
    public String getResultstatus() {
        return resultstatus;
    }

    /**
     * 设置功能测试检核结果
     *
     * @param resultstatus 功能测试检核结果
     */
    public void setResultstatus(String resultstatus) {
        this.resultstatus = resultstatus;
    }

    /**
     * 获取审核意见(备注)
     *
     * @return auditopinion - 审核意见(备注)
     */
    public String getAuditopinion() {
        return auditopinion;
    }

    /**
     * 设置审核意见(备注)
     *
     * @param auditopinion 审核意见(备注)
     */
    public void setAuditopinion(String auditopinion) {
        this.auditopinion = auditopinion;
    }

    /**
     * 获取版本安装测试报告
     *
     * @return installfiles - 版本安装测试报告
     */
    public String getInstallfiles() {
        return installfiles;
    }

    /**
     * 设置版本安装测试报告
     *
     * @param installfiles 版本安装测试报告
     */
    public void setInstallfiles(String installfiles) {
        this.installfiles = installfiles;
    }

    /**
     * 获取安全测试报告
     *
     * @return securitydocuments - 安全测试报告
     */
    public String getSecuritydocuments() {
        return securitydocuments;
    }

    /**
     * 设置安全测试报告
     *
     * @param securitydocuments 安全测试报告
     */
    public void setSecuritydocuments(String securitydocuments) {
        this.securitydocuments = securitydocuments;
    }

    /**
     * 获取沟通记录
     *
     * @return exchangenotes - 沟通记录
     */
    public String getExchangenotes() {
        return exchangenotes;
    }

    /**
     * 设置沟通记录
     *
     * @param exchangenotes 沟通记录
     */
    public void setExchangenotes(String exchangenotes) {
        this.exchangenotes = exchangenotes;
    }

    /**
     * 获取测试中心投产检验结论
     *
     * @return centerresult - 测试中心投产检验结论
     */
    public String getCenterresult() {
        return centerresult;
    }

    /**
     * 设置测试中心投产检验结论
     *
     * @param centerresult 测试中心投产检验结论
     */
    public void setCenterresult(String centerresult) {
        this.centerresult = centerresult;
    }
}