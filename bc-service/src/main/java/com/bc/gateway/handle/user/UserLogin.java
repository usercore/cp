package com.bc.gateway.handle.user;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import com.alibaba.dubbo.common.json.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bc.gateway.impl.AbsCommProtocol;
import com.bc.user.info.domain.UserInfo;
import com.bc.user.info.service.IUserInfoService;
import com.bc.util.MD5Util;
import com.bc.util.exception.BusinessException;

@Service("userLogin")
public class UserLogin extends AbsCommProtocol {

	@Autowired
	private IUserInfoService userInfoService;

	@Override
	protected String process(Map<String, Object> paraMap, String serviceCode) throws BusinessException {
		System.out.println("用户登录");
		Map<String, Object> resultMap = new HashMap<>();
		String result = "";
		String userPhone = paraMap.get("userPhone").toString();
		String password = paraMap.get("password").toString();

		password = MD5Util.GetMD5Code(UserInfo.PWD_PRE + password);
		
		UserInfo userInfo = userInfoService.getUserInfoByPhone(userPhone);
		if (userInfo == null) {
			throw new BusinessException("用户名或密码错误");
		}

		Date lockTime = userInfo.getLocktime();
		if(lockTime == null){
			lockTime = new Date(0);
		}
		Integer pwrtCount = userInfo.getPwrtCount();
		if(pwrtCount == null){
			pwrtCount = 0;
		}
		if (pwrtCount >= 5 && (new Date().getTime() - lockTime.getTime()) <= 5 * 60 * 100) {
			throw new BusinessException("密码输入次数过多，请您"
					+ (5 * 60 * 1000 - (new Date().getTime() - lockTime.getTime())) / 1000 + "秒后再试");
		}

		if (!userInfo.getLoginPwd().equals(password)) {
			userInfo.setPwrtCount(userInfo.getPwrtCount() + 1);
			if(userInfo.getPwrtCount() == 5 ){
				userInfo.setLocktime(new Date());
			}else if(userInfo.getPwrtCount() > 5){
				userInfo.setPwrtCount(1);
			}
			userInfoService.updateByPk(userInfo);
			
			throw new BusinessException("用户名或密码错误");
		}

		userInfo.setPwrtCount(0);
		userInfo.setLastLoginTime(userInfo.getLoginIme());
		userInfo.setLoginIme(new Date());
		userInfoService.updateByPk(userInfo);
		
		resultMap.put("userId", userInfo.getUserId());
		resultMap.put("erorcd", "000000");
		resultMap.put("errmsg", "登录成功");

		try {
			result = JSON.json(resultMap).toString();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

}
