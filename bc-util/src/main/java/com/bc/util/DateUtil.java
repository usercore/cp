package com.bc.util;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class DateUtil{  
      
    static final String yyyy_mm_dd = "yyyy-MM-dd";  
      
    static final String yyyyMMdd = "yyyyMMdd";  
    
    static SimpleDateFormat yyyy_mm_ddFormat = new SimpleDateFormat("yyyy-MM-dd");
    
    public static SimpleDateFormat YYYYMMDDMMHHSSSSS = new SimpleDateFormat("yyyyMMddHHmmssss");
    
    static SimpleDateFormat sdfStart = new SimpleDateFormat("yyyy-MM-01 00:00:00");
    static SimpleDateFormat sdfEnd = new SimpleDateFormat("yyyy-MM-dd 23:59:59");
    static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static List<Calendar> holidayList;
	private static boolean holidayFlag;
	
    public static String dateFormat(Date date, String formatStr){
    	 SimpleDateFormat format = new SimpleDateFormat(formatStr);        
         return format.format(date); 
    }
    /** 
     * 获取当前日期 yyyy-MM-dd
     * @return 
     */  
    public static String getCurrentDate(){  
        SimpleDateFormat format = new SimpleDateFormat(yyyyMMdd);        
        return format.format(new Date());  
    }  
    /**
     * 
     * @param date
     * @param plus
     * @return
     */
    public static boolean compareCurrentDate(Date date,long plus){
    	long nowTime = System.currentTimeMillis();
    	long time = date.getTime()+plus;
    	return (time>nowTime);
    }

	/**
	 * 计算两个日期之间相差的天数
	 * 
	 * @param smdate
	 *            较小的时间
	 * @param bdate
	 *            较大的时间
	 * @return 相差天数
	 * @throws ParseException
	 */
	public static int daysBetween(Date smdate, Date bdate)
			throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		smdate = sdf.parse(sdf.format(smdate));
		bdate = sdf.parse(sdf.format(bdate));
		Calendar cal = Calendar.getInstance();
		cal.setTime(smdate);
		long time1 = cal.getTimeInMillis();
		cal.setTime(bdate);
		long time2 = cal.getTimeInMillis();
		long between_days = (time2 - time1) / (1000 * 3600 * 24);

		return Integer.parseInt(String.valueOf(between_days));
	}
    
    public static int subCurrentDate(String dateStr){
    	long result = 0;
    	SimpleDateFormat format = new SimpleDateFormat(yyyy_mm_dd); 
    	try {
			Date date1 = format.parse(dateStr);
			result = date1.getTime() - System.currentTimeMillis();
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
    	return (int)result/(1000*60*60*24);
    }
    public static int subDate(String dateStr1,String dateStr2){
    	long result = 0;
    	SimpleDateFormat format = new SimpleDateFormat(yyyy_mm_dd); 
    	try {
			Date date1 = format.parse(dateStr1);
			result = date1.getTime() - format.parse(dateStr2).getTime();
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
    	return (int)result/(1000*60*60*24);
    }
    
    public static String calculateRemainTime(Date nowDate,String endTime){
		String result = "";
		int dayCount = 0;
		int monthCount = 0;
		try {
			Date endDate = yyyy_mm_ddFormat.parse(endTime);
			Calendar calendar = Calendar.getInstance();
			Calendar calendarNow = Calendar.getInstance();
			calendarNow.setTime(nowDate);
			calendar.setTime(endDate);
			int month = calendar.get(Calendar.MONTH);
			int calendarNowMonth = calendarNow.get(Calendar.MONTH);
			
			int year = calendar.get(Calendar.YEAR);
			int calendarNowYear = calendarNow.get(Calendar.YEAR);
			monthCount = (year - calendarNowYear)*12;
			if(month-calendarNowMonth>=1||month-calendarNowMonth<0||monthCount>0){
				monthCount = month - calendarNowMonth-1+monthCount;
				dayCount = calendarNow.getMaximum(Calendar.DAY_OF_MONTH) - calendarNow.get(Calendar.DAY_OF_MONTH) ;
				dayCount = calendar.get(Calendar.DAY_OF_MONTH) - calendar.getMinimum(Calendar.DAY_OF_MONTH) + dayCount + 2;
			}else if(month - calendarNowMonth==0){
				dayCount = calendar.get(Calendar.DAY_OF_MONTH) - calendarNow.get(Calendar.DAY_OF_MONTH);
			}
			if(dayCount<0){
				monthCount = monthCount - 1;
				dayCount = 30 + dayCount%30;
			}else{
				monthCount = monthCount + dayCount/30;
				dayCount = dayCount%30;
			}
			
			
			if(monthCount==0){
				result = dayCount+"日";
				if(dayCount<0){
					result = "0日";
				}
			}else if(monthCount<0){
				result = "0日";
			}
			else{
				if(dayCount!=0){
					result = monthCount + "月" + dayCount+"日";
				}else{
					result = monthCount + "月";
				}
				
			}
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return result;
	}
   
	/**
	 * 得到当月实际天数
	 * 
	 * @return
	 */
	public static int getCurrentMonthLastDay() {
		Calendar a = Calendar.getInstance();
		a.set(Calendar.DATE, 1);// 把日期设置为当月第一天
		a.roll(Calendar.DATE, -1);// 日期回滚一天，也就是最后一天
		return a.get(Calendar.DATE);
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

    public static void main(String[] args) {
    	System.out.println(new DateUtil().calculateRemainTime(new Date(),"2016-8-26"));
	}
    
    /**
	 * 校验指定的日期是否在节日列表中 具体节日包含哪些,可以在HolidayMap中修改
	 * 
	 * @param src
	 *            要校验的日期(源)
	 * @version [s001, 2010-11-19]
	 * @author LiuYong
	 */
	public static boolean checkHoliday(Calendar src) {
		boolean result = false;
		if (holidayList == null) {
			initHolidayList();
		}
		// 先检查是否是周六周日(有些国家是周五周六)
		if (src.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY
				|| src.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
			return true;
		}
		for (Calendar c : holidayList) {
			if (src.get(Calendar.MONTH) == c.get(Calendar.MONTH)
					&& src.get(Calendar.DAY_OF_MONTH) == c
							.get(Calendar.DAY_OF_MONTH)) {
				result = true;
			}
		}
		return result;
	}
	
	/**
	 * 初始化节日List,如果需要加入新的节日,请在这里添加 加的时候请尽量使用Calendar自带的常量而不是魔鬼数字
	 * 注:年份可以随便写,因为比的时候只比月份和天
	 * 
	 * @version [s001, 2010-11-19]
	 * @author LiuYong
	 */
	private static void initHolidayList() {
		holidayList = new ArrayList<Calendar>();
		// 五一劳动节
		Calendar may1 = Calendar.getInstance();
		may1.set(Calendar.MONTH, Calendar.MAY);
		may1.set(Calendar.DAY_OF_MONTH, 1);
		holidayList.add(may1);

		Calendar may2 = Calendar.getInstance();
		may2.set(Calendar.MONTH, Calendar.MAY);
		may2.set(Calendar.DAY_OF_MONTH, 2);
		holidayList.add(may2);

		Calendar may3 = Calendar.getInstance();
		may3.set(Calendar.MONTH, Calendar.MAY);
		may3.set(Calendar.DAY_OF_MONTH, 3);
		holidayList.add(may3);

		Calendar h3 = Calendar.getInstance();
		h3.set(2000, 1, 1);
		holidayList.add(h3);

		Calendar h4 = Calendar.getInstance();
		h4.set(2000, 12, 25);
		holidayList.add(h4);

		// 中国母亲节：五月的第二个星期日
		Calendar may5 = Calendar.getInstance();
		// 设置月份为5月
		may5.set(Calendar.MONTH, Calendar.MAY);
		// 设置星期:第2个星期
		may5.set(Calendar.DAY_OF_WEEK_IN_MONTH, 2);
		// 星期日
		may5.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		holidayList.add(may5);
	}
	/**
	 * 计算工作日 具体节日包含哪些,可以在HolidayMap中修改
	 * 
	 * @param src
	 *            日期(源)
	 * @param adddays
	 *            要加的天数
	 * @exception throws [违例类型] [违例说明]
	 * @version [s001, 2010-11-19]
	 * @author LiuYong
	 */
	public static Calendar addDateByWorkDay(Calendar src, int adddays) {
		holidayFlag = false;
		for (int i = 0; i < adddays; i++) {
			// 把源日期加一天
			src.add(Calendar.DAY_OF_MONTH, 1);
			holidayFlag = checkHoliday(src);
			if (holidayFlag) {
				i--;
			}
		}
		src.set(Calendar.HOUR_OF_DAY, 0);
		src.set(Calendar.SECOND, 0);
		src.set(Calendar.MINUTE, 0);
		src.set(Calendar.MILLISECOND, 0);
		holidayList.clear();
		System.out.println("Final Result:"
				+sdf.format(src.getTime()));
		return src;
	}
	public static Date strToDate(String dateString) {
		Date date = null;
		try {
			date = sdf.parse(dateString);
		} catch (ParseException e) {
			date = new Date();
			e.printStackTrace();
		}
		return date;
	}
	
	
	/**
	 * 获当前日期
	 * 
	 * @param date
	 * @param dateFormat
	 * @return String
	 */
	public static String getCurrentDate(String dateFormat) {
		return new SimpleDateFormat(dateFormat).format(new Date());
	}

}  