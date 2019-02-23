package com.bc.protocol.util;


import com.alibaba.fastjson.JSON;
import io.netty.util.CharsetUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class PacketRequest{

	private int channelLength = 8;// 渠道
	private int actinaLength = 50;// 接口编号
	private int timeStampLength = 14;// 时间戳
	private int encryptStrLength = 16;// 密文
	private int headLength = 88;
	private final static int RES_HEAD_LENGTH = 16;    // 包头的长度
	
	public SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
	private static final Logger log = LoggerFactory.getLogger(PacketRequest.class);
	
	public byte[] encodeJson(String actina, Map<String, Object> paraMap) {
		byte[] channelData = new byte[channelLength];
		byte[] actinaData = new byte[actinaLength];
		byte[] encryptStrData = new byte[encryptStrLength];

		String requestData = JSON.toJSONString(paraMap);
		byte[] reqData = requestData.getBytes(CharsetUtil.UTF_8);

		System.arraycopy(paraMap.get("channel").toString().getBytes(), 0, channelData, 0, paraMap.get("channel").toString().getBytes().length);

		System.arraycopy(actina.getBytes(), 0, actinaData, 0, actina.getBytes().length);
		byte[] byte_3 = new byte[headLength + reqData.length];
		
		int pos = 0;
		System.arraycopy(channelData, 0, byte_3,pos, channelLength);
		pos = pos +  channelLength;
		System.arraycopy(actinaData, 0, byte_3,pos, actinaLength);
		pos = pos +  actinaLength;
		System.arraycopy(sdf.format(new Date()).getBytes(), 0, byte_3,pos, timeStampLength);
		pos = pos +  timeStampLength;
		System.arraycopy(encryptStrData, 0, byte_3,pos, encryptStrLength);//密文
		pos = pos +  encryptStrLength;
		System.arraycopy(reqData, 0, byte_3,pos, reqData.length);
		
		return byte_3;
	}

	/**
	 * 解析请求包的包体
	 * 
	 * @param data
	 *            请求包字节序列
	 * @return　byte[]
	 */
	public byte[] parseBody(byte[] data) {
		byte[] bodyData = new byte[data.length - RES_HEAD_LENGTH];
		System.arraycopy(data, RES_HEAD_LENGTH, bodyData, 0, data.length - RES_HEAD_LENGTH);
		return bodyData;
	}
	
}
