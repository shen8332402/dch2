package com.digitalchina.frame.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

/**
 * 
 * @ClassName: PropertiesUtils
 * @Description : 获取配置文件的属性值
 * @author : xuweilong
 * @date : 2014-11-18
 */
public class PropertiesUtils {
	/**
	 * 读取配置文件
	 * 
	 * @param resource
	 * @return Properties
	 */
	private static Properties loadProperties(String resource) {
		Properties properties = new Properties();
		try {
			properties.load(ConfigUtils.getResourceAsStream(resource));
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return properties;
	}
	
	/**
	 * 读取配置文件(可以读取value为中文的配置文件)
	 * 
	 * @param resource
	 * @param code 中文编码
	 * @return Properties
	 */
	public static Properties loadPropertiesZ(String resource,String code) {
		String stripped = resource.startsWith("/") ? resource.substring(1): resource;
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(classLoader.getResourceAsStream(stripped), code));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Properties properties = new Properties();
		try {
			properties.load(br);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return properties;
	}

	/**
	 * 获取配置文件属性
	 * 
	 * @return Properties
	 * 
	 */
	public static Properties getProperties(String resource) {
		return loadProperties(resource);
	}

	/**
	 * 获取配置文件属性值
	 * 
	 * @param key
	 * @return String
	 * 
	 */
	public static String getProperty(String resouce, String key) {
		String tmp = "";
		Properties properties = loadProperties(resouce);
		tmp = properties.getProperty(key);
		return tmp;
	}

	/**
	 * 获取配置文件属性值
	 * 
	 * @param properties
	 * @return String
	 * 
	 */
	public static String getProperty(Properties properties, String key) {
		String tmp = "";
		tmp = properties.getProperty(key);
		return tmp;
	}
}