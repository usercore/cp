package com.bc.gateway.handle.user;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.alibaba.dubbo.common.json.JSON;
import com.bc.gateway.impl.AbsCommProtocol;
import com.bc.user.fund.domain.UserFund;
import com.bc.user.fund.service.IUserFundService;
import com.bc.user.info.domain.UserInfo;
import com.bc.user.info.service.IUserInfoService;
import com.bc.util.MD5Util;
import com.bc.util.PhoneUtil;
import com.bc.util.exception.BusinessException;

@Service("userRegister")
@Transactional
public class UserRegister extends AbsCommProtocol {

	@Autowired
	private IUserInfoService userInfoService;
	@Autowired
	private IUserFundService userFundService;
	
	@Transactional
	@Override
	protected String process(Map<String, Object> paraMap, String serviceCode) throws BusinessException {
		System.out.println("用户注册");
		Map<String, Object> resultMap = new HashMap<>();
		String result = "";
       
		String userPhone = paraMap.get("userPhone").toString();
		String password = paraMap.get("password").toString();
		String nickName = paraMap.get("nickName").toString();
		String channel = paraMap.get("channel").toString();
		
		password = MD5Util.GetMD5Code(UserInfo.PWD_PRE + password);
		 
		if(!PhoneUtil.verifyPhone(userPhone)){
			throw new BusinessException("手机号码格式不正确，请重新输入");
		}
		//判断昵称是否被使用
		if(userInfoService.getUserInfoByUserName(nickName) != null){
			throw new BusinessException("昵称已被使用，请重新输入");
		}
		//判断手机号是否被注册
		if(userInfoService.getUserInfoByPhone(userPhone) != null){
			throw new BusinessException("该手机号已被注册，请重新输入");
		}
		
		String userId = UUID.randomUUID().toString().replace("-", "").toLowerCase();
		UserInfo userInfo = new UserInfo();
		userInfo.setUserId(userId);
		userInfo.setLoginPwd(password);
		userInfo.setTranPwd(password);
		userInfo.setLoginIme(new Date());
		userInfo.setRegisterTime(new Date());
		userInfo.setUserType("1");
		userInfo.setUserName(nickName);
		userInfo.setEnable("0");
		userInfo.setChannel(channel);
		userInfo.setUserPhone(userPhone);
		userInfoService.insert(userInfo);
		
		UserFund userFund = new UserFund();
		userFund.setUserId(userId);
		userFund.setUpdateTime(new Date().getTime());
		userFundService.insert(userFund);
		

		resultMap.put("erorcd", "000000");
		resultMap.put("errmsg", "注册成功");

		try {
			result = JSON.json(resultMap).toString();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

}
