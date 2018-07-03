package com.digitalchina.dch.testdemo.mapper;

import java.util.List;
import java.util.Map;

import com.digitalchina.dch.testdemo.model.Student;

public interface TestDemoMapper {
	public int addData(Student student);
	public Student qryDataReturnClass(Long id);
	public List<Map<String, Object>> qryDataReturnList(Long id);
	public int delData(Long id);
	public List<Map<String, Object>> qryDataListReturnLM();
	public List<Student> qryDataListReturnLC();
}
