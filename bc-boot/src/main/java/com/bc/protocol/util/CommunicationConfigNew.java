package com.bc.protocol.util;

import java.util.ArrayList;
import java.util.List;

import org.jdom.Element;

public class CommunicationConfigNew {
	

	public String getIsNeedDecrypt() {
		return isNeedDecrypt;
	}

	// 交易配置信息
	private Element prcsCfgEle;

	// 交易处理码
	private String actina = "";
	// 交易处理码
	private String isNeedToken = "";
	
	// 交易描述
	private String desc = "";
	
	// serivce服务编号
	private String serviceCode = "";
		
	
	// 取得请求报文格式
	private List<Element> reqEleLs = null;
	
	// 取得返回报文格式
	private List<Element> rspEleLs = new ArrayList<Element>();
	
	// 交易错误码字段标识
	private String erorcd = "";
	// 交易错误信息字段标识
	private String errmsg = "";
	private String isNeedDecrypt = "false";
	// 初始化交易配置对象
	public CommunicationConfigNew(Element packetEle) {
		prcsCfgEle = packetEle;
		init();
	}

	// 初始化交易配置对象
	@SuppressWarnings("unchecked")
	private void init() {
		try {
			// 交易处理码
			actina = prcsCfgEle.getAttributeValue("actina");
			
			// 交易名称
			desc = prcsCfgEle.getAttributeValue("desc");
			
			serviceCode = prcsCfgEle.getAttributeValue("serviceCode");
			
			// 请求报文格式
			reqEleLs = prcsCfgEle.getChild("Request").getChildren("Field");
			// 返回报文格式
			rspEleLs = prcsCfgEle.getChild("Response").getChildren("Field");
			
			if(prcsCfgEle.getAttributeValue("isNeedDecrypt") != null){
				isNeedDecrypt = prcsCfgEle.getAttributeValue("isNeedDecrypt");
			}
			
			// 交易错误码字段标识
			erorcd = prcsCfgEle.getChildText("Erorcd");
			// 交易错误信息字段标识
			errmsg = prcsCfgEle.getChildText("Errmsg");
			
			if(prcsCfgEle.getAttributeValue("isNeedToken") != null){
				isNeedToken = prcsCfgEle.getAttributeValue("isNeedToken");
			}
			 
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public String getIsNeedToken() {
		return isNeedToken;
	}

	// 取得交易配置报文
	public Element getPrcsCfgEle() {
		return prcsCfgEle;
	}
	
	// 取得交易处理码
	public String getActina() {
		return actina;
	}
	
	
	// 取得交易名称
	public String getDesc() {
		return desc;
	}

	// 取得请求报文格式
	public List<Element> getReqEleLs() {
		return reqEleLs;
	}
	
	// 取得返回报文格式
	public List<Element> getRspEleLs() {
		return rspEleLs;
	}
	
	// 交易错误码字段标识
	public String getErorcd() {
		return erorcd;
	}
	
	// 交易错误信息字段标识
	public String getErrmsg() {
		return errmsg;
	}

	public String getServiceCode() {
		return serviceCode;
	}

	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}
	
	

}
