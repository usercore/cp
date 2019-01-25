
package com.bc.game.award.service;


import com.bc.game.award.domain.TraditionAward;
import com.bc.util.db.BaseServiceImpl;
import org.springframework.stereotype.Service;
import com.bc.game.award.dao.TraditionAwardMapper;
import com.bc.util.db.IBaseMapper;
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
	public TraditionAward selectAwardByIssueAndLotteryType(String issueNo,String lotteryType){
		TraditionAward traditionAward = new TraditionAward();
		traditionAward.setIssueNo(issueNo);
		traditionAward.setLotteryType(lotteryType);
		return getUniqueData(traditionAward);
	}
	
}
