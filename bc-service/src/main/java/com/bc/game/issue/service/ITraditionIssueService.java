
package com.bc.game.issue.service;

import com.bc.util.db.IBaseService;
import com.bc.game.issue.domain.TraditionIssue;
import com.bc.game.issue.domain.TraditionIssueStatus;


/**
 * 
 */
public interface ITraditionIssueService extends IBaseService<TraditionIssue> {
	/**
	 * 更新期号状态为已中奖
	 * @param issueNo
	 * @param lotteryType
	 * @param traditionIssueStatus
	 * @return
	 */
	public int updateIssueStatus(String issueNo,String lotteryType,TraditionIssueStatus traditionIssueStatus);
}
