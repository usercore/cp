package com.ivan;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Test {
	public static void main(String[] args) {
		SimpleDateFormat sdfStart = new SimpleDateFormat("yyyy-MM-01 00:00:00");
		SimpleDateFormat sdfEnd = new SimpleDateFormat("yyyy-MM-dd 23:59:59");
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdfHHmmss = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String yearMonthDay = "2016-09-28 00:00:00";
		Date date  ;
		try {
			date = sdfHHmmss.parse(yearMonthDay);
			
			
			System.out.println(sdfHHmmss.parse(sdfStart.format(date)));
			
			Calendar cal = Calendar.getInstance();
			cal.setTime(sdfHHmmss.parse(sdfEnd.format(date)));
			int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
			cal.set(Calendar.DAY_OF_MONTH, lastDay);
			System.out.println(cal.getTime());
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
