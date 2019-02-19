
package com.qbb.jobs.tradition.service.impl;


import com.qbb.capture.model.AwardInfo;
import com.qbb.capture.tradition.D3Result;
import com.qbb.capture.tradition.SsqResult;
import com.qbb.jobs.tradition.dao.TraditionAwardDao;
import com.qbb.jobs.tradition.service.ITraditionAwardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 
 */
 @Service("traditionAwardService")
public class TraditionAwardServiceImpl implements ITraditionAwardService {

	@Autowired
	TraditionAwardDao traditionAwardDao;

	@Override
	public void getAwardNom(String gameCode) {
		System.out.println("获取开奖号码");
		
		
		AwardInfo awardInfo = null;
		String issueNo = "18063";
		switch(gameCode){
			case "201":
				awardInfo = SsqResult.getAwardInfo(issueNo);
				break;
			case "202":
				awardInfo = D3Result.getAwardInfo(issueNo);
				break;
			default:
					break;
		}
		
	}
}
