
package com.bc.game.award.service;

import com.bc.util.db.IBaseService;
import com.bc.game.award.domain.TraditionAward;


/**
 * 
 */
public interface ITraditionAwardService extends IBaseService<TraditionAward> {
	/**
	 * 根据期号、彩种查询开奖详情
	 * @param traditionAward
	 * @return
	 */
	TraditionAward selectAwardByIssueAndLotteryType(String issueNo,String lotteryType);

}
