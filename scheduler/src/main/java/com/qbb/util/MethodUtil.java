package com.qbb.util;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;

import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.sql.Blob;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.*;

/**
 * Created by chen on 2017/2/15.
 */
public class MethodUtil {
    private static Log log = LogFactory.getLog(MethodUtil.class);

    // char转byte
    private static byte[] getBytes(char[] chars) {
        Charset cs = Charset.forName("UTF-8");
        CharBuffer cb = CharBuffer.allocate(chars.length);
        cb.put(chars);
        cb.flip();
        ByteBuffer bb = cs.encode(cb);

        return bb.array();

    }

    /**
     * @param bytesPrim
     * @return
     */
    public static Byte[] toBytes(byte[] bytesPrim) {
        Byte[] bytes = new Byte[bytesPrim.length];

        int i = 0;
        for (byte b : bytesPrim) bytes[i++] = b; // Autoboxing

        return bytes;
    }

    /**
     * @param oBytes
     * @return
     */
    public static byte[] toPrimitives(Byte[] oBytes) {
        byte[] bytes = new byte[oBytes.length];

        for (int i = 0; i < oBytes.length; i++) {
            bytes[i] = oBytes[i];
        }

        return bytes;
    }

    /**
     * Blod类型转换为String
     *
     * @param blobStr
     * @param defStr
     * @return
     */
    public static String blobTOString(Blob blobStr, String defStr) {
        try {
            StringBuffer blobSrt = new StringBuffer(500);
            InputStream in = blobStr.getBinaryStream();
            byte[] buff = new byte[(int) blobStr.length()];
            for (int buffi = 0; (buffi = in.read(buff)) > 0; ) {
                blobSrt.append(new String(buff, 0, buffi, "utf-8"));
            }
            in.close();
            return blobSrt.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return defStr;
    }

    public static boolean isEmpty(Object obj) {
        if (obj == null || obj.toString().length() == 0
                || obj.toString().trim().toLowerCase().equals("null")) {
            return true;
        }
        return false;
    }

    public static boolean isNotEmpty(Object obj) {
        return !isEmpty(obj);
    }

    /**
     * Object 转换为double类型
     *
     * @param objStr
     * @param def
     * @return
     */
    public static double converToDouble(Object objStr, double def) {
        try {
            if (objStr == null) {
                return def;
            }
            return Double.parseDouble(String.valueOf(objStr));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return def;
    }

    /**
     * Object 转换为String类型
     *
     * @param objStr
     * @return
     */
    public static String converToStr(Object objStr) {
        try {
            if (objStr == null) {
                return "";
            }
            return String.valueOf(objStr).trim();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * Object 转换为boolean类型
     *
     * @param objStr
     * @param def
     * @return
     */
    public static boolean converToBoolean(Object objStr, boolean def) {
        try {
            if (objStr == null) {
                return def;
            }
            return Boolean.parseBoolean(objStr.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return def;
    }

    /**
     * Object 转换为long类型
     *
     * @param objStr
     * @param def
     * @return
     */
    public static long converToLong(Object objStr, long def) {
        try {
            if (objStr == null) {
                return def;
            }
            if (MethodUtil.isEmpty(objStr.toString())) {
                return def;
            }
            return Long.parseLong(objStr.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return def;
    }

    /**
     * Object 转换为int类型
     *
     * @param objStr
     * @param def
     * @return
     */
    public static int converToInt(Object objStr, int def) {
        try {
            if (objStr == null) {
                return def;
            }
            return Integer.parseInt(objStr.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return def;
    }

    /**
     * 获得无重复的序列表
     *
     * @return
     */
    public static String uuid() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    /**
     * 首字母转换小写
     *
     * @param name
     * @return
     */
    public static String firstLower(String name) {
        char[] buffer = name.toCharArray();
        buffer[0] = Character.toLowerCase(name.charAt(0));
        return new String(buffer);
    }

    /**
     * 首字母转换大写
     *
     * @param name
     * @return
     */
    public static String firstUpper(String name) {
        char[] buffer = name.toCharArray();
        buffer[0] = Character.toUpperCase(name.charAt(0));
        return new String(buffer);
    }

    /**
     * 数值成货币格式
     *
     * @param money
     * @return
     */
    public static String formatMoney(double money) {
        NumberFormat nf = new DecimalFormat(",###.##");
        String formatStr = nf.format(money);
        return formatStr;
    }

    /**
     * 获得当天的小时数
     * 24制
     *
     * @return
     */
    public static int getHour() {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        return hour;
    }

    /**
     * 接口返回失败
     *
     * @return
     */
    public static Map<String, Object> fail(String bidCode, String errMs) {
        Map<String, Object> resultMap = new HashMap<String, Object>();

        resultMap.put("erorcd", bidCode);
        resultMap.put("errmsg", errMs);
        return resultMap;
    }

    /**
     * 接口返回成功
     *
     * @return
     */
    public static Map<String, Object> succ(String info) {
        Map<String, Object> resultMap = new HashMap<String, Object>();

        resultMap.put("erorcd", "000000");
        resultMap.put("errmsg", info);
        return resultMap;
    }

    public static boolean isSucc(Map<String, Object> resultMap) {
        try {
            String erorcd = MethodUtil.converToStr(resultMap.get("erorcd"));
            if ("000000".equals(erorcd)) {
                return true;
            }
            return false;
        } catch (Exception e) {
            CoreException.error(e);
        }
        return false;
    }

    /**
     * 接口返回成功
     *
     * @return
     */
    public static Map<String, Object> succ(String info, Object result) {
        Map<String, Object> resultMap = succ(info);
        resultMap.put("result", result);
        return resultMap;
    }

    /**
     * @param model
     * @return
     */
    public static Map<String, Object> convertMapByModel(Object model) {
        try {
            String string = JSONObject.toJSONString(model);
            return convertMapByString(string);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <E> E toBean(Map<String, Object> params, Class instance) {
        try {
            if (params == null) {
                params = new HashMap<String, Object>();
            }
            String string = JSONObject.toJSONString(params);
            E newInstall = (E) JSONObject.parseObject(string, instance);
            return newInstall;
        } catch (Exception e) {
            CoreException.error(e);
        }
        return null;
    }

    public static <E> E mapToBean(Map<String, Object> params, E instance) {
        try {
            String string = JSONObject.toJSONString(params);
            E newInstall = (E) JSONObject.parseObject(string, instance.getClass());
            return newInstall;
        } catch (Exception e) {
            CoreException.error(e);
        }
        return instance;
    }

    public static void copyProperties(Object source, Object target) {
        try {
            BeanUtils.copyProperties(source, target);
        } catch (Exception e) {
            CoreException.error(e);
        }
    }


    /**
     * @param string
     * @return
     */
    public static Map<String, Object> convertMapByString(String string) {
        Map map = JSONObject.parseObject(string, Map.class);
        return map;
    }

    /**
     * 获得最大页数
     *
     * @param countNumber
     * @param pageNumber
     * @return
     */
    public static long getMaxPage(long countNumber, long pageNumber) {
        long tempage = countNumber % pageNumber;
        if (tempage == 0) {
            return countNumber / pageNumber;
        }
        return countNumber / pageNumber + 1;
    }
}
