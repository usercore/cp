package com.qbb.util.client;

import com.alibaba.fastjson.JSON;
import io.netty.util.CharsetUtil;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

/**
 * @author      liwentang
 * @version     2.0
 * @create      2015-07-16 10:50:00 PM
 * @description 通讯包
 */
public class Packet extends BasePacket{
	private static final Logger log = Logger.getLogger(Packet.class);
	
	public static void main(String[] args) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ecode", 0);
		map.put("errormsg", "login  s success");
		
		String strJson = JSON.toJSONString(map);
		System.out.println("encode before:" + strJson);
		System.out.println("length:" + strJson.length());
		
		byte[] repsBytes = strJson.getBytes(CharsetUtil.UTF_8);
		
		byte pktType = Packet.PKT_REQUEST;
		int actina = 1001;
		byte compress = (byte)1;
		byte encrypt = (byte)0;
		
		Packet request = new Packet();
		byte[] encodeData = request.encodeJson(pktType, actina, compress, encrypt, repsBytes);
		System.out.println("::" + encodeData.toString());
		
		Packet parseRequest = new Packet();
		parseRequest.parse(encodeData);
		
		System.out.println("head body length:" + parseRequest.getHeadBodyLength());
		System.out.println("parse actina:" +parseRequest.getActina());
		System.out.println("comporess:" + parseRequest.getHeadCompress());
		System.out.println("encrypt:" + parseRequest.getHeadEncrypt());
		
		Packet pkt = new Packet();
		pkt.parseHead(encodeData);
		log.info("actina:" + pkt.getActina());
		log.info("bodylength:" + pkt.getHeadBodyLength());
		
		pkt.parseBody(encodeData, pkt.getHeadLength());
		byte[] bb = pkt.getReqBytes();
		String strBB = new String(bb, CharsetUtil.UTF_8);
		
