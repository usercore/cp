package com.bc.gateway;
/*package com.qbb.gateway;

import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;

import com.alibaba.dubbo.common.json.JSON;
import com.alibaba.dubbo.common.json.JSONObject;
import com.alibaba.dubbo.common.json.ParseException;
import com.qbb.gateway.reqPacket.Packet;
import com.qbb.protocol.util.CommunicationConfig;
import com.qbb.protocol.util.ProtocolUtil;
import com.qbb.protocol.util.XmlConfigUtil;
import com.qbb.util.exception.QbbBusinessException;

*//**   
 * 接口处理通用类   
 *//*
@Scope("prototype")
public abstract class AbstractProtocol  {
	
	Logger logger = Logger.getLogger(AbstractProtocol.class);
	
	
	public String invoke(Packet packet)throws QbbBusinessException{
		
		//调用接口理过程
		Map<String, Object> returnMap = process(packet);
		
		return encodeRes(packet,returnMap);
	}
	
	*//**
	* @Description: 接口处理过程  
	* @return Map<String, Object>    返回类型
	* @throws
	 *//*
	protected abstract Map<String, Object> process(Packet packet)throws QbbBusinessException;
	*//**
	 * 
	* @Description: 组装返回字符
	* @return String    返回类型,json格式字符串
	* @throws
	 *//*
	protected String encodeRes(Packet packet , Map<String, Object> returnMap) throws QbbBusinessException{
		CommunicationConfig communicationConfig = XmlConfigUtil.getPrcsConfig(getJsonValue(packet,"actiona"), "communication.xml");
		return ProtocolUtil.setResponseService(communicationConfig, returnMap);
	}
	
	protected String getJsonValue(Packet packet , String key) {
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject = (JSONObject) JSON.parse(packet.getBody());
		} catch (ParseException e) {
			throw new QbbBusinessException("000002", "请求参数错误");
		}
		Object o = jsonObject.get(key);
		if(o==null){
			return "";
		}else{
			return o.toString();
		}
	}
}
*/