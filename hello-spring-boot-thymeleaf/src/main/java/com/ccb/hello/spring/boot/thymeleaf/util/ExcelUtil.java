package com.ccb.hello.spring.boot.thymeleaf.util;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Name;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

public class ExcelUtil {
    public static String importExcel(MultipartFile file,int startj) throws IOException {
        //创建workbook
        Workbook workBook = null;
        InputStream ins = null;
        String str="";
        if(file!=null){
            //创建输入流
             ins = file.getInputStream();
            boolean isExcel2003 = file.getName().toLowerCase().endsWith("xls")?true:false;
            if(isExcel2003){
                //2003及以下版本使用
                workBook = new HSSFWorkbook(ins);
            }else {
                //2007及以上版本使用
                workBook = new XSSFWorkbook(ins);
            }
            //获取工作表名称个数
            List<? extends Name> allNames = workBook.getAllNames();
            //根据下标获取当前sheet页
            Sheet sheetAt = workBook.getSheetAt(allNames.size()-1);
            //获取excel有多少条数据
            int rowSize = sheetAt.getLastRowNum();
            //循环取出数据
            for(int j = startj; j<rowSize;j++){
                Row row = sheetAt.getRow(j);
               Iterator i = row.cellIterator();
               while(i.hasNext()){
                   str =(String)i.next();
               }
            }
            return str;
        }else{
            return "file is null";
        }

    }
}
