package com.digitalchina.frame.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
public final class DateTimeUtils {

	/*******************************************************************************************************************
	 * 取得系统当前时间,类型为Timestamp
	 * 
	 * @return Timestamp
	 ******************************************************************************************************************/
	public static Timestamp getNowTimestamp() {
		java.util.Date d = new java.util.Date();
		Timestamp numTime = new Timestamp(d.getTime());
		return numTime;
	}

	/*******************************************************************************************************************
	 * 取得系统的当前时间,类型为java.util.Date
	 * 
	 * @return java.util.Date
	 ******************************************************************************************************************/
	public static java.util.Date getNowDate() {
		java.util.Date d = new java.util.Date();
		return d;// new java.sql.Date(d.getTime());
	}

	/*******************************************************************************************************************
	 * 取得系统的当前日期，并以系统JAVA_DATE_FORMATTER的格式转化为字符串返回
	 * 
	 * @return String
	 ******************************************************************************************************************/
	public static String getNowStrDate() {
		return DateToString(getNowDate(), CoreConstants.JAVA_DATE_FORMATTER);
	}

	/*******************************************************************************************************************
	 * 取得系统的当前时间，并以系统JAVA_TIME_FORMATTER的格式转化为字符串返回
	 * 
	 * @return String
	 ******************************************************************************************************************/
	public static String getNowStrTime() {
		return DateToString(getNowDate(), CoreConstants.JAVA_TIME_FORMATTER);
	}

	/*******************************************************************************************************************
	 * 从Timestamp类型转化为pattern的字符串，如果date为null 则返回null
	 * 
	 * @param date
	 *            进行转化的Timestamp
	 * @param pattern
	 *            转化的格式
	 * @return
	 ******************************************************************************************************************/
	public static String TimestampToString(Timestamp date, String pattern) {
		String strTemp = null;
		if (date != null) {
			SimpleDateFormat formatter = new SimpleDateFormat(pattern);
			strTemp = formatter.format(date);
		}
		return strTemp;
	}

	/*******************************************************************************************************************
	 * 从Timestamp类型转化为pattern的字符串，如果date为null 则返回strDefault
	 * 
	 * @param date
	 *            进行转化的Timestamp
	 * @param pattern
	 *            转化的格式
	 * @return
	 ******************************************************************************************************************/
	public static String TimestampToString(Timestamp date, String pattern, String strDefault) {
		String strTemp = strDefault;
		if (date != null) {
			SimpleDateFormat formatter = new SimpleDateFormat(pattern);
			strTemp = formatter.format(date);
		}
		return strTemp;
	}

	/*******************************************************************************************************************
	 * 把date型日期转化为String 格式为pattern，如果被转化的对象为null 则返回null
	 * 
	 * @param date
	 * @param pattern
	 *            日期的格式模板
	 * @return
	 ******************************************************************************************************************/
	public static String DateToString(java.util.Date date, String pattern) {
		String strTemp = null;
		if (date != null) {
			SimpleDateFormat formatter = new SimpleDateFormat(pattern);
			strTemp = formatter.format(date);
		}
		return strTemp;
	}

	/*******************************************************************************************************************
	 * 把date型日期转化为String 格式为pattern，如果被转化的对象为null 则返回strDefault
	 * 
	 * @param date
	 * @param pattern
	 *            日期的格式模板
	 * @param strDefault
	 *            缺省值
	 * @return
	 ******************************************************************************************************************/
	public static String DateToString(java.util.Date date, String pattern, String strDefault) {
		String strTemp = strDefault;
		if (date != null) {
			SimpleDateFormat formatter = new SimpleDateFormat(pattern);
			strTemp = formatter.format(date);
		}
		return strTemp;
	}

	/*******************************************************************************************************************
	 * 把pattern 格式的字符串转化为Timestamp类型对象
	 * 
	 * @param strDate
	 * @param pattern
	 *            日期的格式
	 * @return 返回转化后的Timestamp对象如果strDate为null或""或转换失败 返回null
	 ******************************************************************************************************************/
	public static Timestamp StringToTimestamp(String strDate, String pattern) {
		if (strDate != null && !strDate.equals("")) {
			try {
				SimpleDateFormat formatter = new SimpleDateFormat(pattern);
				java.util.Date d = formatter.parse(strDate);
				Timestamp numTime = new Timestamp(d.getTime());
				return numTime;
			} catch (Exception e) {
				return null;
			}
		} else {
			return null;
		}
	}

	/*******************************************************************************************************************
	 * 把format格式的字符串转化为java.sql.date类型
	 * 
	 * @param strDate
	 * @return 返回转化后的Date对象如果strDate为null或""或转换失败 返回null
	 ******************************************************************************************************************/
	public static java.util.Date StringToDate(String strDate, String format) {
		if (strDate != null && !strDate.equals("")) {
			try {
				SimpleDateFormat formatter = new SimpleDateFormat(format);
				java.util.Date d = formatter.parse(strDate);
				return d;
			} catch (Exception e) {
				return null;
			}
		} else {
			return null;
		}
	}

