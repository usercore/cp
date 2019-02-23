package com.bc.gateway.handle.user;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.alibaba.dubbo.common.json.JSON;
import com.bc.gateway.impl.AbsCommProtocol;
import com.bc.user.info.domain.UserInfo;
import com.bc.user.info.service.IUserInfoService;
import com.bc.util.MD5Util;
import com.bc.util.exception.BusinessException;

@Service("modifyUserLoginPassword")
@Transactional
public class ModifyUserLoginPassword extends AbsCommProtocol {

	@Autowired
	private IUserInfoService userInfoService;

	@Override
	protected String process(Map<String, Object> paraMap, String serviceCode) throws BusinessException {
		System.out.println("用户修改密码");
		Map<String, Object> resultMap = new HashMap<>();
		String result = "";
       
		String newPassword = paraMap.get("newPassword").toString();
		String oldPassword = paraMap.get("oldPassword").toString();
		String userId = paraMap.get("userId").toString();
		
		int modifyResult = userInfoService.modifyUserLoginPassword(userId, oldPassword, newPassword);
		
		if(modifyResult == 0){
			resultMap.put("erorcd", "000001");
			resultMap.put("errmsg", "修改失败");
		}else{
			resultMap.put("erorcd", "000000");
			resultMap.put("errmsg", "修改成功");
		}

		try {
			result = JSON.json(resultMap).toString();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

}
