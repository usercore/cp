
package com.bc.user.fund.service;


import com.bc.user.fund.domain.UserFund;
import com.bc.util.db.BaseServiceImpl;
import org.springframework.stereotype.Service;
import com.bc.user.fund.dao.UserFundMapper;
import com.bc.util.db.IBaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import com.bc.util.exception.BusinessException;
import java.util.List;
/**
 * 
 */
 @Service("userFundService")
public class UserFundServiceImpl extends BaseServiceImpl<UserFund> implements IUserFundService{

	@Autowired
	UserFundMapper userFundMapper;
	
	@Override
	protected IBaseMapper<UserFund> getBaseMapper() {
		return userFundMapper;
	}
}
