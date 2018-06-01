package com.qbb.util;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import com.qbb.util.client.SSLClient;

public class Mo9APIUtil {
	
	private static Logger logger = Logger.getLogger(Mo9APIUtil.class);

	static String creditorCode = "qian88";
	static String key = "99e7da1eb3774494b5c2449b12d715ae";

	public static void main(String[] args) {
//		String url = "http://192.168.1.57:8280/api/route/v1/receiveBorrow";
		String url = "https://amsclone.mo9.com/assetsApi/api/route/v1/receiveBorrow";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("order_code", "1483765645155");
		map.put("borrow_sq", "sq1483768448964");
		map.put("status", "15");
		map.put("borrow_money", new BigDecimal("1500.00"));
		map.put("borrow_title", "title");
		map.put("allbid_time", "2017-04-21");
		map.put("allchips_money", new BigDecimal("1500.00"));
		map.put("refuse_reason", "11");

		System.out.println(testHttps(url, map));
	}
	
	public static String test(String url, Map<String, Object> map) {
		// 时间戳为必需参数
		map.put("timestamp", System.currentTimeMillis());
		String text = asciiSort(map, "&");
		String sign = sign(text, key);

		String parameter = text + "&creditorCode=" + creditorCode + "&sign=" + sign;
		logger.info("---发送地址为："+url+",发送参数为:"+parameter+" ---");
		String json = doPost(url, parameter);
		return json;
	}
	
	public static String testHttps(String url, Map<String, Object> map) {
		// 时间戳为必需参数
		map.put("timestamp", System.currentTimeMillis());
		String text = asciiSort(map, "&");
		String sign = sign(text, key);
		map.put("creditorCode", creditorCode);
		map.put("sign", sign);
		logger.info("---发送地址为："+url+",发送参数为:"+map+" ---");
		System.out.println("---发送地址为："+url+",发送参数为:"+map+" ---");
		String json = doPostHttps(url, map);
		return json;
	}
	
    public static String doPostHttps(String url,Map<String,Object> map){  
        HttpClient httpClient = null;  
        HttpPost httpPost = null;  
        String result = null;  
        try{  
            httpClient = new SSLClient();  
            httpPost = new HttpPost(url);  
            //设置参数  
            List<NameValuePair> list = new ArrayList<NameValuePair>();  
            Iterator iterator = map.entrySet().iterator();  
            while(iterator.hasNext()){  
                Entry<String,Object> elem = (Entry<String, Object>) iterator.next();
                String myValue="";
                if(elem.getValue()!=null){
                	myValue=elem.getValue().toString();
                }
                list.add(new BasicNameValuePair(elem.getKey(),myValue));  
            }  
            if(list.size() > 0){  
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list,"utf-8");  
                httpPost.setEntity(entity);  
            }  
            HttpResponse response = httpClient.execute(httpPost);  
            if(response != null){  
                HttpEntity resEntity = response.getEntity();  
                if(resEntity != null){  
                    result = EntityUtils.toString(resEntity,"utf-8");  
                }  
            }  
        }catch(Exception ex){  
            ex.printStackTrace();  
        }  
        return result;  
    } 

	public static String doPost(String url, String parameter) {
		String html = null;
		HttpURLConnection conn = null;
		conn = getHttpURLConnection(url);
		conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		conn.setDoInput(true);
		conn.setDoOutput(true);
		PrintWriter out = null;
		try {
			if (parameter != null && !parameter.equals("")) {
				out = new PrintWriter(conn.getOutputStream());
				out.write(parameter);
				out.flush();
			}
			if (conn.getResponseCode() == 200) {
				html = getContent(conn);
			} else {
				html = String.valueOf(conn.getResponseCode());
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				out.close();
			}
			conn.disconnect();
		}
		return html;
	}

	public static String doGet(String url) {
		String html = null;
		HttpURLConnection conn = null;
		conn = getHttpURLConnection(url);
		try {
			if (conn.getResponseCode() == 200) {
				html = getContent(conn);
			} else {
				html = String.valueOf(conn.getResponseCode());
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			conn.disconnect();
		}
		return html;
	}

	public static HttpURLConnection getHttpURLConnection(String url) {
		HttpURLConnection conn = null;
		try {
			URL u = new URL(url);
			conn = (HttpURLConnection) u.openConnection();
			conn.setRequestProperty("User-Agent",
					"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36");
			conn.setRequestProperty("Accept", "*/*");
			conn.setRequestProperty("Connection", "keep-alive");
			conn.setReadTimeout(10000);
			conn.setConnectTimeout(10000);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return conn;
	}

	public static String getContent(HttpURLConnection conn) {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		BufferedInputStream bis = null;
		InputStream in = null;
		try {
			in = conn.getInputStream();
			bis = new BufferedInputStream(in);
			byte[] buf = new byte[1024];
			int len = -1;
			while ((len = bis.read(buf)) != -1) {
				out.write(buf, 0, len);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				in.close();
				bis.close();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			return new String(out.toByteArray(), "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 原始文本生成签名串
	 * 
	 * @param text
	 * @param key
	 * @return
	 */
	public static String sign(String text, String key) {
		text = key + "|" + text;
		return MD5(text);
	}

	/**
	 * 将map中的key排序并输出为字符串
	 * 
	 * @param map
	 * @param split
	 *            分隔符
	 */
	public static String asciiSort(Map<String, Object> map, String split) {
		if (map == null || map.isEmpty()) {
			return  "";
		}
		try {
			StringBuilder sb = new StringBuilder();
			Object[] keys = map.keySet().toArray();
			Arrays.sort(keys);
			int last = keys.length - 1;
			for (int i = 0; i < keys.length; i++) {
				Object v = map.get(keys[i]);
				if (v instanceof String[]) {
					String[] arr = (String[]) v;
					for (int j = 0; j < arr.length; j++) {
						sb.append(keys[i]).append("=").append(URLEncoder.encode(arr[j] != null ? arr[j] : "", "utf-8"));
						if (j != arr.length - 1) {
							sb.append(split);
						}
					}
				} else {
					sb.append(keys[i]).append("=").append(URLEncoder.encode(v != null ? v.toString() : "", "utf-8"));
				}
				if (i != last) {
					sb.append(split);
				}
			}
			return sb.toString();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * MD5摘要
	 * 
	 * @param text
	 * @return
	 */
	public static String MD5(String text) {
		String md5 = null;
		try {
			md5 = MD5(text.getBytes("utf-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return md5;
	}

	/**
	 * MD5摘要
	 * 
	 * @param byteArray
	 * @return
	 */
	public static String MD5(byte[] byteArray) {
		String md5 = null;
		try {
			char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };

			// 获得MD5摘要算法的 MessageDigest 对象
			MessageDigest mdInst = MessageDigest.getInstance("MD5");
			// 使用指定的字节更新摘要
			mdInst.update(byteArray);
			// 获得密文
			byte[] md = mdInst.digest();
			// 把密文转换成十六进制的字符串形式
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			md5 = new String(str).toUpperCase();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return md5;
	}
}
