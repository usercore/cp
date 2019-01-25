
package com.bc.order.tradition.service;


import com.bc.order.tradition.domain.TraditionOrder;
import com.bc.util.db.BaseServiceImpl;
import org.springframework.stereotype.Service;
import com.bc.order.tradition.dao.TraditionOrderMapper;
import com.bc.util.db.IBaseMapper;

import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
/**
 * 
 */
 @Service("traditionOrderService")
public class TraditionOrderServiceImpl extends BaseServiceImpl<TraditionOrder> implements ITraditionOrderService{

	@Autowired
	TraditionOrderMapper traditionOrderMapper;
	
	@Override
	protected IBaseMapper<TraditionOrder> getBaseMapper() {
		return traditionOrderMapper;
	}
	/**
	 * 更新快三未中奖订单
	 */
	@Override
	public int updateK3NotPrize(List<String> prizeManner, String issueNo){
		return traditionOrderMapper.updateK3NotPrize(prizeManner, issueNo);
	}
	/**
	 * 查询未算奖订单
	 */
	@Override
	public List<TraditionOrder> getNotSendPrize(String issue, String lotteryType) {
		TraditionOrder traditionOrder = new TraditionOrder();
		traditionOrder.setIssueNo(issue);
		traditionOrder.setLotteryType(lotteryType);
		traditionOrder.setAwardStatus(TraditionOrder.NOT_CALCULATE);
		return traditionOrderMapper.selectByExample(traditionOrder);
	}
	
	/**
	 * 更新中奖订单
	 */
	@Override
	public int updateOrderPrize(TraditionOrder traditionOrder,Map<String,String> prizeDetail) {
		Double prize = Double.parseDouble(prizeDetail.get("prize"));

		if(prize == 0){
			traditionOrder.setAwardStatus(TraditionOrder.NOT_PRIZE);
		}else{
			traditionOrder.setAwardMoney(BigDecimal.valueOf(prize * traditionOrder.getMultiple()));
			traditionOrder.setAwardStatus(TraditionOrder.SEND_PRIZE);//中奖
			traditionOrder.setAwardDetail(prizeDetail.get("level") + "/" + prizeDetail.get("prizeCount") + "/" + prize);
			
		}
		return traditionOrderMapper.updateByPk(traditionOrder);
	}
	
}
