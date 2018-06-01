package com.qbb.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class MyCalendar {
	
	public static void main(String[] args) throws ParseException {
		getMonthAndDay("2017-06-30","2019-05-30");
		
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
		        	int lastmonthday = getlastDay(startdate); //获取开始时间最大天数
		        	startdate = DateUtil.YYYY_MM_DD.parse(start);
		 	    	enddate = DateUtil.YYYY_MM_DD.parse(end);
		 	    	System.out.println(enddate.getDate()+"---"+lastmonthday+"--"+startdate.getDate());
		        	if(enddate.getDate() >= lastmonthday){
		        		day = lastmonthday - startdate.getDate();
		        		//开始时间最大天数-开始天数，enddate.getDate()：还款日的天数
		        	}else{
		        		day = enddate.getDate() - startdate.getDate();  
		        		System.out.println(day);
		        	}
		        }else{
		        	day = enddate.getDate() - startdate.getDate();  
		        }
		       
		    } else {
		    	System.out.println("33333333333333");
		    	month = (enddate.getYear() - startdate.getYear()) * 12   + enddate.getMonth() - startdate.getMonth() - 1;  
		        System.out.println(month+",111111111");
		    	int lastmonthday = getlastDay(startdate); 
		    	 System.out.println(lastmonthday+",111111111");
		        System.out.println(lastmonthday);
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
	 * 得到上一个月有多少天 
	 */
	/*public static int getlastmonthDat(Date date) {  
	    date.setDate(1);  
	    date.setDate(date.getDate() - 1);  
	    return date.getDate();  
	} */ 
	
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
	
	/**
	 * 字符串的日期格式的计算 
	 * @param smdate
	 * @param bdate
	 * @return
	 * @throws ParseException
	 */
	public static int daysBetween(String smdate,String bdate){  
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
        Calendar cal = Calendar.getInstance();    
        try {
			cal.setTime(sdf.parse(smdate));
			long time1 = cal.getTimeInMillis();                 
	        cal.setTime(sdf.parse(bdate));    
	        long time2 = cal.getTimeInMillis();         
	        long between_days=(time2-time1)/(1000*3600*24);  
	            
	        return Integer.parseInt(String.valueOf(between_days)); 
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}    
        return 0;
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
}
