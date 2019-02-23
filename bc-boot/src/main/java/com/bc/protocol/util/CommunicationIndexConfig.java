package com.bc.protocol.util;


import org.jdom.Element;

public class CommunicationIndexConfig {
	

	// 交易配置信息
	private Element prcsCfgEle;

	// 交易处理码
	private String actina = "";
	
	// 交易描述
	private String desc = "";
	
	// serivce服务编号
	private String serviceCode = "";
	
	//private String beanName = "commonProtocol";
	private String beanName = "apiGatewayProtocol";
	
	private String xmlPath = "";
		
	
	// 初始化交易配置对象
	public CommunicationIndexConfig(Element packetEle) {
		prcsCfgEle = packetEle;
		init();
	}

	// 初始化交易配置对象
	private void init() {
		try {
			// 交易处理码
			actina = prcsCfgEle.getAttributeValue("actina");
			
			// 交易名称
			desc = prcsCfgEle.getAttributeValue("desc");
			
			serviceCode = prcsCfgEle.getAttributeValue("serviceCode");
			
			if(prcsCfgEle.getAttributeValue("beanName") != null){
				beanName = prcsCfgEle.getAttributeValue("beanName");
			}
			
			xmlPath = prcsCfgEle.getAttributeValue("xmlPath");
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
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


	public String getServiceCode() {
		return serviceCode;
	}

	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}

	public String getBeanName() {
		return beanName;
	}

	public String getXmlPath() {
		return xmlPath;
	}
	
	

}
