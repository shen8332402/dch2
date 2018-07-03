package com.digitalchina.dch.testdemo.service;

import java.util.List;
import java.util.Map;

import com.digitalchina.dch.testdemo.model.Student;

/**
 * 
 * @author shentt
 * @date 2018年4月19日
 * @className TestDemoService.java
 * @param 
 * @Description 测试后台框架
 */
public interface TestDemoService {
	public int addData(Student student);
	public Student qryDataReturnClass(Long id);
	public List<Map<String, Object>> qryDataReturnList(Long id);
	public int delData(Long id);
	public List<Map<String, Object>> qryDataListReturnLM();
	public List<Student> qryDataListReturnLC();
}
