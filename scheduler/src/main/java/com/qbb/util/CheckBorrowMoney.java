package com.qbb.util;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
public class CheckBorrowMoney {
	public static void main(String[] args)
	{
		String parameter="{\"gys_borrowsq\":\"80000005127\"}";// JSON format String
		System.out.println(parameter);
		// post 提交
		String json=doPost("http://106.120.15.178:40001/callback/QianBaBa/queryRemnantPrincipal",parameter);
		try {
			System.out.println("返回结果："+URLDecoder.decode(json,"UTF-8"));
			String params =  URLDecoder.decode(json,"UTF-8");
			int a = params.indexOf("{");
			int b = params.indexOf("}");
			String data = params.substring(a,b+1);
			System.out.println(data);
			JSONObject obj= JSONObject.parseObject(data);
			System.out.println(obj.get("gys_borrowsq"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		/*String result=getResultData();
		System.out.println("返回结果(例):"+result);*/
	}

	public static String doPost(String url,String parameter)
	{
		String html=null;
		HttpURLConnection conn=null;
		conn=getHttpURLConnection(url);
		conn.setRequestProperty("Content-Type","application/json");
		conn.setDoInput(true);
		conn.setDoOutput(true);
		PrintWriter out=null;
		try
		{
			if(parameter!=null&&!parameter.equals(""))
			{
				out=new PrintWriter(conn.getOutputStream());
				out.write(parameter);
				out.flush();
			}
			if(conn.getResponseCode()==200)
			{
				html=getContent(conn);
			}
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		finally
		{
			if(out!=null)
			{
				out.close();
			}
			conn.disconnect();
		}
		return html;
	}

	public static HttpURLConnection getHttpURLConnection(String url)
	{
		HttpURLConnection conn=null;
		try
		{
			URL u=new URL(url);
			conn=(HttpURLConnection)u.openConnection();
			conn.setRequestProperty("User-Agent",
							"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36");
			conn.setRequestProperty("Accept","*/*");
			conn.setRequestProperty("Connection","keep-alive");
			conn.setReadTimeout(10000);
			conn.setConnectTimeout(10000);
		}
		catch(MalformedURLException e)
		{
			e.printStackTrace();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		return conn;
	}

	public static String getContent(HttpURLConnection conn)
	{
		ByteArrayOutputStream out=new ByteArrayOutputStream();
		BufferedInputStream bis=null;
		InputStream in=null;
		try
		{
			in=conn.getInputStream();
			bis=new BufferedInputStream(in);
			byte[] buf=new byte[1024];
			int len=-1;
			while((len=bis.read(buf))!=-1)
			{
				out.write(buf,0,len);
			}
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				in.close();
				bis.close();
				out.close();
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
		}
		try
		{
			return new String(out.toByteArray(),"utf-8");
		}
		catch(UnsupportedEncodingException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	/*static String merchant="admina";
	static String key="2FDB56383AFF0D8E9C30B6B2182D4563";

	@SuppressWarnings("unchecked")
	public static String getResultData()
	{
		String res="{\"errorCode\":\"0\",\"errorMsg\":\"ok\",\"data\":{\"gys_borrowsq\":\"1000001\",\"borrow_sq\":\"123456\",\"principal\":\"1000.00\"}}";
		Map<String,Object> hashMap=(Map<String,Object>)JSON.parse(res);
		hashMap.put("timestamp","1497956366647");//System.currentTimeMillis());
		// 对map中的key排序并输出为字符串
		String text=asciiSort(hashMap,"&");
		// 生成签名串
		String sign=sign(text,key);//sign=B689847FD57EFD3D65BA6C57C8D6C092
		// 所有请求必须加上这两个参数
		String parameter=text+"&merchant="+merchant+"&sign="+sign;
		return parameter;
	}
*/
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
