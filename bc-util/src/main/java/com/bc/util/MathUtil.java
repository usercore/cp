package com.bc.util;


import java.math.BigDecimal;

/**
 * @author gGW0327
 *
 * 更改所生成类型注释的模板为
 * 窗口 > 首选项 > Java > 代码生成 > 代码和注释
 */
public class MathUtil {

	public static void main(String[] args) {
		/*
		String a="1234567891234567891234788778875555555.5678912345678912123456789";
		String b="5.787555";
		System.out.println("**** add= "+Math.add(a,b));
		System.out.println("**** sub= "+Math.sub(a,b));
		System.out.println("**** mul= "+Math.round(Math.mul(a,b),4));
		System.out.println("**** div= "+Math.div(a,b));
		*/
		System.out.println("*** div="+MathUtil.roundTo5("20", -1));
	}

	/**
	
	 * 由于Java的简单类型不能够精确的对浮点数进行运算，这个工具类提供精
	
	 * 确的浮点数运算，包括加减乘除和四舍五入。
	
	 */

	//默认除法运算精度

	private static final int DEF_DIV_SCALE = 4;

	/**
	
	 * 提供精确的加法运算。
	
	 * @param v1 被加数
	
	 * @param v2 加数
	
	 * @return 两个参数的和
	
	 */

	public static String add(String v1, String v2) {
		
		if(v1==null || v1.equals(""))
			v1="0";
		if(v2==null || v2.equals(""))
			v2="0";

		BigDecimal b1 = new BigDecimal(v1);

		BigDecimal b2 = new BigDecimal(v2);

		return b1.add(b2).toString();

	}

	/**
	
	 * 提供精确的减法运算。
	
	 * @param v1 被减数
	
	 * @param v2 减数
	
	 * @return 两个参数的差
	
	 */

	public static String sub(String v1, String v2) {
		
		if(v1==null || v1.equals(""))
			v1="0";
		if(v2==null || v2.equals(""))
			v2="0";
		BigDecimal b1 = new BigDecimal(v1);

		BigDecimal b2 = new BigDecimal(v2);

		return b1.subtract(b2).toString();

	}

	/**
	
	 * 提供精确的乘法运算。
	
	 * @param v1 被乘数
	
	 * @param v2 乘数
	
	 * @return 两个参数的积
	
	 */

	public static String mul(String v1, String v2) {
		
		if(v1==null || v1.equals(""))
			v1="0";
		if(v2==null || v2.equals(""))
			v2="0";
		BigDecimal b1 = new BigDecimal(v1);

		BigDecimal b2 = new BigDecimal(v2);

		return b1.multiply(b2).toString();

	}

	/**
	
	 * 提供（相对）精确的除法运算，当发生除不尽的情况时，精确到
	
	 * 小数点以后10位，以后的数字四舍五入。
	
	 * @param v1 被除数
	
	 * @param v2 除数
	
	 * @return 两个参数的商
	
	 */

	public static String div(String v1, String v2) {

		return div(v1, v2, DEF_DIV_SCALE);

	}

	/**
	
	 * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指
	
	 * 定精度，以后的数字四舍五入。
	
	 * @param v1 被除数
	
	 * @param v2 除数
	
	 * @param scale 表示表示需要精确到小数点以后几位。
	
	 * @return 两个参数的商
	
	 */

	public static String div(String v1, String v2, int scale) {

		if (scale < 0) {

			throw new IllegalArgumentException("The scale must be a positive integer or zero");

		}
		
		if(v1==null || v1.equals(""))
			v1="0";
		if(v2==null || v2.equals(""))
			v2="1";
		BigDecimal b1 = new BigDecimal(v1);

		BigDecimal b2 = new BigDecimal(v2);

		return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).toString();

	}

	/**
	
	 * 提供精确的小数位四舍五入处理。
	
	 * @param v 需要四舍五入的数字
	
	 * @param scale 小数点后保留几位
	
	 * @return 四舍五入后的结果
	
	 */

	public static String round(String v, int scale) {
		if(v==null || v.equals(""))
			v="0";
		

		if (scale < 0) {

			throw new IllegalArgumentException("The scale must be a positive integer or zero");

		}

		BigDecimal b = new BigDecimal(v);

		return round(b,scale);

	}
	public static String round(BigDecimal b,int scale){
		if (scale < 0) {
			throw new IllegalArgumentException("The scale must be a positive integer or zero");
		}
		BigDecimal one = new BigDecimal("1");
		return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).toString();
	}
	public static String roundTo10(String v, int scale) {
		/*
		if (scale < 0) {

			throw new IllegalArgumentException("The scale must be a positive integer or zero");

		}
		*/
		BigDecimal b = new BigDecimal(v);
		b.add(new BigDecimal("4"));

		BigDecimal one = new BigDecimal("1");

		return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).toPlainString();

	}
	public static String roundTo5(String v, int scale) {
		/*
		if (scale < 0) {

			throw new IllegalArgumentException("The scale must be a positive integer or zero");

		}
		*/
		
		BigDecimal b = new BigDecimal(v);
		if(b.intValue()>=5)
			b=b.subtract(new BigDecimal("5"));
		else
			b=new BigDecimal(0);

		BigDecimal one = new BigDecimal("1");

		return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).toPlainString();

	}
	
	public static String convertStr(BigDecimal v){
		return v.toString();
	}

};