	/*******************************************************************************************************************
	 * 判断字符串是否是日期格式 yyyy/mm/dd，分隔符可不同
	 * 
	 * @param strExp
	 *            类型String，进行判断的字符串
	 * @return 类型Boolean，如果strExp是日期型的则返回true， 否则返回false。
	 ******************************************************************************************************************/
	public static boolean isDate(String strExp) {
		if (strExp.length() != 10)
			return false;

		try {
			Calendar cal = new GregorianCalendar();
			cal.setLenient(false);
			cal.set(Integer.parseInt(strExp.substring(0, 4)), Integer.parseInt(strExp.substring(5, 7)) - 1, Integer
					.parseInt(strExp.substring(8, 10)));
			java.util.Date ud = cal.getTime();
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	/*******************************************************************************************************************
	 * 判断输入的三个整形是否能构成一个合法的日期。
	 * 
	 * @param intYear
	 *            类型int 输入的年
	 * @param intMonth
	 *            类型int 输入的月
	 * @param intDay
	 *            类型int 输入的日
	 * @return 类型boolean ************************************************************
	 */
	public static boolean isDate(int intYear, int intMonth, int intDay) {
		try {
			Calendar cal = new GregorianCalendar();
			cal.setLenient(false);
			cal.set(intYear, intMonth, intDay);
			java.util.Date ud = cal.getTime();
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	public static final Date getDate(int year, int month, int day, int hour, int minute) {
		Calendar cal = new GregorianCalendar(year, intToCalendarMonth(month), day, hour, minute);
		return cal.getTime();
	}

	public static final Date getDate(int year, int month, int day) {
		Calendar cal = new GregorianCalendar(year, intToCalendarMonth(month), day);
		return cal.getTime();
	}

	public static Timestamp getTimestamp(Date date) {
		return new Timestamp(date.getTime());
	}

	public static Timestamp getTimestamp(Calendar date) {
		return new Timestamp(date.getTime().getTime());
	}

	public static Timestamp getTimestamp(String date, String pattern) {
		SimpleDateFormat formatter = new SimpleDateFormat(pattern);
		try {
			Timestamp timestamp = new Timestamp(formatter.parse(date).getTime());
			return timestamp;
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	public static final Date addDays(Date target, int days) {
		long msPerDay = 0x5265c00L;
		long msTarget = target.getTime();
		long msSum = msTarget + msPerDay * (long) days;
		Date result = new Date();
		result.setTime(msSum);
		return result;
	}

	/**
	 * 在给定日期上增加(减少)特定的周期数
	 * 
	 * @param source
	 *            基准时间
	 * @param field
	 *            要增加的周期类型，请使用Calendar中的常量,Calendar.YEAR,Calendar.MONTH,Calendar.WEEK等
	 * @param amount
	 *            要增加或减少的数，正数是增加及未来的时间，负数是减少及过去的时间
	 * @return 返回格式化好的时间串，形如2005-07-06
	 */
	public static final String addCalendar(final Date source, int field, int amount) {

		Calendar greCal = new GregorianCalendar();
		if (source != null) {
			greCal.setTime(source);
		}
		greCal.add(field, amount);
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
		return (f.format(greCal.getTime()));
	}

	public static int dayDiff(Date first, Date second) {
		long msPerDay = 0x5265c00L;
		long diff = first.getTime() / msPerDay - second.getTime() / msPerDay;
		Long convertLong = new Long(diff);
		return convertLong.intValue();
	}

	public static int getYear(Date date) {
		Calendar cal = new GregorianCalendar();
		cal.setTime(date);
		return cal.get(1);
	}

	public static int getMonth(Date date) {
		Calendar cal = new GregorianCalendar();
		cal.setTime(date);
		int calendarMonth = cal.get(2);
		return calendarMonthToInt(calendarMonth);
	}

	public static int getDay(Date date) {
		Calendar cal = new GregorianCalendar();
		cal.setTime(date);
		return cal.get(5);
	}

	public static int getHour(Date date) {
		Calendar cal = new GregorianCalendar();
		cal.setTime(date);
		return cal.get(11);
	}

	public static int getMinute(Date date) {
		Calendar cal = new GregorianCalendar();
		cal.setTime(date);
		return cal.get(12);
	}

	private static int calendarMonthToInt(int calendarMonth) {
		if (calendarMonth == 0)
			return 1;
		if (calendarMonth == 1)
			return 2;
		if (calendarMonth == 2)
			return 3;
		if (calendarMonth == 3)
			return 4;
		if (calendarMonth == 4)
			return 5;
		if (calendarMonth == 5)
			return 6;
		if (calendarMonth == 6)
			return 7;
		if (calendarMonth == 7)
			return 8;
		if (calendarMonth == 8)
			return 9;
		if (calendarMonth == 9)
			return 10;
		if (calendarMonth == 10)
			return 11;
		return calendarMonth != 11 ? 1 : 12;
	}

	public static String format(Date date, String pattern) {
		SimpleDateFormat formatter = new SimpleDateFormat(pattern);
		return formatter.format(date);
	}

	private static int intToCalendarMonth(int month) {
		if (month == 1)
			return 0;
		if (month == 2)
			return 1;
		if (month == 3)
			return 2;
		if (month == 4)
			return 3;
		if (month == 5)
			return 4;
		if (month == 6)
			return 5;
		if (month == 7)
			return 6;
		if (month == 8)
			return 7;
		if (month == 9)
			return 8;
		if (month == 10)
			return 9;
		if (month == 11)
			return 10;
		return month != 12 ? 0 : 11;
	}
	
	/**
	 * 比较两个String格式(2004-01-01 00:00:00)的时间.
	 * 
	 * @param date1
	 *            被比较时间
	 * @param date2
	 *            比较时间
	 * @return result 前一个值大返回正1，后一个值大返回负1，相等返回0
	 */
	public static int compareTwoTime(String date1, String date2) {

		if ((date1 == null || "".equals(date1)) && (date2 == null || "".equals(date2))) {
			return 0;
		} else if (date1 == null || "".equals(date1)) {
			return -1;
		} else if (date2 == null || "".equals(date2)) {
			return 1;
		}
		
		if(date1.length()<11){
			date1=date1+" 00:00:00";
		}
		if(date2.length()<11){
			date2=date2+" 00:00:00";
		}
		int result = 0;
		Date date = null;
		Date dateAnother = null;
		SimpleDateFormat dateFomat = null;

		try {

			dateFomat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

			date = dateFomat.parse(date1);
			dateAnother = dateFomat.parse(date2);
			result = date.compareTo(dateAnother);

		} catch (ParseException pe) {
			pe.printStackTrace();
			System.err.println("Exception in compareTwoDate.");
			result = 1;
		}

		return result;

	}
	/**
	 * 比较两个String格式(2004-01-01)的时间.
	 * 
	 * @param date1
	 *            被比较时间
	 * @param date2
	 *            比较时间
	 * @return result 前一个值大返回正1，后一个值大返回负1，相等返回0
	 */
	public static int compareTwoDate(String date1, String date2) {

		if ((date1 == null || "".equals(date1)) && (date2 == null || "".equals(date2))) {
			return 0;
		} else if (date1 == null || "".equals(date1)) {
			return -1;
		} else if (date2 == null || "".equals(date2)) {
			return 1;
		}

		int result = 0;
		Date date = null;
		Date dateAnother = null;
		SimpleDateFormat dateFomat = null;

		try {

			dateFomat = new SimpleDateFormat("yyyy-MM-dd");

			date = dateFomat.parse(date1);
			dateAnother = dateFomat.parse(date2);
			result = date.compareTo(dateAnother);

		} catch (ParseException pe) {
			pe.printStackTrace();
			System.err.println("Exception in compareTwoDate.");
			result = 1;
		}

		return result;

	}

	/**
	 * 求出某一年某月的最后一天
	 * 
	 * @param year
	 * @param month
	 *            使用Calendar的常量
	 * @return
	 */
	public static final java.util.Date getLastDate4Month(int year, int month) {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.YEAR, year);
		c.set(Calendar.MONTH, month);

		return getDate(year, month, c.getActualMaximum(Calendar.DAY_OF_MONTH));

	}

	/**
	 * 求出距离当前月的月间隔为interval的那个月的最后一天
	 * 
	 * @param interval
	 *            月间隔
	 * @return
	 */
	public static final java.util.Date getLastDate4Month(int interval) {
		Calendar c = Calendar.getInstance();
		c.setLenient(true);
		c.set(Calendar.MONTH, c.get(Calendar.MONTH) + interval);
		int day = c.getActualMaximum(Calendar.DAY_OF_MONTH);
		return new GregorianCalendar(c.get(Calendar.YEAR), c.get(Calendar.MONTH), day).getTime();

	}

	/**
	 * 计算当前月的最后一天
	 * 
	 * @return
	 */
	public static final java.util.Date getLastDate4CurrMonth() {
		Calendar c = Calendar.getInstance();
		int day = Calendar.getInstance().getActualMaximum(Calendar.DAY_OF_MONTH);
		return new GregorianCalendar(c.get(Calendar.YEAR), c.get(Calendar.MONTH), day).getTime();
	}

	/**
	 * 计算到昨天
	 * 
	 * @return
	 */
	public static final String getYestoday() {
		return addCalendar(new Date(), Calendar.DATE, -1);
	}
	/**
	 * 计算到明天
	 */
	public static final String getTomorrow() {
		return addCalendar(new Date(), Calendar.DATE, 1);
	}	
	/**
	 * 计算到后天
	 */
	public static final String getDayAfterTomorrow() {
		return addCalendar(new Date(), Calendar.DATE, 2);
	}		
}
