/**
 *
 */
package com.qbb.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * 时间工具类
 * @author Administrator
 *
 */
public class DateUtil {

	public final static DateFormat YYYY_MM_DD_MM_HH_SS = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public final static DateFormat HH_MM_SS = new SimpleDateFormat("HH:mm:ss");
	public final static DateFormat MMDD = new SimpleDateFormat("MMdd");
	public final static DateFormat MM_HH_SS = new SimpleDateFormat("HH:mm:ss");
	public final static DateFormat YYYY_MM_DD = new SimpleDateFormat("yyyy-MM-dd");
	public final static DateFormat YYYYMMDD = new SimpleDateFormat("yyyyMMdd");
	public final static DateFormat YYYY_MM_DD_2 = new SimpleDateFormat("yyyy年MM月dd日");
	public final static DateFormat YYYYMMDDMMHHSSSSS = new SimpleDateFormat("yyyyMMddHHmmssSSS");
	public final static DateFormat YYYY_MM_DD_3=new SimpleDateFormat("yyyy年MM月dd日 HH:mm");


	public static String dateFormat(long date, String pattern) {
		return dateFormat(new Date(date), pattern);
	}


	/**
	 * 格式化日期
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String dateFormat(Date date, String pattern) {
		SimpleDateFormat df = new SimpleDateFormat(pattern);
		return df.format(date);
	}

	/**
	 * 时间转换为yyyy-MM-dd HH:mm:ss格式的字符串
	 * @param date
	 * @return
	 */
	public static String dateToYYYY_MM_DD_MM_HH_SS(Date date){
		return YYYY_MM_DD_MM_HH_SS.format(date);
	}

	public static String dateToYYYY_MM_DD(Date date){
		return YYYY_MM_DD.format(date);
	}


	public static String dateToYYYYMMDD(Date date){
		return YYYYMMDD.format(date);
	}



	public static String dateToMMDD(){
		return MMDD.format(new Date());
	}

	/**
	 * 获取当前时间的N天 N月  N年
	 * @param format  格式化
	 * @param StrDate 当前时间
	 * @param year  增加或减少年数
	 * @param month 增加或减少月数
	 * @param day 增加或减少天数
	 * @return
	 */
	public static String dateAddYMD(String format, String StrDate, int year,
			int month, int day) {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sFmt = new SimpleDateFormat(format);
		cal.setTime(sFmt.parse((StrDate), new ParsePosition(0)));

		if (day != 0) {
			cal.add(cal.DATE, day);
		}
		if (month != 0) {
			cal.add(cal.MONTH, month);
		}
		if (year != 0) {
			cal.add(cal.YEAR, year);

		}
		return sFmt.format(cal.getTime());
	}



	/**
	* 计算时间差（天）
	*
	* @param beginTime
	*            开始时间
	* @param endTime
	*            结束时间
	* @return 从开始时间到结束时间之间的时间差（天）
	*/
	public static int getTimeBetween(String format,String beginTime, String endTime) {
			Calendar calendar1=Calendar.getInstance();;
		    Calendar calendar2=Calendar.getInstance();;
		    SimpleDateFormat formatter1 = new SimpleDateFormat(format);//格式很重要：是20081031，还是2008-10-31格式呢？
		    if (beginTime.equals("0")) {System.out.println("sBirthDate.equals====0");   return 0;   }
		    try {
		    calendar1.setTime(formatter1.parse(beginTime));
		    calendar2.setTime(formatter1.parse(endTime));
		    } catch (ParseException e) {
			    e.printStackTrace();
			   }
		    System.out.println((int)( (calendar2.getTimeInMillis()-calendar1.getTimeInMillis())/1000/60/60/24 ));

		    return  (int)( (calendar2.getTimeInMillis()-calendar1.getTimeInMillis())/1000/60/60/24 );//获取天数的差值。

	}
	

	
	/**
	 * 格式化日期
	 * @param format
	 * @return
	 */
	public static String StringToYYYYMMDD(String format){
		  SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
		  Date date = null;
			try {
				date = formatter1.parse(format);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		return YYYY_MM_DD.format(date);
	}

	/**
	 * 获取时间格式类型对象
	 * @return
	 */
	public static Timestamp getTimestamp(){
	    Timestamp nousedate = null;
		try {
		     Date date = new Date();
		     nousedate = new Timestamp(date.getTime());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return nousedate;
	}


	/**
	 * 时间转换为yyyy-MM-dd HH:mm:ss格式的字符串
	 * @param date
	 * @return
	 */
	public static String dateToString(Date date){
		return YYYY_MM_DD_MM_HH_SS.format(date);
	}


	/**
	 * 功能描述：返回月
	 *
	 * @param date
	 *            Date 日期
	 * @return 返回月份
	 */
	public static int getMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.MONTH) + 1;
	}

