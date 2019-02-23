package com.bc.util;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Description: 
 * @author: hm
 * @Company: qianbaba
 * @E-mail: hanmin@qian88.com.cn
 * @version 创建时间：2014-8-7 下午03:04:27 
 */
public class AppFunction {
	 /**
     * 取非循环报文内的值。从钱爸爸公司报文中根据字段名，取字段的值，适合取该报文中只有一个该字段，不是循环
     * @param message 渠道过来的报文
     * @param fieldname 字段名称
     * @return
     */
    public static String getValueByCode(String message,String fieldname) throws Exception{
    	String value="";
    	if(fieldname==null || fieldname.equals("")){
    		return value;
    	}
    	if(!message.endsWith("|")){
    		message+="|";
    	}
    	if(message.indexOf(fieldname)>=0){    	
	    	fieldname=fieldname+"=";
			int start=message.indexOf(fieldname);
			int end=message.indexOf("|", start);
			value=message.substring(start, end).replaceAll(fieldname, "");
    	}
    	return value.trim();
    }
    
    
	
	/**
	 * /组装存储过程参数
	 * @param fieldList
	 * @param procna
	 * @return
	 */
	public static String getProcStr(int insize,String procna){
		
		  StringBuffer params = new StringBuffer("");
	        for(int i = 0; i < insize; i++) {
	            params.append("?,");
	        }
	        if(params.length() > 0) {
	            params = params.deleteCharAt(params.length() - 1);
	        }
	        StringBuffer procStr = new StringBuffer("{call ");
	        procStr.append(procna);
	        procStr.append("(");
	        procStr.append(params);
	        procStr.append(")}");
		
		return procStr.toString();
	}
	
	
	/** 
     * 格式化金额         
     * @param s 
     * @param len 
     * @return 
     */  
    public static String formatMoney(Object value, int len)   
    {  
    	String valueString = value.toString();
        if (valueString == null || valueString.length() < 1) {  
            return "";  
        }  
        NumberFormat formater = null;  
        double num = Double.parseDouble(valueString);  
        if (len == 0) {  
            formater = new DecimalFormat("###,###");  
  
        } else {  
            StringBuffer buff = new StringBuffer();  
            buff.append("###,###.");  
            for (int i = 0; i < len; i++) {  
                buff.append("#");  
            }  
            formater = new DecimalFormat(buff.toString());  
        }  
        String result = formater.format(num);  
        if(result.indexOf(".") == -1)  
        {  
            result = result + ".00";  
        }  
        else  
        {  
            result = result;  
        }  
        return "￥"+valueString;  
    }  
	

	
	
	 public static boolean isEmail(String str ) {
//		 Pattern pattern = Pattern.compile("^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$");
	         String regex = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
	         //p{Alpha}:内容是必选的，和字母字符[\p{Lower}\p{Upper}]等价。如：200896@163.com不是合法的。
	         //w{2,15}: 2~15个[a-zA-Z_0-9]字符；w{}内容是必选的。 如：dyh@152.com是合法的。
	         //[a-z0-9]{3,}：至少三个[a-z0-9]字符,[]内的是必选的；如：dyh200896@16.com是不合法的。
	         //[.]:'.'号时必选的； 如：dyh200896@163com是不合法的。
	         //p{Lower}{2,}小写字母，两个以上。如：dyh200896@163.c是不合法的。
	         
	        return match( regex ,str );
	    }
	 
	 
	 /**
	  * 判断大陆身份证合理性
	  * @param str
	  * @return
	  */
	 	public static boolean checkID(String str){ 
		    Pattern pattern = Pattern.compile("^([0-9]{17}[0-9X]{1})$"); 
		    return pattern.matcher(str).matches();    
		} 
	 
	 	/**
		  * 判断香港身份证合理性
		  * @param str
		  * @return
		  */
		 public static boolean checkHKID(String str){ 
			 Pattern pattern = Pattern.compile("^[a-zA-Z]{1}[0-9]{6}"); 
			 return pattern.matcher(str).matches();    
		} 
	 
	 /**
	  * 判断字符串是否为数字(不带小数点)
	  * @param str
	  * @return
	  */
	 	public static boolean isNumeric(String str){ 
		    Pattern pattern = Pattern.compile("[0-9]*"); 
		    return pattern.matcher(str).matches();    
		} 
	 
	 	
	 	
	 	/**
		  * 判断字符串是否为数字(带小数点)
		  * @param str
		  * @return
		  */
	 	public static boolean isDecimal(String str)   
		   {   
		       Pattern pattern= Pattern.compile("^(([1-9]{1}\\d*)|([0]{1}))(\\.(\\d){0,2})?$"); // 判断小数点后2位的数字的正则表达式
		       Matcher match=pattern.matcher(str);
		       if(match.matches()==false)   
		       {   
		          return false;   
		       }   
		       else   
		       {   
		          return true;   
		       }   
		   }  
	  /** 
	     * @param regex 正则表达式字符串
	     * @param str   要匹配的字符串
	     * @return 如果str 符合 regex的正则表达式格式,返回true, 否则返回 false;
	     */
	    private static boolean match( String regex ,String str ){
	        Pattern pattern = Pattern.compile(regex);
	        Matcher  matcher = pattern.matcher( str );
	        return matcher.matches();
	    }
	    
	    
	    /**
		 * 左补位
		 * @param src
		 * @param pad
		 * @param maxlength
		 * @return
		 */
		public static String lpad(String str, String pad, int maxlength) {
			if (str == null) {
				return "";
			}
			if (str.length() < maxlength) {
				StringBuilder b =  new StringBuilder();
				for (int i = 0; i < maxlength - str.length(); i++) {
					b.append(pad);
				}
				return b.toString() + str;
			} else {
				return str;
			}
		}
    

    public static void main(String[] args)
    {
    	
    	System.out.println("====>>"+formatMoney("316", 2));
//    	  List<Object> list = new ArrayList<Object>();  
//    	  
//    	Map hashMap = new HashMap();
//    	
//    	hashMap.put("hanmin", "11111");
//    	hashMap.put("zhangshan", "22222");
////    	JSONObject json = JSONObject.fromObject(hashMap); 
//    	
//    	Map hashMap1 = new HashMap();
//    	
//    	hashMap1.put("lisi", "33333");
//    	hashMap1.put("zhaowu", "22222");
//    	
//    	Map hashMap2 = new HashMap();
//    	
//    	hashMap2.put("aaaa", "555");
//    	hashMap2.put("bbbb", "666");
//    	
//    	
//    	list.add(hashMap);  
//    	list.add(hashMap1);  
//    	
//    	 JSONArray json = new JSONArray();  
//         json.addAll(list);  
//    	
//         Map aaaMap = new HashMap();
//         aaaMap.put("data", json);
//         aaaMap.put("aaaa", "555");
//         aaaMap.put("bbbb", "666");
//     	
//      	JSONObject jsonaaa = JSONObject.fromObject(aaaMap); 
//    	System.out.println("====>>"+"\"data\":"+json);// 
//    	System.out.println("====>>"+"\"jsonaaa\":"+jsonaaa);// 
    	
    }
}
