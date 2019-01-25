package com.bc.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class IdCardUtil {
	 public static int checkIdcard(String idcard) {
	    	String birthday = idcard.substring(6, 14);
			try {
				SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
				Calendar c = Calendar.getInstance();
				c.setTime(format.parse(birthday));
				c.add(Calendar.YEAR, 18);
				Date birthDate = c.getTime();
				Date date = new Date();
				int n = 0;
				if ((n = comparaDate(date, birthDate)) >0) {//已满18岁
					return 0;
				}
				c.add(Calendar.YEAR, -2);
				birthDate = c.getTime();
				if ((n = comparaDate(date, birthDate)) >= 0) {//16-18岁
					return 1;
				}
			} catch (ParseException e) {
				e.printStackTrace();
				return 3;
			}
			//未满16岁
			return 2;
		}
	 
	 public static int comparaDate(Date date1, Date date2) {
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
			if (date1.getYear() > date2.getYear()) {
				return 1;
			}
			if (date1.getYear() < date2.getYear()) {
				return -1;
			}

			if (date1.getMonth() > date2.getMonth()) {
				return 2;
			}
			if (date1.getMonth() < date2.getMonth()) {
				return -2;
			}
			if (date1.getDate() > date2.getDate()) {
				return 3;
			}
			if (date1.getDate() < date2.getDate()) {
				return -3;
			}
			return 0;
		}
}
