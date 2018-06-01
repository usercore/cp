/**
 *
 */
package com.qbb.util.client;

import org.apache.commons.lang3.StringUtils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间工具类
 *
 * @author Administrator
 */
public class DateUtil {

    public final static DateFormat YYYY_MM_DD_MM_HH_SS = new SimpleDateFormat(
            "yyyy-MM-dd HH:mm:ss");

    public final static DateFormat MMDD = new SimpleDateFormat("MMdd");
    
    public final static DateFormat MMDD_2 = new SimpleDateFormat("MM月dd日");

    public final static DateFormat YYYYMM = new SimpleDateFormat("yyyyMM");

    public final static DateFormat YYYY_MM_DD = new SimpleDateFormat(
            "yyyy-MM-dd");
    public final static DateFormat YYYYMMDD = new SimpleDateFormat("yyyyMMdd");
    public final static DateFormat YYYY_MM_DD_2 = new SimpleDateFormat(
            "yyyy年MM月dd日");
    public final static DateFormat YYYYMMDDMMHHSSSSS = new SimpleDateFormat(
            "yyyyMMddHHmmssSSS");
    public final static DateFormat YYYY_MM_DD_3 = new SimpleDateFormat(
            "yyyy年MM月dd日 HH:mm");
    
	public final static DateFormat MM_HH_SS = new SimpleDateFormat("HH:mm:ss");
    
    /**
     * yyyy-MM-dd HH:mm
     */
    public static final String FORMAT15 = "yyyy-MM-dd HH:mm";

