
package com.bc.game.issue.service;


import com.bc.game.issue.domain.TraditionIssue;
import com.bc.game.issue.domain.TraditionIssueStatus;
import com.bc.util.db.BaseServiceImpl;
import org.springframework.stereotype.Service;
import com.bc.game.issue.dao.TraditionIssueMapper;
import com.bc.util.db.IBaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Date;
/**
 * 
 */
 @Service("traditionIssueService")
public class TraditionIssueServiceImpl extends BaseServiceImpl<TraditionIssue> implements ITraditionIssueService{

	@Autowired
	TraditionIssueMapper traditionIssueMapper;
	
	@Override
	protected IBaseMapper<TraditionIssue> getBaseMapper() {
		return traditionIssueMapper;
	}
	
	public int updateIssueStatus(String issueNo,String lotteryType,TraditionIssueStatus traditionIssueStatus){
		TraditionIssue traditionIssue = new TraditionIssue();
		traditionIssue.setIssueNo(issueNo);
		traditionIssue.setLotteryType(lotteryType);
		traditionIssue.setStatus(traditionIssueStatus.ordinal());
		traditionIssue.setUpdateTime(new Date());
		return traditionIssueMapper.updateIssueStatus(traditionIssue);
	}
}