	/**
	 * 功能描述：返回日
	 *
	 * @param date
	 *            Date 日期
	 * @return 返回日份
	 */
	public static int getDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 功能描述：返回小
	 *
	 * @param date
	 *            日期
	 * @return 返回小时
	 */
	public static int getHour(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.HOUR_OF_DAY);
	}

	/**
	 * 功能描述：返回分
	 *
	 * @param date
	 *            日期
	 * @return 返回分钟
	 */
	public static int getMinute(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.MINUTE);
	}

	/**
	 * 返回秒钟
	 *
	 * @param date
	 *            Date 日期
	 * @return 返回秒钟
	 */
	public static int getSecond(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.SECOND);
	}

	/**
	 * 功能描述：返回毫
	 *
	 * @param date
	 *            日期
	 * @return 返回毫
	 */
	public static long getMillis(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.getTimeInMillis();
	}

	
	/** 
	 * 得到两个时间之间为几个月和几天 2013-8-31 2013-10-5 
	 * @throws ParseException 
	 */  
	public static Map<String,Object> getMonthAndDay(String start, String end) {  
		Date startdate;
		Date enddate;
		int month = 0;  
		int day = 0;  ;
		try {
			startdate = DateUtil.YYYY_MM_DD.parse(start);
			enddate = DateUtil.YYYY_MM_DD.parse(end);
		    if (enddate.getDate() >= startdate.getDate()) {
		        month = (enddate.getYear() - startdate.getYear()) * 12  
		                + enddate.getMonth() - startdate.getMonth();  
		        if(enddate.getDate() > startdate.getDate()){
		        	int lastmonthday = getlastDay(startdate); 
		        	startdate = DateUtil.YYYY_MM_DD.parse(start);
		 	    	enddate = DateUtil.YYYY_MM_DD.parse(end);
		        	if(enddate.getDate() >= lastmonthday){
		        		day = lastmonthday - startdate.getDate();  
		        	}else{
		        		day = enddate.getDate() - startdate.getDate();  
		        	}
		        }else{
		        	day = enddate.getDate() - startdate.getDate();  
		        }
		       
		    } else {
		    	month = (enddate.getYear() - startdate.getYear()) * 12   + enddate.getMonth() - startdate.getMonth() - 1;  
		        int lastmonthday = getlastDay(startdate);  
		        startdate = DateUtil.YYYY_MM_DD.parse(start);
		    	enddate = DateUtil.YYYY_MM_DD.parse(end);
		        day = lastmonthday - startdate.getDate() + enddate.getDate();  
	        }  
		} catch (ParseException e) {
			e.printStackTrace();
		}
	   System.out.println("month:"+month+"   day:"+day);
	   Map<String,Object> map = new HashMap<String, Object>();
	   map.put("month", month);
	   map.put("day", day);
	   return map;
	   
	}  

	

	
	/** 
	 * 得到date 所在的月有多少天 
	 *  
	 * @param date 
	 * @throws ParseException 
	 */
	public static int getlastDay(Date date){
		date.setMonth(date.getMonth() + 1);  
	    date.setDate(1);  
	    date.setDate(date.getDate() - 1);  
	    return date.getDate();  
	}

	public static void main(String[] args) {
		String next_time="2016-07-01";
		if(31==31){
			try {
				//获取当期还款时间的最大天数 2016-10-22
				int max_day=DateUtil.getlastDay(DateUtil.YYYY_MM_DD.parse(next_time));
				 Calendar c = Calendar.getInstance();
				 c.setTime(DateUtil.YYYY_MM_DD.parse(next_time));
				 int month = c.get(Calendar.MONTH)+1;
				 int year = c.get(Calendar.YEAR);
				 next_time = year+"-"+month+"-"+max_day;
				 System.out.println(next_time);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	/**
	 * 字符串的日期格式的计算 格式 :yyyy-MM-dd
	 * 
	 * @param smdate
	 * @param bdate
	 * @return
	 * @throws ParseException
	 */
	public static int daysBetween(String smdate, String bdate) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.setTime(sdf.parse(smdate));
		long time1 = cal.getTimeInMillis();
		cal.setTime(sdf.parse(bdate));
		long time2 = cal.getTimeInMillis();
		long between_days = (time2 - time1) / (1000 * 3600 * 24);

		return Integer.parseInt(String.valueOf(between_days));
	}
	
	
	/**
	 * 计算时间差（天）
	 * 
	 * @param beginTime
	 *            开始时间
	 * @param endTime
	 *            结束时间
	 * @return 从开始时间到结束时间之间的时间差（天）
	 */
	public static int getTimeMin(String format, String beginTime,
			String endTime) {
		Calendar calendar1 = Calendar.getInstance();
		;
		Calendar calendar2 = Calendar.getInstance();
		;
		SimpleDateFormat formatter1 = new SimpleDateFormat(format);// 格式很重要：是20081031，还是2008-10-31格式呢？
		if (beginTime.equals("0")) {
			System.out.println("sBirthDate.equals====0");
			return 0;
		}
		try {
			calendar1.setTime(formatter1.parse(beginTime));
			calendar2.setTime(formatter1.parse(endTime));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		System.out.println((int) ((calendar2.getTimeInMillis() - calendar1
				.getTimeInMillis()) / 1000 / 60));

		return (int) ((calendar2.getTimeInMillis() - calendar1
				.getTimeInMillis()) / 1000 / 60);// 获取天数的差值。

	}
}
