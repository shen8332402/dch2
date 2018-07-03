package com.digitalchina.dch.exceldemo.controller;

import java.beans.IntrospectionException;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.digitalchina.dch.exceldemo.service.ExcelDemoService;

/**
 * 
 * @author shentt
 * @date 2018年4月19日
 * @className ExcelDemoController.java
 * @param 
 * @Description exceldemo控制层
 */
@Controller
@Scope("prototype")
@RequestMapping(value="/excelDemo")
public class ExcelDemoController {
	@Autowired
	@Qualifier(value="ExcelDemoServiceImpl")
	private ExcelDemoService excelDemoService;
	@RequestMapping("/import")  
	public String impotr(HttpServletRequest request, Model model) throws Exception {  
	     int adminId = 1;  
	     //获取上传的文件  
	     MultipartHttpServletRequest multipart = (MultipartHttpServletRequest) request;  
	     MultipartFile file = multipart.getFile("upfile");  
	     String month = request.getParameter("month");  
	     InputStream in = file.getInputStream();  
	     //数据导入  
	     excelDemoService.importExcelInfo(in,file,month,adminId);  
	     in.close();  
	     return "redirect:/salary/index.html";  
	}
	
	@RequestMapping("/export")  
	public @ResponseBody void export(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, IntrospectionException, IllegalAccessException, ParseException, InvocationTargetException {  
	    String salaryDate = request.getParameter("salaryDate");  
	    if(salaryDate!=""){  
	        response.reset(); //清除buffer缓存  
	        Map<String,Object> map=new HashMap<String,Object>();  
	        // 指定下载的文件名  
	        response.setHeader("Content-Disposition", "attachment;filename="+salaryDate+".xlsx");  
	        response.setContentType("application/vnd.ms-excel;charset=UTF-8");  
	        response.setHeader("Pragma", "no-cache");  
	        response.setHeader("Cache-Control", "no-cache");  
	        response.setDateHeader("Expires", 0);  
	        XSSFWorkbook workbook=null;  
	        //导出Excel对象  
	        workbook = excelDemoService.exportExcelInfo(salaryDate);  
	        OutputStream output;  
	        try {  
	            output = response.getOutputStream();  
	            BufferedOutputStream bufferedOutPut = new BufferedOutputStream(output);  
	            bufferedOutPut.flush();  
	            workbook.write(bufferedOutPut);  
	            bufferedOutPut.close();  
	        } catch (IOException e) {  
	            e.printStackTrace();  
	        }  
	    } 
	}
}
