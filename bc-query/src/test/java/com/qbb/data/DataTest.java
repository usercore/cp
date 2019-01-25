/*package com.qbb.data;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.common.json.JSON;
import com.qbb.AbstractTestCase;
import com.qbb.borrow.domain.Borrow;
import com.qbb.data.service.IBaseService;
import com.qbb.sql.parase.SQLParser;
import com.qbb.sql.parase.domain.QbbBoundSql;
import com.qbb.util.DateUtil;
import com.qbb.util.exception.QbbBusinessException;

public class DataTest extends AbstractTestCase{
	@Autowired
	IBaseService service;
	
	
	@Test
	public void testSql(){
		
		String sql = " select * from bid_borrow   <trim prefix=\"WHERE\" prefixOverrides=\"AND|OR\"> <if test=\"status!=null and status!=''\">and status = #{status}</if></trim>";
		SQLParser sqlParser = new SQLParser(sql);
		Map<String, Object> parameterObject = new HashMap<String, Object>();
		parameterObject.put("status", "07");
		QbbBoundSql qbbBoundSql  = sqlParser.getBoundSql(parameterObject);
		List<Map<String,Object>> result = service.getObjList(qbbBoundSql.getSql(), qbbBoundSql.getParams());
		System.out.println(result);
	}
	//@Test
	public void testA(){
		Map<String,String> param = new HashMap<String,String>();
		param.put("tableName", "app_push_info");
		param.put("push_type", "1");
		List<Map<String,Object>> result = service.query(param,"type desc ");
		for(Map map : result){
			System.out.println(map);
		}
	}
	//@Test
	public void testB(){
		String sql = "select a.plan_name planName,a.plan_sq planSq ,DATE_FORMAT(b.create_time,'%Y-%m-%d')  createTime,CONCAT(a.min_rate,'-',a.max_rate) rateRange ,"
				+ "sum(join_money)  investMoney,sum(actual_interest)  totalIncome "
				+ "from zn_plan_total a,zn_plan_join b,zn_plan_sign c where b.user_id=? and a.plan_sq = b.plan_sq and a.plan_sq=c.plan_sq group by a.plan_sq";
		Object[] obj = {75};
		List<Map<String,Object>> result = service.getObjList(sql, obj);
		System.out.println(result);
	}
	//@Test
	public void process()  {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String sql = "select bb.borrow_title borrowName,min(bi.create_time) investTime,bb.inteaccr_type,ROUND(bi.investment_money,2) investMoney,brd.borrow_sq,min(repayment_time) lastRepTime "
				+ " from bid_repayment_detail brd   LEFT JOIN bid_borrow bb on brd.borrow_sq=bb.borrow_sq  LEFT JOIN bid_investment bi on brd.investment_sq=bi.investment_sq "
				+ "WHERE bb.is_plan =1 AND bb.credit_enable='N' AND brd.`status`='15' AND brd.repayment_period>1 AND brd.investor_id=? and (brd.zq_status='O' or brd.zq_status='N')"
				+ "and brd.borrow_sq in (select borrow_sq from zn_plan_branch WHERE plan_sq = ?)      GROUP BY brd.borrow_sq,brd.investment_sq  ";
		Object[] obj = { "201500", "2017011600000006" };
		List<Map<String, Object>> resultList = service.getObjList(sql, obj);
		
		BigDecimal canQuitMoney = new BigDecimal(0);
		for (int i = 0; i < resultList.size(); i++) {
			
			Map<String, Object> map = resultList.get(i);
			String createTime = map.get("investTime").toString();
			int holdingDay = DateUtil.getTimeBetween("yyyy-MM-dd HH:mm:ss", createTime, sdf.format(new Date()));
			map.put("holdingTime", holdingDay + "天");
			map.put("inteaccrName", Borrow.inteaccrTypeMap.get(map.get("inteaccr_type").toString()));

			if (holdingDay > 30) {
				canQuitMoney = canQuitMoney.add(new BigDecimal(map.get("investMoney").toString()));
			}
		}
		
	}

}
*/