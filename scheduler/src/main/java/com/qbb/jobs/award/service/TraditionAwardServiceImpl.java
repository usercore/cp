
package com.qbb.jobs.award.service;


import com.qbb.jobs.award.domain.TraditionAward;
import com.qbb.util.db.BaseServiceImpl;
import org.springframework.stereotype.Service;
import com.qbb.jobs.award.dao.TraditionAwardMapper;
import com.qbb.util.db.IBaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
/**
 * 
 */
 @Service("traditionAwardService")
public class TraditionAwardServiceImpl extends BaseServiceImpl<TraditionAward> implements ITraditionAwardService{

	@Autowired
	TraditionAwardMapper traditionAwardMapper;
	
	@Override
	protected IBaseMapper<TraditionAward> getBaseMapper() {
		return traditionAwardMapper;
	}

	@Override
	public void getAwardNom(String gameCode) {
		System.out.println("获取开奖号码");
		
	}
}
