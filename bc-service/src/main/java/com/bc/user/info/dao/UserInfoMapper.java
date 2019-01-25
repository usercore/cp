
package com.bc.user.info.dao;


import com.bc.user.info.domain.UserInfo;
import com.bc.util.db.IBaseMapper;

/**
 * 
 */
public interface UserInfoMapper extends IBaseMapper<UserInfo> {

	int modifyUserLoginPassword(UserInfo userInfo);
}
