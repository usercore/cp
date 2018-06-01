package com.qbb.util.client;

import com.alibaba.fastjson.JSON;
import io.netty.util.CharsetUtil;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.log4j.Logger;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.Date;
import java.util.Map;

/**
 * @author liwentang
 * @version 2.0
 * @create 2015-07-15 16:13:00 PM
 * @description
 */
public class QbbServiceClient {
	private static final Logger log = Logger.getLogger(QbbServiceClient.class);

	public static Map<String, Object> doPost(String url, String actina,
			Map<String, Object> map) throws IOException {
		return QbbServiceClient.doPost(url, actina, (byte) 0, (byte) 0, map);
	}
	
	public static Map<String, Object> doPost(String url, String actina,
			byte encryptType, Map<String, Object> map) throws IOException {
		return QbbServiceClient.doPost(url, actina, (byte) 0,
				(byte) encryptType, map);
	}

	@SuppressWarnings("unchecked")
	public static Map<String, Object> doPost(String url, String actina,
			byte compressType, byte encryptType, Map<String, Object> map)
			throws IOException {

		String requestData = JSON.toJSONString(map);
		byte[] reqData = requestData.getBytes(CharsetUtil.UTF_8);

		Packet request = new Packet();
		byte[] reqPkt = request.encodeJson(Packet.PKT_REQUEST, Integer.parseInt(actina),
						compressType, (byte) encryptType, reqData);
		HttpURLConnection httpConnection = null;

		try {
			URL myurl = new URL(url);
			// 打开HTTP连接
			httpConnection = (HttpURLConnection) myurl.openConnection();
			// 设置HTTP请求的选项,请求中必须包含Content-Length选项
			httpConnection.setRequestProperty("accept", "*/*");
			httpConnection.setRequestProperty("connection", "Keep-Alive");
			httpConnection.setRequestProperty("Content-Length", String.valueOf(reqPkt.length));
			System.out.println(reqPkt);
			// 打开输入输出流
			httpConnection.setDoOutput(true);
			httpConnection.setDoInput(true);

			// 发送请求数据
			httpConnection.getOutputStream().write(reqPkt);

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
				response.parseHead(responseBuf);
				// 解析响应包的包体
				byte[] repsBuf = response.parseBody(responseBuf, response.getHeadLength());
				// 将包体字节流用utf-8转化为字符串
				String strJson = new String(repsBuf, CharsetUtil.UTF_8);
				log.info("recv data:" + strJson);

				Map<String, Object> returnMap = (Map<String, Object>) JSON.parse(strJson);
				return returnMap;
			}

		} catch(ConnectTimeoutException ex) {
			// 连接异常
			throw new RuntimeException("连接超时异常");
		} catch(SocketTimeoutException  ex) {
			// 超时异常
			printException(ex);
			throw new RuntimeException("Socket超时异常");
		} catch(IOException ex) {
			// IO异常
			printException(ex);
			throw new RuntimeException("IO异常");
		} finally {
			// 关闭HTTP连接
			if (null != httpConnection)
				httpConnection.disconnect();
		}

		return null;
	}
	
	private static void printException(Exception cause) {
		String beginStr = DateUtil.dateToString(new Date());
		log.error("exception catch time:[" + beginStr + "]");
		
		StackTraceElement[] ste = cause.getStackTrace();
		StringBuffer sb = new StringBuffer();
		sb.append(cause.getMessage() + "\r\n");
		for(int i = 0; i < ste.length; ++i) {
			sb.append(ste[i].toString() + "\r\n");
		}
		log.error(sb.toString());
	}
}
