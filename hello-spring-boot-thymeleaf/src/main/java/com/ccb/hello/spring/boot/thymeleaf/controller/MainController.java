package com.ccb.hello.spring.boot.thymeleaf.controller;


import com.ccb.hello.spring.boot.thymeleaf.dao.TesttableMapper;
import com.ccb.hello.spring.boot.thymeleaf.entity.Testtable;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/main")
@Api(value = "测试接口", tags = "MainController", description = "测试接口相关")
public class MainController {

    @Autowired
    private TesttableMapper testtableMapper;

    @RequestMapping(value ={"","index"},method = RequestMethod.GET)
    public String index(Model model){

        return "index";
    }


    @RequestMapping(value = "/MatchingServer",method =RequestMethod.POST)
    @ApiOperation(value = "导入接口",notes = "导入接口")
    public void Excel(MultipartFile file, HttpServletResponse response) throws IOException {
        //创建workbook
        Workbook workBook = null;
//        WirtableWorkbook wirtabook =null;

        InputStream filei = file.getInputStream();
        //输出流
//        FileOutputStream fileo = null;
        //文件路径
        String filePath = "C:\\Users\\1\\Desktop\\应用变更自服务操作控制表_模板(V1.1)-系统P5ICA_版本号200313.xlsm";

        //读取指定路径下的excel
        try {
            //输入流

            boolean isExcel2003 = filePath.toLowerCase().endsWith("xls")?true:false;
            if(isExcel2003){
                //2003及以下版本使用
                workBook = new HSSFWorkbook(filei);
            }else {
                //2007及以上版本使用
                workBook = new XSSFWorkbook(filei);
            }
            //加载到workBook
            // workBook = WorkbookFactory.create(filei);
//            int numberOfSheets = workBook.getNumberOfSheets();
            List<? extends Name> allNames = workBook.getAllNames();
            System.out.println("allNames总共有"+allNames.size());
            //获取sheet页數量
            Sheet sheetAt = workBook.getSheetAt(allNames.size()-1);
            System.out.println("sheetAt===="+sheetAt);

            //获取excel有多少条数据
            int rowSize = sheetAt.getLastRowNum();
            int physicalNumberOfRows = sheetAt.getPhysicalNumberOfRows();
            System.out.println("rowSize====="+rowSize);
            System.out.println("rowSize====="+physicalNumberOfRows);

            String str= "";
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
                List<Testtable> testTypeByNameIsDepname = testtableMapper.getTestTypeByNameIsDepname(cell3.getStringCellValue());
                System.out.println("testTypeByNameIsDepname=="+testTypeByNameIsDepname);
                for(Testtable tetab : testTypeByNameIsDepname){
                    str+=tetab.getHostname()+",";
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
                font.setColor(Font.COLOR_RED);
                style.setFont(font);
                cell7.setCellStyle(style);
                cell8.setCellStyle(style);
                cell9.setCellStyle(style);
                cell10.setCellStyle(style);
                str="";
            }
            // 设置输出的格式
            response.reset();
            response.setContentType("application/octet-stream");
            response.addHeader("Content-Disposition", "attachment; filename=\"" + new Date() + "\"");
            // 循环取出流中的数据
            byte[] b = new byte[100];
            int len;
            while ((len = filei.read(b)) > 0)
                response.getOutputStream().write(b, 0, len);
                filei.close();

           // byte[] buffer = new byte[filei.available()];
            // 设置response的Header
//            response.addHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes()));
//            response.addHeader("Content-Length", "" +filePath.length());
//            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
//            response.setContentType("application/octet-stream");
//            int a=0;
//            byte[] b = new byte[512];
//            while (a != -1){
//                a = filei.read(b);
//                toClient.write(b,0,a);
//
//            }
//                   // 清空response
//                   response.reset();
//                 //  toClient.write(buffer);
//                   toClient.flush();
//                   toClient.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        //创建workbook
        Workbook workBook = null;
//        WirtableWorkbook wirtabook =null;

        //输出流
        FileOutputStream fileo = null;
        //文件路径
        String filePath = "C:\\Users\\1\\Desktop\\应用变更自服务操作控制表_模板(V1.1)-系统P5ICA_版本号200313.xlsm";

        //读取指定路径下的excel
        try {
            //输入流
            FileInputStream filei = new FileInputStream(filePath);
            boolean isExcel2003 = filePath.toLowerCase().endsWith("xls")?true:false;
            if(isExcel2003){
                //2003及以下版本使用
                workBook = new HSSFWorkbook(filei);
            }else {
                //2007及以上版本使用
                workBook = new XSSFWorkbook(filei);
            }
            //加载到workBook
            // workBook = WorkbookFactory.create(filei);
//            int numberOfSheets = workBook.getNumberOfSheets();
            List<? extends Name> allNames = workBook.getAllNames();
            System.out.println("allNames总共有"+allNames.size());
            //获取sheet页數量
            Sheet sheetAt = workBook.getSheetAt(allNames.size()-1);
            System.out.println("sheetAt===="+sheetAt);

            //获取excel有多少条数据
            int rowSize = sheetAt.getLastRowNum();
            int physicalNumberOfRows = sheetAt.getPhysicalNumberOfRows();
            System.out.println("rowSize====="+rowSize);
            System.out.println("rowSize====="+physicalNumberOfRows);

            String str= "a1,a2,a3,";
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
//                System.out.println("cell2===="+cell3);
//                List<Testtable> testTypeByNameIsDepname = testtableMapper.getTestTypeByNameIsDepname(cell3.getStringCellValue());
//                for(Testtable tetab : testTypeByNameIsDepname){
//                    str=tetab.getHostname()+",";
//                }
                String hostNames = str.substring(0,str.length()-1);
                Cell cell7 = row.getCell(6);
                cell7.setCellValue(cell2.getStringCellValue());
                Cell cell8 = row.getCell(7);
                cell8.setCellValue(cell3.getStringCellValue());
                Cell cell9 = row.getCell(8);
                cell9.setCellValue(cell4.getStringCellValue());
                Cell cell10 = row.getCell(9);
                cell10.setCellValue(hostNames);

                //设置单位格的风格
                CellStyle style = workBook.createCellStyle();
//                //创建字体
                Font font = workBook.createFont();
//                //设置字体的颜色
                font.setColor(Font.COLOR_RED);
                style.setFont(font);
                cell7.setCellStyle(style);
                cell8.setCellStyle(style);
                cell9.setCellStyle(style);
                cell10.setCellStyle(style);
            }

            //将更改后的excel写回去
//            fileo = new FileOutputStream(filei);
//            workBook.write(fileo);
//            //遍历所有的数据
//            for (int i = 8; i <=rowSize; i++) {
//
//                //获取第I行的数据
//                Row row = sheetAt.getRow(i);
////                System.out.println("row====="+row);
//                //获取此行第一列
//                Cell cell1 = row.getCell(0);
//                cell1.setCellType(CellType.STRING);
//                //判断此行如果为空则停止
//                if ("".equals(cell1.getStringCellValue())) {
//                    break;
//                }
//
//                //创建第I行  第4列的单位格
//                Cell cell = row.createCell(3);
//                //设置值
//                cell.setCellValue("hello");
//                //设置单位格的风格
//                CellStyle style = workBook.createCellStyle();
//                //创建字体
//                Font font = workBook.createFont();
//                //设置字体的颜色
//                font.setColor(Font.COLOR_RED);
//                style.setFont(font);
//                cell.setCellStyle(style);
//
//
//            }
            //将更改后的excel写回去
            fileo = new FileOutputStream(filePath);
            workBook.write(fileo);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
