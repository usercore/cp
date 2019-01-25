package com.bc.util.rpc;

import com.alibaba.fastjson.JSON;
import com.bc.util.DateUtil;

import io.netty.util.CharsetUtil;
import org.apache.log4j.Logger;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class QbbRpcClient {
	protected final static Logger log = Logger.getLogger(QbbRpcClient.class);

	public static Map<String, Object> doPost(String url, String actina, Map<String, Object> map) {
		return QbbRpcClient.doPost(url, actina, (byte) 0, (byte) 0, map);
	}

	public static Map<String, Object> doPost(String url, String actina, byte encryptType, Map<String, Object> map) throws IOException {
		return QbbRpcClient.doPost(url, actina, (byte) 0, (byte) encryptType, map);
	}

	private static byte[] assemblyReqPara(Map<String, Object> paraMap, String serviceCode) {
		Packet request = new Packet();
		byte[] reqPkt = request.encodeJson(serviceCode, paraMap);
		log.info("请求开始，请求参数=" + paraMap);
		return reqPkt;

	}

	@SuppressWarnings("unchecked")
	public static Map<String, Object> doPost(String url, String actina, byte compressType, byte encryptType, Map<String, Object> map){

		byte[] reqPkt = assemblyReqPara(map, actina);

		HttpURLConnection httpConnection = null;

		String param = "param=";

		byte[] reqPkt1 = byteMerger(param.getBytes(), reqPkt);
		try {
			URL myurl = new URL(url);
			// 打开HTTP连接
			httpConnection = (HttpURLConnection) myurl.openConnection();
			// 设置HTTP请求的选项,请求中必须包含Content-Length选项
			httpConnection.setRequestProperty("accept", "*/*");
			httpConnection.setRequestProperty("connection", "Keep-Alive");
			httpConnection.setRequestProperty("Content-Length", String.valueOf(reqPkt.length));
			// 打开输入输出流
			httpConnection.setDoOutput(true);
			httpConnection.setDoInput(true);

			// 发送请求数据
			httpConnection.getOutputStream().write(reqPkt1);

			// 获取并校验响应码,200表示成功
			int statuscode = httpConnection.getResponseCode();
			if (statuscode != 200) {
				log.error("error http response code:" + statuscode);
			} else {
				int ch = 0;
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				while ((ch = httpConnection.getInputStream().read()) != -1) {
					baos.write(ch);
				}

				byte[] responseBuf = baos.toByteArray();

				Packet response = new Packet();
				// 解析响应包的头部
				//response.parseHead(responseBuf);
				// 解析响应包的包体
				byte[] repsBuf = response.parseBody(responseBuf);
				// 将包体字节流用utf-8转化为字符串
				String strJson = new String(repsBuf, CharsetUtil.UTF_8);
				log.info("recv data:" + strJson);

				Map<String, Object> returnMap = (Map<String, Object>) JSON.parse(strJson);
				return returnMap;
			}

		} catch (Exception e) {
			log.error("连接" + url + "网络异常：{}",e);
			// 将异常写入日志中
			printException(e);
		} finally {
			// 关闭HTTP连接
			if (null != httpConnection)
				httpConnection.disconnect();
		}

		Map<String, Object> returnMap = new HashMap<String, Object>();
		returnMap.put("erorcd", "666666");
		returnMap.put("errmsg", "连接不上服务");
		return returnMap;
	}

	private static void printException(Exception cause) {
		String beginStr = DateUtil.getCurrentDate("yyyy-MM-dd HH:mm:ss");
		log.error("exception catch time:[" + beginStr + "]");

		StackTraceElement[] ste = cause.getStackTrace();
		StringBuffer sb = new StringBuffer();
		sb.append(cause.getMessage() + "\r\n");
		for (int i = 0; i < ste.length; ++i) {
			sb.append(ste[i].toString() + "\r\n");
		}
		log.error(sb.toString());
	}

	public static byte[] byteMerger(byte[] byte_1, byte[] byte_2) {
		byte[] byte_3 = new byte[byte_1.length + byte_2.length];
		System.arraycopy(byte_1, 0, byte_3, 0, byte_1.length);
		System.arraycopy(byte_2, 0, byte_3, byte_1.length, byte_2.length);
		return byte_3;
	}

	public static Map<String, Object> post(String url,String actina, Map<String, Object> paramMap) {
		Map<String, Object> ret = null;
		ret = doPost(url, actina, paramMap);
		if (ret == null) {
			ret = new HashMap<String, Object>();
		}
		if (ret.get("erorcd") == null) {
			ret.put("erorcd", "9527");
		}
		if (ret.get("errmsg") == null) {
			ret.put("errmsg", "网络繁忙...");
		}
		return ret;
	}
}