		log.info("encode after:" + bb);
		log.info("encode after:" + strBB);
	}
	
	public int getHeadLength() {
		return HEADERLENGTH;
	}
	public byte[] encodeJson(byte pktType, int actina, byte[] reqData) {
		return encodeJson(pktType, actina, CT_NONE, ET_NONE, reqData);
	}
	
	public byte[] encodeJson(byte pktType, int actina, byte encryptType, byte[] reqData) {
		return encodeJson(pktType, actina, CT_NONE, encryptType, reqData);
	}
	
	public byte[] encodeJson(byte pktType, int actina, byte compressType, byte encryptType, byte[] reqData) {
		log.info("encode request data by jason form.");
		
		//初始化包头
		initHead(pktType, actina, compressType, encryptType);
		
		// 初始化包体
		initBody(reqData);
		
		// 获取包体字节序列
		byte[] bodyData = getBody();
		
		// 加密处理
		if(this.headEncrypt != ET_NONE) {
			bodyData = encryptData(bodyData, this.headEncrypt, true);
		}
		
		// 压缩处理
		if(this.headCompress != CT_NONE) {
			bodyData = compressData(bodyData, this.headCompress, true);
		}
		
		if(null == bodyData) {
			log.info("bodyData is null");
			this.headBodyLength = 0;
		}
		
		this.headBodyLength = bodyData.length;
		
		// 获取包头字节序列
		byte[] headData = getHead();
		
		int pktLength = headData.length + this.headBodyLength;
		byte[] pktData = new byte[pktLength];
		System.arraycopy(headData, 0, pktData, 0, headData.length);
		
		if(null != bodyData) {
			System.arraycopy(bodyData, 0, pktData, headData.length, bodyData.length);
		}
		
		// 返回请求包字节序列，包含包头和包体
		return pktData;
	}
	
	public byte[] encryptData(byte[] data, byte encryptType, boolean encrypt) {
		return data;
	}
	
	public byte[] compressData(byte[] data, byte compressType, boolean compress) {
		return data;
	}
	
	public void initHead(byte pktType, int actina,byte compressType, byte enctryType) {
		this.headBodyLength = 0;
		this.headTermType = TT_PC;
		this.headCompress = compressType;
		this.headEncrypt = enctryType;
		this.actina = actina;
		this.pktType = pktType;
		this.timestamp = System.currentTimeMillis();
		this.dataType = DT_JSON;
		this.headReserved[0] = 0x00;
		this.headReserved[1] = 0x00;
	}
	
	public byte[] getHead() {
		byte[] hdata = new byte[HEADERLENGTH];
		int pos = 0;
		
		// 向包头字节序列里写入包体长度和终端类型字段 
		KUtils.integer2Bytes(hdata, pos, this.headBodyLength);
		pos += 4;
		KUtils.short2Bytes(hdata, pos, this.headTermType);
		pos += 2;
		
		// 向包头字节序列里写入压缩方式字段和加密方式字段
		hdata[pos++] = this.headCompress;
		hdata[pos++] = this.headEncrypt;
		
		// 向包头字节序列里写入交易码
		KUtils.integer2Bytes(hdata, pos, this.actina);
		pos += 4;
		
		// 向包头字节序列里写入请求包类型
		hdata[pos++] = this.pktType;
		
		// 向包头字节序列里写入时间戳
		KUtils.integer2Bytes(hdata, pos, (int)this.timestamp);
		pos += 4;
		
		// 向包头字节序列里写入包体数据格式类型，目前是JSON格式
		KUtils.short2Bytes(hdata, pos, this.dataType);
		pos += 2;
		
		// 向包头字节序列里写入预留字段
		hdata[pos++] = this.headReserved[0];
		hdata[pos++] = this.headReserved[1];
		return hdata;
	}
	
	public void initBody(byte[] reqData) {
		this.reqBytes = reqData;
	}
	
	public byte[] getBody() {	
		return reqBytes;
	}
	
	public byte[] parse(byte[] data) {
		parseHead(data);
		return parseBody(data, this.getHeadLength());
	}
	
	public void parseHead(byte[] data) {
		parseHead(data, 0);
	}
	
	public void parseHead(byte[] data, int offset) {
		if((data.length - offset) < MIN_PACKET_LENGTH) {
			// 需要解码的数据不能小于包的最小长度
			log.error("Packet length less than MIN_PACKET_LENGTH");
			log.error("data:" + data.toString());
			
			throw new RuntimeException("解码的数据长度小于数据包的最小长度");
		}
		
		int pos = offset;
		
		// 解析请求包的包体长度和终端类型
		this.headBodyLength = KUtils.bytes2Integer(data,pos);
		pos += 4;
		this.headTermType = KUtils.bytes2Short(data, pos);
		pos += 2;
		
		// 解析压缩类型和加密方式
		this.headCompress = data[pos++];
		this.headEncrypt = data[pos++];
		
		// 解析交易码
		this.actina = KUtils.bytes2Integer(data, pos);
		pos += 4;
		
		// 解析数据包的类型，是请求还是响应
		this.pktType = data[pos++];
		
		// 解析时间戳和包体的数据类型（目前是JSON格式）
		this.timestamp = KUtils.bytes2Integer(data, pos);
		pos += 4;
		this.dataType = KUtils.bytes2Short(data, pos);
		pos += 2;
		
		// 解析预留字段
		this.headReserved[0] = data[pos++];
		this.headReserved[1] = data[pos++];
	}
	/**
	 * 解析请求包的包体
	 * @param data 请求包字节序列
	 * @param offset　到包体的偏移字节数
	 * @return　byte[]
	 */
	public byte[] parseBody(byte[] data, int offset) {
		byte[] bodyData = new byte[this.headBodyLength];
		System.arraycopy(data, offset, bodyData ,0, this.headBodyLength);
		
		// 解压缩处理，目前不压缩
		if(this.headCompress != CT_NONE) {
			bodyData = compressData(bodyData, this.headCompress, false);
		}
		
		// 解密处理，目前不加密
		if(this.headEncrypt != ET_NONE) {
			bodyData = encryptData(bodyData, this.headEncrypt, false);
		}
		
		this.reqBytes = null;
		this.reqBytes = bodyData;
		
		return this.reqBytes;
	}
}
