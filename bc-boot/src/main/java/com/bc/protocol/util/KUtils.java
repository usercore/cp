package com.bc.protocol.util;


import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.Date;

/**
 * <p>Description: 字符串分割、编码等工具</p>
 * <p>Copyright: Copyright (c) 2015</p>
 * <p>Company: qian88</p>
 * @version 1.0
 */
public final class KUtils {
    //private static final String Char_Enc0 = "ISO8859-1"; //字符编码方式1
    //private static final String Char_Enc1 = "ISO8859_1"; //字符编码方式2

    /**
     * 从字节数组中指定的位置读取一个Integer类型的数据。
     * @param       b   字节数组
     * @param       pos  指定的开始位置
     * @return     一个Integer类型的数据
     */
    public static int bytes2Integer(byte[] b, int pos) {
        int val = 0;
        val = b[pos + 3] & 0xff; // 多字节数据，低8位在前面的字节。
        val <<= 8;
        val |= b[pos + 2] & 0xff;
        val <<= 8;
        val |= b[pos + 1] & 0xff;
        val <<= 8;
        val |= b[pos] & 0xff;
        return val;
    }

    /**
     * 从字节数组中指定的位置读取一个Short类型的数据。
     * @param       b   字节数组
     * @param       pos  指定的开始位置
     * @return     一个Short类型的数据
     */
    public static short bytes2Short(byte[] b, int pos) {
        int val = 0;
        val = b[pos + 1] & 0xFF; // 多字节数据，低8位在前面的字节。
        val = val << 8;
        val |= b[pos] & 0xFF;
        return (short) val;
    }

    /**
     * 从字节数组中指定的位置读取一个String类型的数据。
     * @param       b   字节数组
     * @param       pos  指定的开始位置
     * @param       len  指定的字节长度
     * @return     一个String类型的数据
     */
    public static String bytes2StringZ(byte[] b, int pos, int len) {
        int strLen = b.length - pos;
        if (strLen <= 0) {
            return "";
        }
        int i = 0;
        int rlen = len < strLen ? len : strLen;
        for (; i < rlen; i++) {
            if (b[pos + i] == 0) {
                break;
            }
        }
        return bytesToString(b, pos, i);
    }
    
