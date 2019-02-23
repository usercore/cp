package com.bc.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BorrowUtil {

	public static String getMonth(String str) {
		Pattern p = Pattern.compile("(\\d+)月");
		Matcher m = p.matcher(str);
		String result = "";
		while (m.find()) {
			result = m.group(1);
		}
		return result;
	}

	public static String getDay(String str) {
		Pattern p = Pattern.compile("(\\d+)日");
		Matcher m = p.matcher(str);
		String result = "";
		while (m.find()) {
			result = m.group(1);
		}
		return result;
	}
}
