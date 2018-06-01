package com.qbb.jobs.pub.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间日期工具类
 * 
 *
 */
public class DateUtil {

	public static int getMonthMaxDay(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.DAY_OF_MONTH, 1);
		c.add(Calendar.MONTH, +1);
		c.add(Calendar.DAY_OF_MONTH, -1);
		return c.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 获取两个日期相差的天数
	 * 
	 * @param start
	 * @param end
	 * @return
	 */
	public static int differentDays(Date start, Date end) {
		Date s = parseDate(formatDate(start, "yyyyMMdd"), "yyyyMMdd");
		Date e = parseDate(formatDate(end, "yyyyMMdd"), "yyyyMMdd");
		Calendar c0 = Calendar.getInstance();
		Calendar c1 = Calendar.getInstance();
		c0.setTime(s);
		c1.setTime(e);
		long time = c1.getTimeInMillis() - c0.getTimeInMillis();
		int day = (int) (time / (24 * 3600 * 1000));
		return day;
	}

	/**
	 * 获取当前月份的最大天数
	 * 
	 * @return
	 */
	public static int getMaxDayOfCurrentMonth() {
		return getMaxDayOfMonth(new Date());
	}

	/**
	 * 获取指定日期所在月份的最大天数
	 * 
	 * @return
	 */
	public static int getMaxDayOfMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, 1);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		return calendar.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 获取当前日期所在月份
	 * 
	 * @return
	 */
	public static int getCurrentMonthOfYear() {
		return getMonthOfYear(new Date());
	}

	/**
	 * 获取指定日期所在月份
	 * 
	 * @return
	 */
	public static int getMonthOfYear(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.MONTH) + 1;
	}

	/**
	 * 获取当前日期所在年份
	 * 
	 * @return
	 */
	public static int getCurrentYear() {
		return getYear(new Date());
	}

	/**
	 * 获取指定日期所在的年份
	 * 
	 * @param date
	 * @return
	 */
	public static int getYear(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.YEAR);
	}

	/**
	 * 字符串格式化转Date
	 * 
	 * @param source
	 * @param pattern
	 * @return
	 */
	public static Date parseDate(String source, String pattern) {
		Date date = null;
		DateFormat format = new SimpleDateFormat(pattern);
		try {
			date = format.parse(source);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 日期格式化
	 * 
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String formatDate(Date date, String pattern) {
		String formatDate = null;
		DateFormat format = new SimpleDateFormat(pattern);
		formatDate = format.format(date);
		return formatDate;
	}

	public static Calendar getClearTimeCalendar(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar;
	}

}
