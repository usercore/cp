package com.bc.gateway.reqPacket;

import com.bc.util.exception.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;

public class Packet  {
	private static final Logger log = LoggerFactory.getLogger(Packet.class);


	private String channel;// 渠道
	private String actina;// 接口编号
	private String timeStamp;// 时间戳
	private String encryptStr;// 密文
	private String body;//报文体

	private int channelLength = 8;// 渠道
	private int actinaLength = 50;// 接口编号
	private int timeStampLength = 14;// 时间戳
	private int encryptStrLength = 16;// 密文

	public final static int HEAD_LENGTH = 88; // 包头的长度

	public void parseData(byte[] data) {

		if ((data.length - 0) < HEAD_LENGTH) {
			// 需要解码的数据不能小于包的最小长度
			log.error("Packet length less than MIN_PACKET_LENGTH");
			try {
				log.error("data:" + new String(data, "UTF-8"));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}

			throw new BusinessException("解码的数据长度小于数据包的最小长度");
		}
		String dataStr = "";
	
		try {
			int pos = 0;
			dataStr = new String(data, "UTF-8");
			this.channel = dataStr.substring(pos,channelLength);
			pos = pos + channelLength;
			this.actina = dataStr.substring(pos,pos + actinaLength);
			pos = pos + actinaLength;
			this.timeStamp = dataStr.substring(pos,pos + timeStampLength);
			pos = pos + timeStampLength;
			this.encryptStr = dataStr.substring(pos,pos + encryptStrLength);
			pos = pos + encryptStrLength;
			this.body =  dataStr.substring(pos);
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	public byte[] responsePacket(String actina, String msg) throws UnsupportedEncodingException {
		int actinaLength = actina.length();
		if(actinaLength < this.actinaLength){
			for(int i=actinaLength;i<this.actinaLength;i++){
				actina = actina + " ";
			}
		}
		return (actina + msg).getBytes();
	}
	
	
	public void setBody(String body) {
		this.body = body;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getActina() {
		return actina;
	}

	public void setActina(String actina) {
		this.actina = actina;
	}

	public String getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getEncryptStr() {
		return encryptStr;
	}

	public void setEncryptStr(String encryptStr) {
		this.encryptStr = encryptStr;
	}
	public String getBody() {
		return body;
	}

}
