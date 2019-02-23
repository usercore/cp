package com.bc.gateway.handle;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import net.sf.json.JSONObject;

import com.bc.gateway.impl.AbsCommProtocol;
import com.bc.protocol.util.QbbRpcClient;
import com.bc.util.exception.BusinessException;

/**
 * app网关调用
 *  * @author lizhenkui
 *
 */
@Service("commonQueryProtocol")
public class CommonQueryGatewayProtocol extends AbsCommProtocol{
	
	@Value("${commonQuery_gateway}")    
	private String commonQuery_gateway; 
	
	@Override
	protected String process(Map<String, Object> paraMap, String serviceCode) throws BusinessException {
		Map<String, Object> returnMap = null;
		JSONObject jsonObject = null;
		log.info("请求开始");
		try {
			
			log.info("请求开始，请求参数="+paraMap);
			
			returnMap = QbbRpcClient.doPost(commonQuery_gateway, serviceCode, paraMap);
			log.info("返回结果："+returnMap);
			
		} catch (Exception e) {
			throw new BusinessException("000003", "服务器忙");
		}
		jsonObject = JSONObject.fromObject(returnMap, convertJsonToString());
		return jsonObject.toString();
	}
	
}