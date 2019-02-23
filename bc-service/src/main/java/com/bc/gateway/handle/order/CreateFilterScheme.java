package com.bc.gateway.handle.order;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.dubbo.common.json.JSON;
import com.bc.filter.scheme.domain.FilterScheme;
import com.bc.filter.scheme.service.IFilterSchemeService;
import com.bc.gateway.impl.AbsCommProtocol;
import com.bc.order.tradition.domain.TraditionOrder;
import com.bc.order.tradition.service.ITraditionOrderService;
import com.bc.util.exception.BusinessException;
@Transactional
@Service("createFilterScheme")
public class CreateFilterScheme extends AbsCommProtocol {

	@Autowired
	IFilterSchemeService filterSchemeService ;
	@Autowired
	ITraditionOrderService traditionOrderService ;
	
	
	@Override
	protected String process(Map<String, Object> paraMap, String serviceCode) throws BusinessException {
		System.out.println("保存过滤方案");
		Map<String, Object> resultMap = new HashMap<>();
		String result = "";
		//判断当前期是否在销售中
		
		String userId = paraMap.get("userId").toString();
		String schemeDetail = paraMap.get("schemeDetail").toString();
		String issueNo = paraMap.get("issueNo").toString();
		String lotteryType = paraMap.get("lotteryType").toString();
		String investMoney = paraMap.get("investMoney").toString();
		String continuousCount = paraMap.get("continuousCount").toString();
		String multiple = paraMap.get("multiple").toString();
		
		String schemeId = UUID.randomUUID().toString();
		
		FilterScheme filterScheme = new FilterScheme();
		filterScheme.setSchemeId(schemeId);
		filterScheme.setUserId(userId);
		filterScheme.setSchemeDetail(schemeDetail);
		filterScheme.setStartIssueNo(issueNo);
		filterScheme.setLotteryType(lotteryType);
		filterScheme.setInvestMoney(new BigDecimal(investMoney));
		filterScheme.setContinuousCount(Integer.parseInt(continuousCount));
		filterScheme.setMultiple(Integer.parseInt(multiple));
		filterScheme.setAwardStop(1);
		filterSchemeService.insert(filterScheme);
		
		String[] schemeDetails = schemeDetail.split("\\@");
		for(String investDetails : schemeDetails){
			TraditionOrder traditionOrder = new TraditionOrder();
			traditionOrder.setOrderId(UUID.randomUUID().toString());
			traditionOrder.setUserId(userId);
			String[] investDetail = investDetails.split("#");
			//玩法#号码#注数
			traditionOrder.setInvestNum(investDetail[1]);
			traditionOrder.setManner(investDetail[0]);
			traditionOrder.setIssueNo(issueNo);
			traditionOrder.setLotteryType(lotteryType);
			traditionOrder.setInvestCount(Integer.parseInt(investDetail[2]));
			traditionOrder.setInvestMoney(new BigDecimal(Integer.parseInt(multiple)*traditionOrder.getInvestCount()*2));
			traditionOrder.setMultiple(Integer.parseInt(multiple));
			if(continuousCount.equals("0")){
				traditionOrder.setInvestManner(0);
			}else{
				traditionOrder.setInvestManner(1);
			}
			traditionOrder.setSchemeId(schemeId);
			traditionOrderService.insert(traditionOrder);
		}
		
		resultMap.put("erorcd", "000000");
		resultMap.put("errmsg", "订单创建成功");

		try {
			result = JSON.json(resultMap).toString();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

}
