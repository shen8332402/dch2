package com.digitalchina.frame.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * 
 * @ClassName: ConfigUtils
 * @Description : 定位配置文件工具类
 * @author : xuweilong
 * @date : 2014-11-18
 */
public class ConfigUtils {
	
	/**
	 * 获取配置文件输入流
	 * @param resource
	 * @return InputStream
	 */
	public static InputStream getResourceAsStream(String resource) {
		String stripped = resource.startsWith("/") ? resource.substring(1): resource;
		InputStream stream = null;
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		if (classLoader != null) {
			stream = classLoader.getResourceAsStream(stripped);
		}
		if (stream == null) {
			stream = ConfigUtils.class.getResourceAsStream(resource);
		}
		if (stream == null) {
			stream = ConfigUtils.class.getClassLoader().getResourceAsStream(stripped);
		}
		return stream;
	}
	
	/**
	 * 获取配置文件输出流
	 * @param resource
	 * @return OutputStream
	 */
	public static OutputStream getResourceOutStream(String resource) {
		String stripped = resource.startsWith("/") ? resource.substring(1): resource;
		OutputStream stream = null;
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		if (classLoader != null) {
			URL url = classLoader.getResource(stripped);
			try {
				File file = new File((new URI(url.getPath())).getPath());
				stream = new FileOutputStream(file);
			} catch (URISyntaxException e) {
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		return stream;
	}

}

