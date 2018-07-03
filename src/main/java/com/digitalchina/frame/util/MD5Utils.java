package com.digitalchina.frame.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Utils {
	
	/**
	 * MD5和SHA1 多重加密
	 */
	public static String getSecurityPassword(String password) {
		String result = password;
		for (int i = 0; i < 2; i++) {
			result = SHA1(result);
			for (int j = 0; j < 3; j++) {
				result = MD5(result);
			}
		}

		for (int k = 0; k < 3; k++) {
			result = MD5(result);
			for (int j = 0; j < 2; j++) {
				result = SHA1(result);
			}
		}
		return result;
	}
	
	public static void main(String[] args) {
		System.out.println("密码加密="+getSecurityPassword("123"));
	}

	public static String MD5(String plain) {
		StringBuffer buf = new StringBuffer("");
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(plain.getBytes());
			byte b[] = md.digest();
			int i;
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0) {
					i += 256;
				}
				if (i < 16) {
					buf.append("0");
				}
				buf.append(Integer.toHexString(i));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return buf.toString();
	}

	public static String SHA1(String value) {
		StringBuffer sb = new StringBuffer();
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-1");

			md.update(value.getBytes("UTF-8"));
			byte[] result = md.digest();
			int k = 0;
			for (byte b : result) {
				int i = b & 0xff;
				if (i < 0xf) {
					sb.append(0);
				}
				sb.append(Integer.toHexString(i));
				++k;
				if (k >= 16) {
					break;
				}
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}


	/**
	 * 获取6位随机数
	 */
	public static String getRadomSixNum() {
		int rand = (int) ((Math.random() * 9 + 1) * 100000);
		return rand + "";
	}

	/**
	 * 获取3位随机数
	 */
	public static String getRadomThreeNum() {
		int rand = (int) ((Math.random() * 9 + 1) * 100);
		return rand + "";
	}

}
