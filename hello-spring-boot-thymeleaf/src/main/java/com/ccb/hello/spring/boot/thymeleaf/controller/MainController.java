package com.ccb.hello.spring.boot.thymeleaf.controller;


import com.ccb.hello.spring.boot.thymeleaf.dao.TesttableMapper;
import com.ccb.hello.spring.boot.thymeleaf.entity.Testtable;
import com.ccb.hello.spring.boot.thymeleaf.service.TestTableService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.hssf.usermodel.HSSFFooter;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/main")
@Api(value = "测试接口", tags = "MainController", description = "测试接口相关")
public class MainController {

    @Autowired
    private TesttableMapper testtableMapper;
    @Autowired
    private TestTableService testTableService;

    @RequestMapping(value ="/index",method = RequestMethod.GET)
    public String index(Model model){

        return "index";
    }
    @RequestMapping(value = "/toMatchingServer",method = RequestMethod.GET)
    public String toMatchingServer(Model model){
        return "/testtablefront/importtestdata";
    }

    @RequestMapping(value = "/MatchingServer",method =RequestMethod.POST)
    @ApiOperation(value = "导入接口",notes = "导入接口")
    @ResponseBody
    public void Excel(@RequestParam(value = "file")MultipartFile file, HttpServletResponse response) throws IOException {
        //创建workbook
        Workbook workBook = null;

        //创建输入流
        InputStream filei = file.getInputStream();

        //读取指定路径下的excel
        try {

            boolean isExcel2003 = file.getName().toLowerCase().endsWith("xls")?true:false;
            if(isExcel2003){
                //2003及以下版本使用
                workBook = new HSSFWorkbook(filei);
            }else {
                //2007及以上版本使用
                workBook = new XSSFWorkbook(filei);
            }
            //获取工作表名称个数
            List<? extends Name> allNames = workBook.getAllNames();
            //根据下标获取当前sheet页
            Sheet sheetAt = workBook.getSheetAt(allNames.size()-1);

            //获取excel有多少条数据
            int rowSize = sheetAt.getLastRowNum();

            String str= "";
            //循环取出数据并赋值
            for(int j=8; j<rowSize;j++){
                Row row = sheetAt.getRow(j);
                Cell cell2 = row.getCell(1);
                cell2.setCellType(CellType.STRING);
                //第j行第3列
                Cell cell3 = row.getCell(2);
                cell3.setCellType(CellType.STRING);
                Cell cell4 = row.getCell(3);
                cell4.setCellType(CellType.STRING);
                //判断此行如果为空则停止匹配
                if ("".equals(cell3.getStringCellValue())) {
                    break;
                }
                System.out.println("cell2===="+cell3);
                List<Testtable> testTypeByNameIsDepname = testTableService.getTestTypeByNameIsDepname(cell3.getStringCellValue());
                System.out.println("testTypeByNameIsDepname=="+testTypeByNameIsDepname);
                if(testTypeByNameIsDepname!=null){
                    for(Testtable tetab : testTypeByNameIsDepname){
                        str+=tetab.getHostname()+",";
                    }
                }

                System.out.println("str==="+str);
                String hostNames = str.substring(0,str.length()-1);
                System.out.println("hostNames==="+hostNames);
                Cell cell7 = row.getCell(6);
                cell7.setCellValue(cell2.getStringCellValue());
                Cell cell8 = row.getCell(7);
                cell8.setCellValue(cell3.getStringCellValue());
                Cell cell9 = row.getCell(8);
                cell9.setCellValue(cell4.getStringCellValue());
                Cell cell10 = row.getCell(9);
                cell10.setCellValue(hostNames);

                CellStyle style = workBook.createCellStyle();
                //创建字体
                Font font = workBook.createFont();
                //设置字体的颜色
                font.setColor(Font.COLOR_NORMAL);
                style.setFont(font);
                cell7.setCellStyle(style);
                cell8.setCellStyle(style);
                cell9.setCellStyle(style);
                cell10.setCellStyle(style);
                str="";
            }
            filei.close();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-ddHH:mm:ss");//设置日期格式
            String format = df.format(new Date());
            // 设置输出的格式
            response.reset();
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(format + ".xlsm","gb2312"));
            response.setHeader("Cache-Control", "No-cache");

            OutputStream os = response.getOutputStream();
            workBook.write(os);
            os.flush();
            os.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
