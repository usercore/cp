package com.bc.util;

import java.util.Properties;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public final class PropertiesFileUtil {

	public static String getProperty(Properties properties, String key, String encoding) throws UnsupportedEncodingException {
		if (properties == null) {
			return null;
		}
		String value = properties.getProperty(key);
		if (value == null) {
			return null;
		}
		value = new String(value.getBytes("ISO8859-1"), encoding);
		return value;
	}

	private static Properties prop = new Properties();
	static {
		InputStream in = PropertiesFileUtil.class.getResourceAsStream("/recharge.properties");
		try {
			prop.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 私有构造方法，不需要创建对象
	 */
	private PropertiesFileUtil() {
	}

	public static String getRechargeParam(String proKey) {
		return prop.getProperty(proKey);
	}

	/*public static String getRechargeParam(String proKey) {

		try {
			InputStream is = new FileInputStream("recharge.properties");
			Properties pt = new Properties();
			pt.load(is);
			return getProperty(pt, proKey, "gbk");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}*/


	public static void main(String[] args) {
		System.out.println(getRechargeParam("debug"));
	}
}
