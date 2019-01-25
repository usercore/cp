
package com.bc.user.info.service;


import com.bc.user.info.dao.UserInfoMapper;
import com.bc.user.info.domain.UserInfo;
import com.bc.util.MD5Util;
import com.bc.util.db.BaseServiceImpl;
import org.springframework.stereotype.Service;

import com.bc.util.db.IBaseMapper;
import com.bc.util.exception.BusinessException;

import org.springframework.beans.factory.annotation.Autowired;
/**
 * 
 */
 @Service("userInfoService")
public class UserInfoServiceImpl extends BaseServiceImpl<UserInfo> implements IUserInfoService{

	@Autowired
	UserInfoMapper userInfoMapper;
	
	@Override
	protected IBaseMapper<UserInfo> getBaseMapper() {
		return userInfoMapper;
	}

	/**
	 * 通过用户昵称获取用户信息
	 */
	@Override
	public UserInfo getUserInfoByUserName(String userName) {
		UserInfo userInfo = new UserInfo();
		userInfo.setUserName(userName);
		return getUniqueData(userInfo);
	}
	
	/**
	 * 通过用户手机号码获取用户信息
	 */
	@Override
	public UserInfo getUserInfoByPhone(String userPhone) {
		UserInfo userInfo = new UserInfo();
		userInfo.setUserPhone(userPhone);
		return getUniqueData(userInfo);
	}

	@Override
	public int modifyUserLoginPassword(String userId, String oldPassword, String newPassword) throws BusinessException {
		
		UserInfo userInfo = selectByPk(userId);
		
		if(!userInfo.getLoginPwd().equals(MD5Util.GetMD5Code(UserInfo.PWD_PRE + oldPassword))){
			throw new BusinessException("原密码不正确，请重新输入");
		}
		
		userInfo.setLoginPwd(MD5Util.GetMD5Code(UserInfo.PWD_PRE + newPassword));
		
		return userInfoMapper.modifyUserLoginPassword(userInfo);
	}
}
