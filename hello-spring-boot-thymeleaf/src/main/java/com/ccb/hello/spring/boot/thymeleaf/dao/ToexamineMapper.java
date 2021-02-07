package com.ccb.hello.spring.boot.thymeleaf.dao;

import com.ccb.hello.spring.boot.thymeleaf.entity.Toexamine;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.MyMapper;

import java.util.List;
import java.util.Map;
@Mapper
public interface ToexamineMapper extends MyMapper<Toexamine> {
    @Select("<script>"+
            "select * from toexamine where 1=1"+
            "<if test='branch != null'>"+
            "and branch like #{branch}"+
            "</if>"+
            "<if test='devtasks != null'>"+
            "and devtasks like #{devtasks}"+
            "</if>"+
            "<if test='centerresult != null'>"+
            "and centerresult = #{centerresult}"+
            "</if>"+
            "<if test='versriondate != null'>"+
            "and versriondate = #{versriondate}"+
            "</if>"+
            "limit #{currIndex},#{showNum}"+
            "</script>")
    public List<Toexamine> findData(Map<String,String> map);
    @Delete("delete from toexamine where id in (#{id})")
    public void deleteInIds(@Param("id") String id);
}