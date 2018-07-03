package com.digitalchina.dch.exceldemo.mapper;

import java.util.List;

import com.digitalchina.dch.testdemo.model.Student;

public interface ExcelDemoMapper {
	public void insertInfoBatch(List<Student> list);
	public List<Student> selectApartInfo();
}