    public static String dateFormat(long date, String pattern) {
        return dateFormat(new Date(date), pattern);
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


    /**
     * 格式化日期
     *
     * @param date
     * @param pattern
     * @return
     */
    public static String dateFormat(Date date, String pattern) {
        SimpleDateFormat df = new SimpleDateFormat(pattern);
        return df.format(date);
    }

    public static String strToYY(String str) {
        if (StringUtils.isEmpty(str)) {
            return null;
        }
        if (str.length() < 4) {
            return null;
        }
        return str.substring(0, 4);
    }

    public static String strToMM(String str) {
        if (StringUtils.isEmpty(str)) {
            return null;
        }
        if (str.length() < 7) {
            return null;
        }
        return str.substring(5, 7);
    }

    public static String strToDD(String str) {
        if (StringUtils.isEmpty(str)) {
            return null;
        }
        if (str.length() < 10) {
            return null;
        }
        return str.substring(8, 10);
    }

    /**
     * 时间转换为yyyy-MM-dd HH:mm:ss格式的字符串
     *
     * @param date
     * @return
     */
    public static String dateToString(Date date) {
        return YYYY_MM_DD_MM_HH_SS.format(date);
    }

    public static String dateToYYYYMM(Date date) {
        return YYYYMM.format(date);
    }

    public static String DateToYYYY_MM_DD(Date date) {
        return YYYY_MM_DD.format(date);
    }

    public static String DateToMMDD() {
        return MMDD.format(new Date());
    }

    public static String DateToYYYY_MM_DD_2(Date date) {
        return YYYY_MM_DD_2.format(date);
    }

    public static String DateToYYYYMMDD(Date date) {
        return YYYYMMDD.format(date);
    }

    public static Date strToDate(String dateString) {
        Date date = null;
        try {
            date = YYYY_MM_DD_MM_HH_SS.parse(dateString);
        } catch (ParseException e) {
            date = new Date();
            e.printStackTrace();
        }
        return date;
    }

    public static Date strYMDToDate(String dateString) {
        Date date = null;
        try {
            date = YYYY_MM_DD.parse(dateString);
        } catch (ParseException e) {
            date = new Date();
            e.printStackTrace();
        }
        return date;
    }
    /**  
     * 计算两个日期之间相差的天数  
     * @param smdate 较小的时间 
     * @param bdate  较大的时间 
     * @return 相差天数 
     * @throws ParseException  
     */    
    public static int daysBetween(Date smdate,Date bdate) throws ParseException    
    {    
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
        smdate=sdf.parse(sdf.format(smdate));  
        bdate=sdf.parse(sdf.format(bdate));  
        Calendar cal = Calendar.getInstance();    
        cal.setTime(smdate);    
        long time1 = cal.getTimeInMillis();                 
        cal.setTime(bdate);    
        long time2 = cal.getTimeInMillis();         
        long between_days=(time2-time1)/(1000*3600*24);  
            
       return Integer.parseInt(String.valueOf(between_days));           
    }    

    /**
     * 计算两个时间之间相差的天数
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public static long diffDays(Date startDate, Date endDate) {

        long days = 0;
        long start = startDate.getTime();
        long end = endDate.getTime();
        // 一天的毫秒数1000 * 60 * 60 * 24=86400000
        days = (end - start) / 86400000;
        return days;
    }

    /**
     * 计算两个时间之间相差的分钟
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public static long diffMinutes(Date startDate, Date endDate) {
        long days = 0;
        long start = startDate.getTime();
        long end = endDate.getTime();
        // 一分钟的毫秒数1000 * 60
        days = (end - start) / 60000;
        return days;
    }

    public static long diffDays_(Date startDate, Date endDate) {
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");

        try {
            System.out.println(sdf1.parse(sdf1.format(startDate)));

            long days = 0;
            long start = sdf1.parse(sdf1.format(startDate)).getTime();
            long end = sdf1.parse(sdf1.format(endDate)).getTime();
            // 一天的毫秒数1000 * 60 * 60 * 24=86400000
            days = (end - start) / 86400000;
            return days;

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 日期加上月数的时间
     *
     * @param date
     * @param month
     * @return
     */
    public static Date dateAddMonth(Date date, int month) {
        return add(date, Calendar.MONTH, month);
    }

    /**
     * 日期加上天数的时间
     *
     * @param date
     * @return
     */
    public static Date dateAddDay(Date date, int day) {
        return add(date, Calendar.DAY_OF_YEAR, day);
    }

    /**
     * 日期加上年数的时间
     *
     * @param date
     * @param year
     * @return
     */
    public static Date dateAddYear(Date date, int year) {
        return add(date, Calendar.YEAR, year);
    }

    /**
     * 将字符串转换为日期
     *
     * @param str
     * @return
     * @throws ParseException
     */
    public static Date parseStringToDate(String str, String format) {

        DateFormat formatter = null;
        Date date = null;
        if (StringUtils.isNotBlank(str)) {

            if (StringUtils.isBlank(format)) {
                formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            } else {
                formatter = new SimpleDateFormat(format);
            }

            try {
                date = formatter.parse(str);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        return date;
    }

    /**
     * 日期转换为字符串
     *
     * @param date   日期
     * @param format 格式
     * @return 返回字符型日期
     */
    public static String parseDateToString(Date date, String format) {

        String result = "";
        DateFormat formatter = null;
        try {
            if (date != null) {
                if (StringUtils.isBlank(format)) {
                    formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                } else {
                    formatter = new SimpleDateFormat(format);
                }
                result = formatter.format(date);
            }
        } catch (Exception e) {
        }

        return result;
    }

    public static Timestamp parseUtilDateToTimestamp(Date date, String format) {

        return parseStringToTimestamp(parseDateToString(date, format), format);
    }

    public static Date parseTimestampToUtilDate(Timestamp date, String format) {

        return parseStringToDate(parseDateToString(date, format), format);
    }

    public static Timestamp parseStringToTimestamp(String dateStr, String format) {

        if (StringUtils.isBlank(dateStr)) {
            return null;
        }

        Date date = null;
        if (StringUtils.isBlank(format)) {
            date = parseStringToDate(dateStr, "yyyy-MM-dd HH:mm:ss");
        } else {
            date = parseStringToDate(dateStr, format);
        }

        if (date != null) {
            long t = date.getTime();
            return new Timestamp(t);
        } else {
            return null;
        }
    }

    /**
     * 计算剩余时间 (多少天多少时多少分)
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public static String remainDateToString(Date startDate, Date endDate) {
        StringBuilder result = new StringBuilder();
        long times = endDate.getTime() - startDate.getTime();
        if (times < -1) {
            result.append("过期");
        } else {
            long temp = 1000 * 60 * 60 * 24;
            // 天数
            long d = times / temp;

            // 小时数
            times %= temp;
            temp /= 24;
            long m = times / temp;
            // 分钟数
            times %= temp;
            temp /= 60;
            long s = times / temp;

            result.append(d);
            result.append("天");
            result.append(m);
            result.append("小时");
            result.append(s);
            result.append("分");
        }
        return result.toString();
    }

    /**
     * 获取当前日期的后三个月日期
     *
     * @return 三个月后的时间
     */
    public static Date getLastThreeMonth(Date date, int inday) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, inday);
        Date otherDate = cal.getTime();
        return otherDate;
    }


    /**
     * 比较日期大小
     *
     * @param DATE1
     * @param DATE2
     * @return
     */
    public static boolean compare_day(String DATE1, String DATE2) {
        try {
            Date dt1 = YYYY_MM_DD.parse(DATE1);
            Date dt2 = YYYY_MM_DD.parse(DATE2);
            if (dt1.getTime() < dt2.getTime()) {
                return true;
            } else if (dt1.getTime() > dt2.getTime()) {
                return false;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return false;
    }

    /**
     * 比较日期大小
     *
     * @param DATE1
     * @param DATE2
     * @return
     */
    public static boolean compare_date(String DATE1, String DATE2) {
        try {
            Date dt1 = YYYY_MM_DD_MM_HH_SS.parse(DATE1);
            Date dt2 = YYYY_MM_DD_MM_HH_SS.parse(DATE2);
            if (dt1.getTime() > dt2.getTime()) {
                return true;// dt1 在dt2前
            } else if (dt1.getTime() < dt2.getTime()) {
                return false;// dt1在dt2后
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return true;
    }

    private static Date add(Date date, int type, int value) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(type, value);
        return calendar.getTime();
    }

    /**
     * 获取当前时间减7天
     *
     * @param dateString
     * @param beforeDays
     * @return
     * @throws ParseException
     */
    public Date getDate7(String dateString, int beforeDays)
            throws ParseException {

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date inputDate = dateFormat.parse(dateString);
        Calendar cal = Calendar.getInstance();
        cal.setTime(inputDate);

        int inputDayOfYear = cal.get(Calendar.DAY_OF_YEAR);
        cal.set(Calendar.DAY_OF_YEAR, inputDayOfYear - beforeDays);
        return cal.getTime();
    }

    public static Date parseDate(String string) throws ParseException {
        DateFormat format = DateFormat.getDateInstance();
        return format.parse(string);
    }

    /**
     * @param date
     * @return
     */
    @SuppressWarnings("deprecation")
    public static String dateMonthBegin(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int firstDay = calendar.getActualMinimum(Calendar.DAY_OF_MONTH);

        Date firstDate = calendar.getTime();
        firstDate.setDate(firstDay);

        return DateToYYYY_MM_DD(firstDate);
    }

    /**
     * @param date
     * @return
     */
    @SuppressWarnings("deprecation")
    public static String dateMonthEnd(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int lastDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

        Date lastDate = calendar.getTime();
        lastDate.setDate(lastDay);

        return DateToYYYY_MM_DD(lastDate);
    }

}