    public static String bytes2StringZ2Utf8(byte[] b, int pos, int len) {
        int strLen = b.length - pos;
        if (strLen <= 0) {
            return "";
        }
        int i = 0;
        int rlen = len < strLen ? len : strLen;
        for (; i < rlen; i++) {
            if (b[pos + i] == 0) {
                break;
            }
        }
        String retStr = null;
        if (b != null) {
        	try {
				retStr = new String(b, pos, len, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        return retStr;
    }

    /**
     * 从字节数组中指定的位置获得一个String类型数据的长度。[以\0结尾]
     * @param       b   字节数组
     * @param       pos  指定的开始位置
     * @return     一个String类型数据的长度
     */
    public static int bytes2StringZlen(byte[] b, int pos) {
        int i = pos;
        for (; i < b.length; i++) {
            if (b[i] == 0) {
                break;
            }
        }
        return (i - pos + 1);
    }

    public static int bytes2Stringlen(byte[] b, int pos) {
        int i = pos;
        for (; i < b.length; i += 2) {
            if (b[i] == 0 && b[i + 1] == 0) {
                break;
            }
        }
        return (i - pos + 1);
    }

    /**
     * 转换一个二进制数组为字符串
     * @param bytes 需要转换的二进制数组
     * @return 转换后的字符串
     */
    public static String bytesToString(byte[] bytes) {
        return bytesToString(bytes, 0, bytes.length);
    }

    /**
     * 转换一个二进制数组中指定长度数据为字符串
     * @param bytes 需要转换的二进制数组
     * @param off 开始转换的起始位置
     * @param len 转换的长度
     * @return 转换后的字符串
     */
    public static String bytesToString(byte[] bytes, int off, int len) {
        //System.out.println(System.getProperty("microedition.encoding"));
        String ret = null;
        if (bytes != null) {
            //if(Char_Enc_Type%2==0){
            //    if (Char_Enc_Type==2||Char_Enc_Type==0) {
            //        try { //首先我们强制转换成ISO8859-1方式
            //            ret = new String(bytes, off, len, Char_Enc0);
            //            Char_Enc_Type = 2;
            //        } catch (Exception e) {}
            //    }
            //    if (ret == null) {
            //        try { //再次尝试强制转换成ISO8859_1方式
            //            ret = new String(bytes, off, len, Char_Enc1);
            //            Char_Enc_Type=4;
            //        } catch (Exception e) {}
            //    }
            //}
            //if (ret == null) { //否则使用缺省编码方式
                ret = new String(bytes, off, len);
                Char_Enc_Type=1;
            //}

        }
        return ret;
    }
    public static int Char_Enc_Type;

    /**
     * 从字节数组中指定的位置读取一个String类型的数据。 解析WString类型的数据
     * 字节数据必须是已经编码为Unicode码的内容。
     * @param       b   字节数组
     * @param       pos  指定的开始位置
     * @param       len  指定的字节长度
     * @return     一个String类型的数据
     */
    public static String bytes2String(byte[] b, int pos, int len) {
        int i = 0;
        if (len > b.length) {
            i = b.length / 2;
        } else {
            i = len / 2;
        }
        char[] cs = new char[i];
        int j;
        for (j = 0; j < i; j++) {
            cs[j] = (char) (b[pos + 2 * j + 1] & 0xFF);
            cs[j] <<= 8;
            char c = (char) (b[pos + 2 * j] & 0xFF);
            cs[j] += c;
            if (cs[j] == (char) 0) {
                break;
            }
        }
        return (new String(cs, 0, j)).trim();
    }

    /**
     * 将一个short 类型的数据转换为字节并存入到指定的字节数组指定的位置。
     * @param       b   字节数组
     * @param       pos  指定的开始位置
     * @param       val short类型的数据。
     */
    public static void short2Bytes(byte[] b, int pos, short val) {
        b[pos + 1] = (byte) (val >>> 8 & 0xff);
        b[pos] = (byte) (val & 0xff);
    }
    public static byte[] short2Bytes(short val) {
      byte[] b = new byte[2];
      b[1] = (byte) (val >>> 8 & 0xff);
      b[0] = (byte) (val & 0xff);
      return b;
    }
    /**
     * 将一个short 类型的数据转换为字节并存入到指定的字节数组指定的位置。高位在前
     * @param       b   字节数组
     * @param       pos  指定的开始位置
     * @param       val short类型的数据。
     */
    public static void shortToBytes(byte[] b, int pos, short val) {

        for (int i = 0; i < 2; i++) {
            b[pos + i] = (byte) (val >>> (8 - i * 8));
        }
    }

    /**
     * 将一个int类型的数据转换为字节并存入到指定的字节数组指定的位置。
     * @param       b   字节数组
     * @param       pos  指定的开始位置
     * @param       val int类型的数据。
     */
    public static void integer2Bytes(byte[] b, int pos, int val) {
        b[pos + 3] = (byte) (val >>> 24 & 0xff); // 低位在前
        b[pos + 2] = (byte) (val >>> 16 & 0xff);
        b[pos + 1] = (byte) (val >>> 8 & 0xff);
        b[pos] = (byte) (val & 0xff);
    }

    /**
     * 将一个int类型的数据转换为字节并存入到指定的字节数组指定的位置,高位在前
     * @param b
     * @param pos
     * @param val
     */
    public static void integerToBytes(byte[] b, int pos, int val) {
        for (int i = 0; i < 4; i++) {
            b[pos + i] = (byte) (val >>> (24 - i * 8));
        }

    }
    /**
     * 将一个int类型的数据转换为字节并存入到指定的字节数组指定的位置,高位在前
     * @param val int
     * @return byte[]
     */
    public static byte[] integerToBytes(int val) {
        byte[] b=new byte[4];
        for (int i = 0; i < 4; i++) {
            //b[i] = (byte) (val >>> (24 - i * 8));
            b[i] = (byte)((val  >>(i*8)) & 0xff);
        }

        return b;
    }

    /**
     * 将一个String类型的数据转换为字节并存入到指定的字节数组指定的位置。
     *
     * @param       b   字节数组
     * @param       pos  指定的开始位置
     * @param       val String类型的数据。
     */
    //public static void string2Bytes(byte[] b, int pos, String s) {
    //    int strLen = s.length() * 2;
    //    if (strLen < 1) {
    //        return;
    //    }
    //    char c;
    //    for (int j = 0; j < s.length(); j++) {
    //        c = s.charAt(j);
    //        b[pos + j * 2 + 1] = (byte) (c >>> 8 & 0xff);
    //        b[pos + j * 2] = (byte) (c & 0xff);
    //    }
    //}
    public static byte[] string2UnicodeBytes(String s) {
        if(s==null)return null;
        int strLen = s.length() * 2;
        byte[] buffer=new byte[strLen];
        char c;
        for (int j = 0; j < s.length(); j++) {
            c = s.charAt(j);
            buffer[j * 2 + 1] = (byte) (c >>> 8 & 0xff);
            buffer[j * 2] = (byte) (c & 0xff);
        }
        return buffer;
    }


    /**
     * 转换一个字符串为一个二进制字节数组
     * @param s 需要转换的字符串
     * @return 转换后的二进制字节数组
     */
    public static byte[] stringToBytes(String s) {
        return s==null?null:s.getBytes();
    }

    /**
     * 取得当前日期。
     * @return  字符串形式的当前日期（年、月、日）。
     */
    public static int getCurrentDate() {
        Calendar rightNow = Calendar.getInstance();
        int year = rightNow.get(Calendar.YEAR);
        int month = rightNow.get(Calendar.MONTH) + 1;
        int day = rightNow.get(Calendar.DATE);
        return (year * 10000 + month * 100 + day);
    }

    /**
     * 取得起始日期，和终止日期
     * @return
     */
    public static int[] getQSZZDate() {
        int[] date = new int[2];
        Calendar rightNow = Calendar.getInstance();
        int year = rightNow.get(Calendar.YEAR);
        int month = rightNow.get(Calendar.MONTH) + 1;
        int day = rightNow.get(Calendar.DATE);
        date[0] = year * 10000 + month * 100 + day;
        Date now = rightNow.getTime();
        now.setTime(now.getTime() - 6 * 24 * 3600 * 1000);
        rightNow.setTime(now);
        year = rightNow.get(Calendar.YEAR);
        month = rightNow.get(Calendar.MONTH) + 1;
        day = rightNow.get(Calendar.DATE);
        date[1] = year * 10000 + month * 100 + day;
        return date;
    }

    public static boolean checkDate(String data) {
        boolean rtn = false;

        return rtn;

    }
    /**
     * 求幂
     * @param num int
     * @param p int
     * @return int
     */
    public static int pow(int num,int m){
        int result = num;
        if(m==0)return 1;
        for(int i=1;i<m;i++)result=result*num;
        return result;
    }

    /**
     * 将UTF-8字节数据转化为Unicode字符串
     * @param utf_data byte[] - UTF-8编码字节数组
     * @param len int - 字节数组长度
     * @return String - 变换后的Unicode编码字符串
     */
    public static String UTF2Uni(byte[] utf_data, int len){
      StringBuffer unis = new StringBuffer();
      char unic = 0;
      int ptr = 0;
      int cntBits = 0;
      for (; ptr < len; ) {
        cntBits = getCntBits(utf_data[ptr]);
        if (cntBits == -1){
          ++ptr;
          continue;
        }else if (cntBits == 0){
          unic = UTFC2UniC(utf_data, ptr, cntBits);
          ++ptr;
        }else{
          unic = UTFC2UniC(utf_data, ptr, cntBits);
          ptr += cntBits;
        }
        unis.append(unic);
      }
      return unis.toString();
    }
    /**
     * 将指定的UTF-8字节组合成一个Unicode编码字符
     * @param utf byte[] - UTF-8字节数组
     * @param sptr int - 编码字节起始位置
     * @param cntBits int - 编码字节数
     * @return char - 变换后的Unicode字符
     */
    public static char UTFC2UniC(byte[] utf, int sptr, int cntBits){
      /*
        Unicode <-> UTF-8
        U-00000000 - U-0000007F:  0xxxxxxx
        U-00000080 - U-000007FF:  110xxxxx 10xxxxxx
        U-00000800 - U-0000FFFF:  1110xxxx 10xxxxxx 10xxxxxx
        U-00010000 - U-001FFFFF:  11110xxx 10xxxxxx 10xxxxxx 10xxxxxx
        U-00200000 - U-03FFFFFF:  111110xx 10xxxxxx 10xxxxxx 10xxxxxx 10xxxxxx
       U-04000000 - U-7FFFFFFF:  1111110x 10xxxxxx 10xxxxxx 10xxxxxx 10xxxxxx 10xxxxxx
       */
      int uniC = 0; //  represent the unicode char
      byte firstByte = utf[sptr];
      int ptr = 0; //  pointer 0 ~ 15
      // resolve single byte UTF-8 encoding char
      if (cntBits == 0)return (char) firstByte;
      // resolve the first byte
      firstByte &= (1 << (7 - cntBits)) - 1;
      // resolve multiple bytes UTF-8 encoding char(except the first byte)
      for (int i = sptr + cntBits - 1; i > sptr; --i){
        byte utfb = utf[i];
        uniC |= (utfb & 0x3f) << ptr;
        ptr += 6;
      }
      uniC |= firstByte << ptr;
      return (char) uniC;
    }
    // 根据给定字节计算UTF-8编码的一个字符所占字节数
    // UTF-8规则定义，字节标记只能为0或2~6
    public static int getCntBits(byte b){
      int cnt = 0;
      if (b == 0)return -1;
      for (int i = 7; i >= 0; --i){
        if ( ( (b >> i) & 0x1) == 1)
          ++cnt;
        else
          break;
      }
      return (cnt > 6 || cnt == 1) ? -1 : cnt;
    }
    /**
     * 根据标签取值
     * @param iStart int
     * @param strData String
     * @param label char
     * @param labelLib char[]
     * @return String
     */
    public static String getLabelValue(int iStart,String strData,String startLabel,String[] labelLib){
      if(strData==null||strData.length()<=iStart)return null;
      int startIndex=strData.indexOf(startLabel,iStart)+1;
      startIndex=startIndex>iStart?startIndex:iStart;
      int endIndex=0;
      for(int i=0;i<labelLib.length;i++){
        int tempIndex=strData.indexOf(labelLib[i],startIndex);
        if(i==0||tempIndex>=startIndex&&tempIndex<endIndex){
          endIndex=tempIndex;
        }
      }
      if(endIndex>=startIndex){
        while(startIndex<endIndex){
          if(strData.charAt(startIndex)==' '||strData.charAt(startIndex)=='　'){
            startIndex++;
          }else{
            break;
          }
        }
        return strData.substring(startIndex,endIndex);
      }else{
        while(startIndex<strData.length()-1){
          if(strData.charAt(startIndex)==' '||strData.charAt(startIndex)=='　'){
            startIndex++;
          }else{
            break;
          }
        }
        return strData.substring(startIndex);
      }
    }

    public static String validateCode;

	//#if shortname=='iq' && cpid==600337
//@    /**
//@     * 转换字符串为二进制数组，编码方式为UTF-8
//@     * @param s
//@     * @return
//@     */
//@    public static byte[] stringToBytes(String s,String enc)
//@    {
//@
//@      if (s == null || s.length() == 0)
//@      {
//@
//@        return null;
//@      }
//@
//@
//@
//@      if (enc == null || enc.length() == 0)
//@        enc = "UTF-8";
//@      try{
//@        return s.getBytes("UTF-8");
//@      }
//@      catch(Exception e)
//@      {
//@        return null;
//@      }
//@    }
//@
//@
//@    /**
//@     * 转换一个二进制数组中指定长度数据为字符串
//@     * @param bytes 需要转换的二进制数组
//@     * @param off 开始转换的起始位置
//@     * @param len 转换的长度
//@     * @param encode 编码类型，若为null,则默认按照GBK编码
//@     * @return 转换后的字符串
//@     */
//@    public static String bytesToString(byte[] bytes, int off, int len,int maxLen)
//@    {
//@      return bytesToString(bytes, off, len,maxLen,"UTF-8");
//@    }
//@
//@    /**
//@     * 转换一个二进制数组中指定长度数据为字符串
//@     * @param bytes 需要转换的二进制数组
//@     * @param off 开始转换的起始位置
//@     * @param len 转换的长度
//@     * @param encode 编码类型，若为null,则默认按照UTF-8编码
//@     * @return 转换后的字符串
//@     */
//@    public static String bytesToString(byte[] bytes, int off, int len,int maxLen,String enc)
//@    {
//@      String ret = null;
//@      if (enc == null) enc = "UTF-8";
//@      if (bytes != null) {
//@        if (len>maxLen)
//@          len = maxLen;
//@
//@        try {
//@          ret = new String(bytes, off, len, enc);
//@        }catch (Exception e){}
//@
//@        if (ret == null) { //否则使用缺省编码方式
//@          try {
//@            ret = new String(bytes, off, len);
//@          }catch (Exception e){}
//@        }
//@      }
//@      return ret;
//@
//@    }
    //#endif
    public static boolean isNum(String s){
        if(s==null||s.length()==0)return false;
        for(int i=0;i<s.length();i++){
            if(!Character.isDigit(s.charAt(i)))return false;
        }
        return true;
    }
}

