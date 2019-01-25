
package com.bc.order.tradition.service;

import com.bc.util.db.IBaseService;

import java.util.List;
import java.util.Map;

import com.bc.order.tradition.domain.TraditionOrder;


/**
 * 
 */
public interface ITraditionOrderService extends IBaseService<TraditionOrder> {

	/**
	 * 更新快三未中奖订单
	 * @param prizeManner 中奖的玩法
	 * @param issueNo
	 */
	public int updateK3NotPrize(List<String> prizeManner, String issueNo);
	/**
	 * 获取未派奖订单
	 * @param issue
	 * @param lotteryType
	 * @return
	 */
	public List<TraditionOrder> getNotSendPrize(String issue,String lotteryType);
	/**
	 * 更新投注中奖详情
	 * @param traditionOrder 订单
	 * @param prizeDetail 中奖情况
	 * @return
	 */
	public int updateOrderPrize(TraditionOrder traditionOrder,Map<String,String> prizeDetail);
}
