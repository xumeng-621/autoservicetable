package com.ccb.hello.spring.boot.thymeleaf.controller;

import com.ccb.hello.spring.boot.thymeleaf.entity.Toexamine;
import com.ccb.hello.spring.boot.thymeleaf.service.CheckEditionService;
import com.ccb.hello.spring.boot.thymeleaf.util.ResponseEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

import org.apache.poi.xssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;


@CrossOrigin
@Controller
@RequestMapping("/checkEditionController")
@Api(value = "基线数据", tags = "CheckEditionController", description = "版本检核")
public class CheckEditionController {
    @Autowired
    private CheckEditionService checkEditionService;
    @RequestMapping(value = "/toimportdata",method = RequestMethod.GET)
    public String toImportData(Model model){
        return "/testtablefront/checkedition";
    }

    @RequestMapping(value = "toAddOrUpdateData",method = RequestMethod.GET)
    @ApiOperation(value = "前去增加或修改页面",notes ="前去增加或修改页面")
    public String toAddOrUpdateData(Model model){
        model.getAttribute("list");
        String id = (String)model.getAttribute("id");
        if(!StringUtils.isEmpty(id)){
            Toexamine toexamine = checkEditionService.selectToexamineById(id);
            model.addAttribute("toexamine",toexamine);
        }
        return "";
    }
    @RequestMapping(value = "/versionDate",method = RequestMethod.GET)
    @ApiOperation(value = "查询已存的版本日期",notes = "查询经过去重后导入数据库中的版本日期")
    @ResponseBody
    public ResponseEntity versionDate(){
       List<String> list = checkEditionService.findVersionDate();
       ResponseEntity re = new ResponseEntity(list);
       return re;
    }
    @RequestMapping(value = "/add",method = RequestMethod.POST )
    @ApiOperation(value = "添加数据",notes =  "增加页面：投产基线（baseline）物理子系统（physicalsubsystem）部署平台（deploymenplatform）" +
            "所属分行（branch）开发任务（devtasks）系统中文名（chinesename）是否投产（productionstatus）是否提交需求文档（requirementdocument）" +
            "是否存在功能的介绍（functionpoint)是否提交测试方案（testplan）是否存在根据记录（process）初始案例总数（totalnumber）初始反案例数（startenumber）" +
            "功能点覆盖率（cover）最终案例总数（finalcase）最终反案例数（endnumber）功能测试检核结果（resultStatus）审核意见（auditopinion）" +
            "是否提交安装测试报告（installfiles）是否提交安全测试报告（securitydocuments）沟通记录(exchangenotes)测试中心投产检验结论(centerresult)")
    @ResponseBody
    public ResponseEntity add(@RequestBody Toexamine toexamine){

        if(toexamine!=null){
            checkEditionService.addToexamine(toexamine);
            ResponseEntity re = new ResponseEntity("success");
            return re;
        }else{
            ResponseEntity re = new ResponseEntity("201","fail");
            return re;
        }

    }
    @RequestMapping(value = "/update",method = RequestMethod.POST )
    @ApiOperation(value = "修改数据",notes = "修改数据")
    @ResponseBody
    public ResponseEntity update(@RequestBody List<Toexamine> list){
        if(list!=null && list.size()>0){
            for(Toexamine toexamine : list){
                checkEditionService.updateToexamine(toexamine);
            }
            ResponseEntity re = new ResponseEntity("success");
            return re;
        }else{
           ResponseEntity re = new ResponseEntity("201","fail");
           return re;
        }
    }
    @RequestMapping(value = "/deleteInIds",method = RequestMethod.GET)
    @ApiOperation(value = "按id删除数据",notes = "按id删除数据")
    @ResponseBody
    public ResponseEntity deleteInIds(String Ids){
        //String Ids = (String)model.getAttribute("Ids");
        if(!StringUtils.isEmpty(Ids)){
            checkEditionService.deleteInIds(Ids);
            ResponseEntity re = new ResponseEntity("success");
            return re;
        }else{
            ResponseEntity re = new ResponseEntity("201","fail");
            return re;
          }

    }
    @RequestMapping(value = "/selectData",method = RequestMethod.GET)
    @ApiOperation(value = "按条件查询数据",notes = "按条件查询数据")
    @ResponseBody
    public ResponseEntity selectData(String branch, String devtasks, String centerresult, String versriondate,Integer currPage){
       /* String branch = (String)model.getAttribute("branch");
        String devtasks = (String)model.getAttribute("devtasks");
        String resultstatus = (String)model.getAttribute("resultstatus");*/
        //int currPage = (Integer)model.getAttribute("currPage");
        //Toexamine toexamine = new Toexamine();
       /* if(currPage==0){
            currPage = 1;
        }*/
       if(currPage==null|| currPage==0){
           currPage = 1;
       }
        Map<String,String> map = new HashMap<String,String>();
        if(!StringUtils.isEmpty(branch)){
            map.put("branch","%"+branch+"%");
        }
        if(!StringUtils.isEmpty(devtasks)){
            map.put("devtasks","%"+devtasks+"%");
        }
        if(!StringUtils.isEmpty(centerresult)){
            map.put("centerresult",centerresult);
        }
        if(!StringUtils.isEmpty(versriondate)){
            map.put("versriondate",versriondate);
        }
         int totalnumber = checkEditionService.findTotalNumberBy(map);
         List<Toexamine> list = checkEditionService.selectDate(map,currPage,10);
        //Toexamine toexamine = new Toexamine();
        //List<Toexamine> list = checkEditionService.selectToexamine(toexamine);
        //model.addAttribute("list",list);
        if(list!=null&&list.size()>0){
            ResponseEntity re = new ResponseEntity("200",Integer.toString(totalnumber),list);
            return re;
        }else{
            String [] str = new String[0];
            ResponseEntity re = new ResponseEntity(str);
            return re;
        }

    }
    @RequestMapping(value = "/importData",method = RequestMethod.POST)
    @ApiOperation(value = "导入接口",notes = "导入接口")
    @ResponseBody
    public ResponseEntity importData(MultipartFile file, HttpServletResponse response)throws IOException {
        //创建workbook
        Workbook workBook = null;
        InputStream ins = null;
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
            //根据下标获取当前sheet页
            Sheet sheetAt = workBook.getSheetAt(0);
            //获取excel有多少条数据
            int rowSize = sheetAt.getLastRowNum();
            List<Toexamine> list = new ArrayList<Toexamine>();
            try{
                //循环取出数据
                for(int j = 1; j<=rowSize;j++){
                    Toexamine toexamine = new Toexamine();
                    Row row = sheetAt.getRow(j);
                    //基线号
                    if(row.getCell(0)!=null){
                        String str = row.getCell(0).toString();
                        String newstr = str.substring(0,5);
                        toexamine.setBaseline(newstr);
                    }
                    //基线系统名
                    if(row.getCell(1)!=null){
                        String systemName = row.getCell(1).getStringCellValue().toString();
                        toexamine.setSystemname(systemName);
                        String[] totalname = systemName.split("\\)");
                        String enname = totalname[0].substring(1);
                        toexamine.setChinesename(totalname[1]);
                        toexamine.setPhysicalsubsystem(enname);


                    }
                    //部署平台
                    if(row.getCell(3)!=null){
                        toexamine.setDeploymenplatform(row.getCell(3).getStringCellValue());
                    }
                    //所属银行
                    if(row.getCell(5)!=null){
                        toexamine.setBranch(row.getCell(5).getStringCellValue());
                    }
                    //开发任务
                    if(row.getCell(21)!=null){
                        toexamine.setDevtasks(row.getCell(21).getStringCellValue());
                    }
                    //版本日期
                    if(row.getCell(27)!=null){
                    /*String str = row.getCell(27).getCellFormula();
                    String newstr = str.substring(0,8);*/
                        Cell cell = row.getCell(27);
                        if (HSSFDateUtil.isCellDateFormatted(cell)) {
                            double d = cell.getNumericCellValue();
                            Date date = HSSFDateUtil.getJavaDate(d);
                            SimpleDateFormat dformat = new SimpleDateFormat("yyyy-MM-dd");
                            String newstr = dformat.format(date);
                            toexamine.setVersriondate(newstr);
                        } else {
                            NumberFormat nf = NumberFormat.getInstance();
                            nf.setGroupingUsed(false);// true时的格式：1,234,567,890
                            String newstr = nf.format(cell.getNumericCellValue());// 数值类型的数据为double，所以需要转换一下
                            toexamine.setVersriondate(newstr);
                        }

                    }
                    list.add(toexamine);
                }

            }catch (Exception e){
                ResponseEntity re = new ResponseEntity("201","data is error");
                return re;
            }
            for(Toexamine toexamine:list){
                checkEditionService.saveToexamine(toexamine);
            }
            ResponseEntity re = new ResponseEntity("success");
            return re;
        }else{
            ResponseEntity re = new ResponseEntity("201","file is null");
            return re;
        }

    }
    //根据需求增减
    private List<String> getTitles(){
        List<String> list = new ArrayList<String>();
        list.add("序号");
        list.add("版本日期");
        list.add("投产基线ID");
        list.add("基线系统名");
        list.add("基线系统名");
        list.add("系统中文名称");
        list.add("物理子系统简称");
        list.add("所属分行");
        list.add("是否投产");
        list.add("开发任务");
        list.add("需求文档情况");
        list.add("功能点介绍");
        list.add("功能测试方案情况");
        list.add("案例执行过程跟踪记录(图片等方式)");
        list.add("初始案例总数");
        list.add("初始反案例数");
        list.add("功能点覆盖率");
        list.add("最终案例总数");
        list.add("最终反案例数");
        list.add("案例执行率");
        list.add("案例通过率");
        list.add("缺陷总计");
        list.add("修复缺陷");
        list.add("遗留缺陷");
        list.add("功能测试检核结果");
        list.add("功能风险等级");
        list.add("备注");
        list.add("非功能测试必要性评估表");
        list.add("非功能测试方案");
        list.add("非功能测试方案");
        list.add("非功能测试案例");
        list.add("非功能测试报告");
        list.add("非功能数据库检核结果");
        list.add("非功能测试检核结论");
        list.add("备注");
        list.add("版本安装测试报告");
        list.add("备注");
        list.add("安全测试报告");
        list.add("备注");
        list.add("测试中心投产检验结论");
        return list;
    }
    @RequestMapping(value = "/exportData",method = RequestMethod.GET)
    @ApiOperation(value = "导出接口",notes = "导出接口")
    @ResponseBody
    public void exportData(HttpServletResponse response, HttpServletRequest request)throws IOException{
        // 创建写工作簿对象
        Workbook wb = new XSSFWorkbook();
        Sheet sheet = wb.createSheet("本次投产");
        int rowIndex = 0;
        rowIndex = writeTitlesToExcel(wb,sheet,getTitles());
        Toexamine toexamine = new Toexamine();
       // String branch = (String)request.getAttribute("branch");
       // String devtasks = (String)request.getAttribute("devtasks");
       // String centerresult = (String)request.getAttribute("centerresult");
        String versriondate = (String)request.getAttribute("versriondate");
        /*if(!StringUtils.isEmpty(branch)){
            toexamine.setBranch(branch);
        }
        if(!StringUtils.isEmpty(devtasks)){
            toexamine.setDevtasks(devtasks);
        }
        if(!StringUtils.isEmpty(centerresult)){
            toexamine.setCenterresult(centerresult);
        }*/
        if(!StringUtils.isEmpty(versriondate)){
            toexamine.setVersriondate(versriondate);
        }
        List<Toexamine> toexaminesList = checkEditionService.selectToexamine(toexamine);
        writeRowsToExcel(wb, sheet,toexaminesList , rowIndex);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-ddHH:mm:ss");//设置日期格式
        String format = df.format(new Date());
        // 设置输出的格式
//        response.reset();
        response.setCharacterEncoding(request.getCharacterEncoding());
        response.setContentType("application/octet-stream");
//       response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(format + ".xlsx","UTF-8"));
        response.setHeader("Content-Disposition", "attachment;filename=guang.xlsx");
        response.setHeader("Cache-Control", "No-cache");
        wb.write(response.getOutputStream());
        response.flushBuffer();
        wb.close();
    }

    private int writeTitlesToExcel(Workbook wb, Sheet sheet, List<String> titles){
        int rowIndex = 0;
        int colIndex = 0;
       /* Font titleFont = wb.createFont();//获取字体
        titleFont.setFontName("simsun");//设置字体名称（宋体）
        titleFont.setBold(true);//设置字体加粗
        titleFont.setColor(IndexedColors.BLACK.index);//设置字体颜色 黑色
        CellStyle titleStyle = wb.createCellStyle();//获取单元格样式
        titleStyle.setAlignment(HorizontalAlignment.CENTER);//设置单元格的水平对齐类型(这里是水平居中)
        titleStyle.setVerticalAlignment(VerticalAlignment.CENTER);//设置单元格的垂直对齐类型（这里是居中）
        //titleStyle.setFillForegroundColor(createXssfColor("#FFFFFF"));//设置单元格前景色（白色）
        titleStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);//指定图案和纯色单元格填充的单元格填充信息（实心前景）
        titleStyle.setFont(titleFont);//设置字体样式
        // setBorder(titleStyle, BorderStyle.THIN, createXssfColor("#000000"));//设置边框样式（细线、黑色）*/
        Row titleRow = sheet.createRow(rowIndex);//在该工作簿中创建第一行.
        colIndex = 0;
        for (String field : titles) {//循环创建列
            Cell cell = titleRow.createCell(colIndex);
            cell.setCellValue(field);
            //cell.setCellStyle(titleStyle);
            colIndex++;
        }
        rowIndex++;//将行数++ 返回用于下面添加数据
        return rowIndex;
    }
    private int writeRowsToExcel(Workbook wb, Sheet sheet, List<Toexamine> rows, int rowIndex) {
        /*Font dataFont = wb.createFont();//获取字体
        dataFont.setFontName("simsun");//设置字体名称（宋体）
        dataFont.setColor(IndexedColors.BLACK.index);//设置字体颜色 黑色
        CellStyle dataStyle = wb.createCellStyle();//获取单元格样式
        dataStyle.setAlignment(HorizontalAlignment.CENTER);//设置单元格的水平对齐类型(这里是水平居中)
        dataStyle.setVerticalAlignment(VerticalAlignment.CENTER);//设置单元格的垂直对齐类型（这里是居中）
        dataStyle.setFont(dataFont);//设置字体样式
        //setBorder(dataStyle, BorderStyle.THIN, createXssfColor("#000000"));//设置边框样式（细线、黑色）*/
        for (Toexamine rowData : rows) {//循环写入数据
            Row dataRow = sheet.createRow(rowIndex);
            Cell cell1 = dataRow.createCell(0);
            cell1.setCellValue(rowIndex);
            Cell cell2 = dataRow.createCell(1);
            if(!StringUtils.isEmpty(rowData.getVersriondate())) {
                cell2.setCellValue(rowData.getVersriondate());
            }
            Cell cell3 = dataRow.createCell(2);
            if(!StringUtils.isEmpty(rowData.getBaseline())) {
                cell3.setCellValue(rowData.getBaseline());
            }
            Cell cell4 = dataRow.createCell(3);
            if(!StringUtils.isEmpty(rowData.getSystemname())) {
                cell4.setCellValue(rowData.getSystemname());
            }
            Cell cell5 = dataRow.createCell(4);
            if(!StringUtils.isEmpty(rowData.getDeploymenplatform())) {
                cell5.setCellValue(rowData.getDeploymenplatform());
            }
            Cell cell6 = dataRow.createCell(5);
            if(!StringUtils.isEmpty(rowData.getChinesename())) {
                cell6.setCellValue(rowData.getChinesename());
            }
            Cell cell7 = dataRow.createCell(6);
            if(!StringUtils.isEmpty(rowData.getPhysicalsubsystem())) {
                cell7.setCellValue(rowData.getPhysicalsubsystem());
            }
            Cell cell8 = dataRow.createCell(7);
            if(!StringUtils.isEmpty(rowData.getBranch())) {
                cell8.setCellValue(rowData.getBranch());
            }
            Cell cell9 = dataRow.createCell(8);
            if(!StringUtils.isEmpty(rowData.getProductionstatus())) {
                cell9.setCellValue(rowData.getProductionstatus());
            }
            Cell cell10 = dataRow.createCell(9);
            if(!StringUtils.isEmpty(rowData.getDevtasks())) {
                cell10.setCellValue(rowData.getDevtasks());
            }
            Cell cell11 = dataRow.createCell(10);
            if(!StringUtils.isEmpty(rowData.getRequirementdocument())) {
                cell11.setCellValue(rowData.getRequirementdocument());
            }
            Cell cell12 = dataRow.createCell(11);
            if(!StringUtils.isEmpty(rowData.getFunctionpoint())) {
                cell12.setCellValue(rowData.getFunctionpoint());
            }
            Cell cell13 = dataRow.createCell(12);
            if(!StringUtils.isEmpty(rowData.getTestplan())) {
                cell13.setCellValue(rowData.getTestplan());
            }
            Cell cell14 = dataRow.createCell(13);
            if(!StringUtils.isEmpty(rowData.getProcess())) {
                cell14.setCellValue(rowData.getProcess());
            }
            Cell cell15 = dataRow.createCell(14);
            if(!StringUtils.isEmpty(rowData.getTotalnumber())) {
                cell15.setCellValue(rowData.getTotalnumber());
            }
            Cell cell16 = dataRow.createCell(15);
            if(!StringUtils.isEmpty(rowData.getStartenumber())) {
                cell16.setCellValue(rowData.getStartenumber());
            }
            Cell cell17 = dataRow.createCell(16);
            if(!StringUtils.isEmpty(rowData.getCover())) {
                cell17.setCellValue(rowData.getCover());
            }
            Cell cell18 = dataRow.createCell(17);
            if(!StringUtils.isEmpty(rowData.getFinalcase())) {
                cell18.setCellValue(rowData.getFinalcase());
            }
            Cell cell19 = dataRow.createCell(18);
            if(!StringUtils.isEmpty(rowData.getEndnumber())) {
                cell19.setCellValue(rowData.getEndnumber());
            }
            Cell cell20 = dataRow.createCell(19);
            cell20.setCellValue("");
            Cell cell21 = dataRow.createCell(20);
            cell21.setCellValue("");
            Cell cell22 = dataRow.createCell(21);
            cell22.setCellValue("");
            Cell cell23 = dataRow.createCell(22);
            cell23.setCellValue("");
            Cell cell24 = dataRow.createCell(23);
            cell24.setCellValue("");
            Cell cell25 = dataRow.createCell(24);
            if(!StringUtils.isEmpty(rowData.getResultstatus())) {
                cell25.setCellValue(rowData.getResultstatus());
            }
            Cell cell26 = dataRow.createCell(25);
            cell26.setCellValue("");
            Cell cell27 = dataRow.createCell(26);
            if(!StringUtils.isEmpty(rowData.getAuditopinion())) {
                cell27.setCellValue(rowData.getAuditopinion());
            }
            Cell cell28 = dataRow.createCell(27);
            cell28.setCellValue("");
            Cell cell29 = dataRow.createCell(28);
            cell29.setCellValue("");
            Cell cell30 = dataRow.createCell(29);
            cell30.setCellValue("");
            Cell cell31 = dataRow.createCell(30);
            cell31.setCellValue("");
            Cell cell32 = dataRow.createCell(31);
            cell32.setCellValue("");
            Cell cell33 = dataRow.createCell(32);
            cell33.setCellValue("");
            Cell cell34 = dataRow.createCell(33);
            cell34.setCellValue("");
            Cell cell35 = dataRow.createCell(34);
            cell35.setCellValue("");
            Cell cell36 = dataRow.createCell(35);
            if(!StringUtils.isEmpty(rowData.getInstallfiles())) {
                cell36.setCellValue(rowData.getInstallfiles());
            }
            Cell cell37 = dataRow.createCell(36);
            cell37.setCellValue("");
            Cell cell38 = dataRow.createCell(37);
            if(!StringUtils.isEmpty(rowData.getSecuritydocuments())) {
                cell38.setCellValue(rowData.getSecuritydocuments());
            }
            Cell cell39 = dataRow.createCell(38);
            cell39.setCellValue("");
            Cell cell40 = dataRow.createCell(39);
            if(!StringUtils.isEmpty(rowData.getCenterresult())){
                cell40.setCellValue(rowData.getCenterresult());
            }

            rowIndex++;
        }
        return rowIndex;
    }
}

