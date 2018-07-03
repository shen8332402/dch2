package com.digitalchina.dch.exceldemo.service.impl;

import java.beans.IntrospectionException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.digitalchina.dch.exceldemo.mapper.ExcelDemoMapper;
import com.digitalchina.dch.exceldemo.service.ExcelDemoService;
import com.digitalchina.dch.testdemo.model.Student;
import com.digitalchina.frame.officeutil.ExcelBean;
import com.digitalchina.frame.officeutil.ExcelUtil;

@Service(value="ExcelDemoServiceImpl")
public class ExcelDemoServiceImpl implements ExcelDemoService{

	@Autowired
	@Qualifier(value="excelDemoMapper")
	private ExcelDemoMapper excelDemoMapper;
	
	@Override
	public void importExcelInfo(InputStream in, MultipartFile file, String salaryDate, Integer adminId) {
		List<List<Object>> listob = null;
		try {
			listob = ExcelUtil.getBankListByExcel(in,file.getOriginalFilename());
		} catch (Exception e) {
			e.printStackTrace();
		}  
	    List<Student> salaryList = new ArrayList<Student>();  
	    //遍历listob数据，把数据放到List中  
	    for (int i = 0; i < listob.size(); i++) {  
	        List<Object> ob = listob.get(i);  
	        Student student = new Student();  
	        //通过遍历实现把每一列封装成一个model中，再把所有的model用List集合装载  
	        student.setName(String.valueOf(ob.get(0)));  
	        student.setAddress(String.valueOf(ob.get(1)));  
	        student.setScore(Double.valueOf(String.valueOf(ob.get(2))));  
	        student.setTel(String.valueOf(ob.get(3)));  
	        salaryList.add(student);  
	    }  
	    //批量插入  
	    excelDemoMapper.insertInfoBatch(salaryList);  
	}

	@Override
	public XSSFWorkbook exportExcelInfo(String salaryDate) throws InvocationTargetException, ClassNotFoundException,
			IntrospectionException, ParseException, IllegalAccessException {
		//根据条件查询数据，把数据装载到一个list中  
	    List<Student> list = excelDemoMapper.selectApartInfo();  
	    List<ExcelBean> excel=new ArrayList<>();  
	    Map<Integer,List<ExcelBean>> map=new LinkedHashMap<>();  
	    XSSFWorkbook xssfWorkbook=null;  
	    //设置标题栏  
	    excel.add(new ExcelBean("序号","id",0));  
	    excel.add(new ExcelBean("姓名","name",0));  
	    excel.add(new ExcelBean("地址","address",0));  
	    excel.add(new ExcelBean("电话","tel",0));  
	    excel.add(new ExcelBean("分数","score",0));  
	    map.put(0, excel);  
	    String sheetName = salaryDate + "成绩单";  
	    //调用ExcelUtil的方法  
	    xssfWorkbook = ExcelUtil.createExcelFile(Student.class, list, map, sheetName);  
	    return xssfWorkbook;  
	}

}
