package com.bc.gateway;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import com.alibaba.dubbo.common.json.JSON;
import com.alibaba.dubbo.common.json.JSONObject;
import com.bc.gateway.reqPacket.Packet;
import com.bc.util.exception.BusinessException;

/**   
 *    
 */
public abstract class ABSProtocol {
	Logger logger = Logger.getLogger(ABSProtocol.class);
	protected   SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	protected DecimalFormat df = new DecimalFormat("0.00");
	
	public String invoke(Packet packet)throws BusinessException{
		//解析请求数据,验证数据合法性
		decodeReqInfo(packet);
		//调用协议处理过程
		process();
		return encodeRes();
	}
	

	/**
	 * 
	* @Description: 解析报文
	* @return void    返回类型
	* @throws
	 */
	protected abstract void decodeReqInfo(Packet packet)throws BusinessException;

	/**
	 * 
	* @Description: 协议处理过程  
	* @return void    返回类型
	* @throws
	 */
	protected abstract void process()throws BusinessException;
	/**
	 * 
	* @Description: 组装返回字符
	* @return String    返回类型
	* @throws
	 */
	protected abstract String encodeRes() throws BusinessException;
	
	protected String convertJson(Map<String,String> map,Map<String,List<Object>> objectMap,Map<String,String[]> propertys) {
		StringBuffer sb = new StringBuffer("{\"erorcd\":\"000000\",\"errmsg\":\"\",");
		for (String key : map.keySet()) {
			sb.append("\""+key+"\":"+map.get(key)+",");
		}
		for (String key : objectMap.keySet()) {
			sb.append("\""+key+"\":[");
			for (Object obj : objectMap.get(key)) {
				try {
					sb.append(JSON.json(obj, propertys.get(key))+",");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(sb.lastIndexOf(",") == sb.length()-1){
				sb.deleteCharAt(sb.length()-1).append("]");
			}else{
				sb.append("]");
			}
		}
		return sb.append("}").toString();
	}
	
	protected String getJsonValue(JSONObject jsonObject,String key) {
		Object o = jsonObject.get(key);
		if(o==null){
			return "";
		}else{
			return o.toString();
		}
	}
}
