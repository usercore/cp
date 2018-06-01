package com.qbb.util;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;

/**
 * 字符串转换类
 * @author yinyungui
 * @2017-05-21
 */
public class StrFmtUtil {
	/**
	 * 字符串转换
	 * @param obj
	 * @return
	 */
	public static String toTreStr(Object obj){
		return obj==null|| obj.toString().equals("")?null:obj.toString();
	}
	
	/**
	 * 整型转换
	 * @param obj
	 * @return
	 */
	public static Integer toTreInt(Object obj){
		try {
			return obj==null|| obj.toString().equals("")?0:Integer.valueOf(obj.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 长整型转换
	 * @param obj
	 * @return
	 */
	public static Long toTreLong(Object obj){
		try {
			return obj==null|| obj.toString().equals("")?null:Long.valueOf(obj.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * BigDecimal转换
	 * @param obj
	 * @return
	 */
	public static BigDecimal toTreBigDecimal(Object obj){
		try {
			return obj==null|| obj.toString().equals("")?null:(new BigDecimal(obj.toString()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * YYYY-MM-DD日期转换
	 * @param obj
	 * @return
	 */
	public static Date toTreDateYYYYMMDD(Object obj){
		try {
			return obj==null || obj.toString().equals("")?null:(DateUtil.YYYY_MM_DD.parse(obj.toString()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * YYYY-MM-DD HH:mm:ss日期转换
	 * @param obj
	 * @return
	 */
	public static Date toTreDateYYYYMMDDHHMMSS(Object obj){
		try {
			return obj==null || obj.toString().equals("")?null:(DateUtil.YYYY_MM_DD_MM_HH_SS.parse(obj.toString()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
}
