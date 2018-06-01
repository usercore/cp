package com.qbb.util.client;


/**
 * @author      liwentang
 * @version     2.0
 * @create      2015-07-15 10:50:00 PM
 * @description 通讯包
 */
public class BasePacket {
	/** 
	 * 通讯数据包说明：
	 *     1 公共包头部分
	 *     2 包体部分
	 *       1 用户请求数据
	 */
	
	// 基础包头部分 (共10个字节) 
	protected int headBodyLength; // 4B 通讯包体长度
	protected short headTermType; // 2B 终端类型标识
	protected byte headCompress;  // 1B 包体的压缩方式
	protected byte headEncrypt;   // 1B 包体的加密方式
	protected int actina;         // 4B 服务ID
	protected byte pktType;       // 1B 数据包类型
	protected long timestamp;     // 4B 时间戳
	protected short dataType;     // 2B 用户请求数据类型(JSON、XML)，目前只支持JSON
	
	protected byte[] headReserved = new byte[2];  // 2B 预留字段，暂不使用,以备不时之需
	
	// 包体长度定义 
	public final static int HEADERLENGTH = 21;    // 包头的长度
	public final static int BODYLENGTH = 24;      // 包体（不包含数据部分）的长度
	// 请求的终端类型 
	public final static byte TT_PC = 0x01;        // PC端
	public final static byte TT_ANDROID = 0x02;   // Android客户端
	public final static byte TT_IOS = 0x03;       // IOS客户端
	// 下面是压缩类型定义
	public final static byte CT_NONE = 0x00;      // 不压缩
	public final static byte CT_GZIP = 0x01;      // GZIP压缩
	// 下面是加密类型定义
	public final static byte ET_NONE = 0x00;      // 不加密
	public final static byte ET_3DES = 0x01;      // 3DES加密
	// 数据包的请求类型
	public final static byte PKT_REQUEST = 0x00;  // 请求数据包
	public final static byte PKT_RESPONSE = 0x01; // 响应数据包
	
	// 用户请求数据格式  
	public static final short DT_NONE = 0x00;     // 用户的请求数据为文本格式
	public static final short DT_JSON = 0x01;     // 用户的请求数据为JSON格式
	public static final short DT_XML  = 0x02;     // 用户的请求数据为XML格式
	
	public static final int MIN_PACKET_LENGTH = HEADERLENGTH;
	
	// 数据包的最后一部分是用户的请求数据 
	byte[] reqBytes;

	public int getHeadBodyLength() {
		return headBodyLength;
	}

	public void setHeadBodyLength(int headBodyLength) {
		this.headBodyLength = headBodyLength;
	}

	public short getHeadTermType() {
		return headTermType;
	}

	public void setHeadTermType(short headTermType) {
		this.headTermType = headTermType;
	}

	public byte getHeadCompress() {
		return headCompress;
	}

	public void setHeadCompress(byte headCompress) {
		this.headCompress = headCompress;
	}

	public byte getHeadEncrypt() {
		return headEncrypt;
	}

	public void setHeadEncrypt(byte headEncrypt) {
		this.headEncrypt = headEncrypt;
	}

	public int getActina() {
		return this.actina;
	}
	
	public void setActina(int actina) {
		this.actina = actina;
	}

	public byte[] getHeadReserved() {
		return headReserved;
	}

	public void setHeadReserved(byte[] headReserved) {
		this.headReserved = headReserved;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public short getDataType() {
		return dataType;
	}

	public void setDataType(short dataType) {
		this.dataType = dataType;
	}


	public byte[] getReqBytes() {
		return reqBytes;
	}

	public void setReqBytes(byte[] reqBytes) {
		this.reqBytes = reqBytes;
	}

	public byte getPktType() {
		return pktType;
	}

	public void setPktType(byte pktType) {
		this.pktType = pktType;
	}
}
