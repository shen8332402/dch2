package com.digitalchina.frame.util;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author LiangQingLu
 * @date 2017年4月28日 下午1:54:00
 */
public class CoreConstants {
	/**
	 * session中存放的当前登录人信息
	 */
	public static final String userInfo = "userInfo";

	public static final String PASSWORD_CRYPTO_ALGORITHM = "MD5";

	// **************************session保存的对象*****************************

	// SESSION中保存的用户信息
	public static String SESSION_USER_VIEW = "PermMgr";

	public static String SESSION_TOKEN_KEY = "TOKEN";

	// ***************************日期时间的格式*********************************
	public static String DB_DATE_FORMATTER = "YYYY-MM-DD";
	public static String DB_TIME_FORMATTER = "YYYY-MM-DD HH24:MI:SS";

	public static String JAVA_DATE_FORMATTER = "yyyy-MM-dd";
	public static String JAVA_TIME_FORMATTER = "yyyy-MM-dd HH:mm:ss";
	public static String JAVA_TIME_FORMATTER1 = "yyyy-MM-dd HH:mm";

	// ***************************系统配置信息********************************
	public static String DEF_CHARACTER_SET = "GBK";

	// ************************多值的分隔符*********************
	public static String SEPARATE = "||";

	public static String NOQUERY = "b_00_NOQUERY";

	// 更新全部缓存的代码

	public static final String REFRESH_ALL_CACHE = "REFRESH_ALL_CACHE";
	/**
	 * 分页查询拼装sql的参数操作类型
	 */
	public static final Map<String,String> pageMap = new HashMap<String,String>(){{
		put("eq", " = :");
		put("like", " like :");
		put("le", " >= :");
		put("ge", " <= :");
		put("in", " in (:");
	}};
}
