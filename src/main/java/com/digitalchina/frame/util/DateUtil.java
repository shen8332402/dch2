package com.digitalchina.frame.util;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.i18n.LocaleContextHolder;

public class DateUtil {
	private static Log log = LogFactory.getLog(DateUtil.class);
	private static SimpleDateFormat longFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	/**
	 * 获取当前时间 yyyy-MM-dd HH:mm:ss
	 *
	 * @return
	 * @author LiangQingLu
	 * @date 2017年4月25日 下午4:33:00
	 */
	public static String getCurrentDate() {
		return longFormat.format(new Date());
	}
	/**
	 * 传入的日期参数格式：2017-04-26  或者 2017-04-26 12:23:08 <br>
	 * 字符串转date日期类型。格式化失败则返回null
	 * @date 2017年4月28日 下午3:13:54
	 */
	public static Date stringToDate(String inStr){
		Date date = null;
		try {
			if( inStr.length() == 10){
				inStr = inStr + " 00:00:00";
			}
			date = longFormat.parse(inStr);
		} catch (ParseException e) {
			System.out.println("格式化日期出错了！ "+inStr);
			e.printStackTrace();
		}  
		System.out.println("格式化日期的结果="+date);
		return date;
	}

	/**
	 * 当前日期
	 * @return 系统当前时间
	 */
	public static Date getDate() {
		return new Date();
	}
	/**
	 * 格式化日期
	 * 默认输出 yyyy-MM-dd HH:mm:ss 格式
	 * @author LiangQingLu
	 * @date 2017年5月17日 上午10:57:30
	 */
	public static Date formatDate(Date date,String format){
		SimpleDateFormat formatter = longFormat;
		if(null != format && !"".equals(format)){
			formatter = new SimpleDateFormat(format);
		}
		   String dateString = formatter.format(date);
		   ParsePosition pos = new ParsePosition(0);
		   Date out = formatter.parse(dateString, pos);
		   return out;
	}
	

	public static String getDatePattern() {
		Locale locale = LocaleContextHolder.getLocale();
		String defaultDatePattern = null;
		try {
			defaultDatePattern = ResourceBundle.getBundle("ApplicationResources", locale).getString("date.format");
		} catch (MissingResourceException mse) {
			System.out.print(mse.toString());
		}

		return defaultDatePattern;
	}

	public static String getDateTimePattern() {
		return getDatePattern() + " HH:mm:ss.S";
	}

	public static String getDate(Date aDate) {
		String returnValue = "";

		if (aDate != null) {
			SimpleDateFormat df = new SimpleDateFormat(getDatePattern());
			returnValue = df.format(aDate);
		}

		return returnValue;
	}

	public static Date convertStringToDate(String aMask, String strDate) throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat(aMask);

		if (log.isDebugEnabled()) {
			log.debug("converting '" + strDate + "' to date with mask '" + aMask + "'");
		}
		Date date = null;
		try {
			date = StringUtils.isNotBlank(strDate) ? df.parse(strDate) : null;
		} catch (ParseException pe) {

			throw new ParseException(pe.getMessage(), pe.getErrorOffset());
		}

		return date;
	}

	public static String getTimeNow(Date theTime) {
		return getDateTime("HH:mm", theTime);
	}

	public static Calendar getToday() throws ParseException {
		Date today = new Date();
		SimpleDateFormat df = new SimpleDateFormat(getDatePattern());

		String todayAsString = df.format(today);
		Calendar cal = new GregorianCalendar();
		cal.setTime(convertStringToDate(todayAsString));

		return cal;
	}

	public static String getDateTime(String aMask, Date aDate) {
		SimpleDateFormat df = null;
		String returnValue = "";

		if (aDate == null) {
			log.error("aDate is null!");
		} else {
			df = new SimpleDateFormat(aMask);
			returnValue = df.format(aDate);
		}

		return returnValue;
	}

	public static String convertDateToString(Date aDate) {
		return getDateTime(getDatePattern(), aDate);
	}

	public static Date convertStringToDate(String strDate) throws ParseException {
		Date aDate = null;
		try {
			if (log.isDebugEnabled()) {
				log.debug("converting date with pattern: " + getDatePattern());
			}

			aDate = convertStringToDate(getDatePattern(), strDate);
		} catch (ParseException pe) {
			log.error("Could not convert '" + strDate + "' to a date, throwing exception");
			pe.printStackTrace();
			throw new ParseException(pe.getMessage(), pe.getErrorOffset());
		}

		return aDate;
	}

	public static String convertDate2Str(String pattern, Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		String str = "";
		try {
			str = sdf.format(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str;
	}
}