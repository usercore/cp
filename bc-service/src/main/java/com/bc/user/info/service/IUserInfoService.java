
package com.bc.user.info.service;

import com.bc.user.info.domain.UserInfo;
import com.bc.util.db.IBaseService;
import com.bc.util.exception.BusinessException;


/**
 * 
 */
public interface IUserInfoService extends IBaseService<UserInfo> {

	/**
	 * 通过用户昵称获取用户信息
	 * @param userName
	 * @return
	 */
	public UserInfo getUserInfoByUserName(String userName);
	/**
	 * 通过用户手机号获取用户信息
	 * @param userName
	 * @return
	 */
	public UserInfo getUserInfoByPhone(String userPhone);
	/**
	 * 修改用户密码
	 * @param userId
	 * @param newPassWord
	 * @return
	 */
	public int modifyUserLoginPassword(String userId,String oldPassword,String newPassword)throws BusinessException;
}
