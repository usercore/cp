package com.bc.gateway.handle.award;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.alibaba.dubbo.common.json.JSON;
import com.bc.game.award.K3CalculateAward;
import com.bc.game.issue.domain.TraditionIssueStatus;
import com.bc.game.issue.service.ITraditionIssueService;
import com.bc.gateway.impl.AbsCommProtocol;
import com.bc.order.tradition.domain.TraditionOrder;
import com.bc.order.tradition.service.ITraditionOrderService;
import com.bc.util.exception.BusinessException;
/**
 * 算奖
 * @author pc
 *
 */
@Service("calculateAward")
@Transactional
public class CalculateAward extends AbsCommProtocol {

	@Autowired
	K3CalculateAward k3CalculateAward;
	
	@Autowired
	ITraditionOrderService traditionOrderService;
	@Autowired
	ITraditionIssueService traditionIssueService;
	
	@Override
	protected String process(Map<String, Object> paraMap, String serviceCode) throws BusinessException {
		
		Map<String, Object> resultMap = new HashMap<>();
		String result = "";
       
		String issueNo = paraMap.get("issueNo").toString();
		String lotteryType = paraMap.get("lotteryType").toString();
		
		
		log.info("快三" + issueNo + "期算奖");
		
		List<String> mannerList = k3CalculateAward.calculateAwardManner(issueNo);
		
		//更新未中奖订单
		traditionOrderService.updateK3NotPrize(mannerList, issueNo);
		
//		玩法 11:和值 12:三同号通选 13:三同号单选 14:二同号复选 15:二同号单选 16：三不同号 17：二不同号 18：三连号通选
		
		//查询未算奖订单，遍历开始
		List<TraditionOrder> traditionOrderList = traditionOrderService.getNotSendPrize(issueNo, lotteryType);
		
		while(traditionOrderList.size() != 0){
			for (TraditionOrder traditionOrder : traditionOrderList) {
				//计算奖金
				Map<String,String> prizeDetail = k3CalculateAward.calculatePrize(traditionOrder.getInvestNum(), traditionOrder.getManner(), issueNo);
				
				//更新订单奖金
				traditionOrderService.updateOrderPrize(traditionOrder,prizeDetail);
			}
			traditionOrderList = traditionOrderService.getNotSendPrize(issueNo, lotteryType);//查询未算奖订单
		}
		
		//遍历结束，更新方案中奖详情---------
		
		//更新期号为已算奖
		traditionIssueService.updateIssueStatus(issueNo, lotteryType, TraditionIssueStatus.ALREADY_SEND_PRIZE);
		
		resultMap.put("erorcd", "000000");
	    resultMap.put("errmsg", "算奖成功");
		try {
			result = JSON.json(resultMap).toString();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

}
