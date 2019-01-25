/*package com.qbb.data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.qbb.AbstractTestCase;
import com.qbb.data.service.IBaseService;
import com.qbb.sql.parase.SQLParser;
import com.qbb.sql.parase.domain.QbbBoundSql;
import com.qbb.sys.query.ISysQueryService;

public class SqlParserTest extends AbstractTestCase {
	@Autowired
	IBaseService service;
	@Autowired
	ISysQueryService sysQueryService;

	//@Test
	public void testQuery() {
		Map<String, Object> parameterObject = new HashMap<String, Object>();
		parameterObject.put("userId", "205256");
		List<Map<String, Object>> commonSqlList = sysQueryService.listSysQueryByActina("initDrawMoney");
		System.out.println("query------" + commonSqlList.size());

		for (Map<String, Object> map : commonSqlList) {
			SQLParser sqlParser = new SQLParser(map.get("query_sql").toString());
			System.out.println(map.get("query_sql"));
			QbbBoundSql qbbBoundSql = sqlParser.getBoundSql(parameterObject);
			System.out.println(qbbBoundSql.getSql());
			List<Map<String, Object>> resultList = service.getObjList(qbbBoundSql.getSql(), qbbBoundSql.getParams());
			System.out.println(resultList);

		}
	}

	// @Test
	public void testSql() {

		String sql = " select c.bank_path bankImg,c.bank_name bankName,right(a.card_acct,4) cardNo,b.pa_user_usable+b.pa_user_way totalMoney,b.pa_user_usable canDrawMoney from cnp_bank_card a "
				+ "LEFT JOIN cnp_user_fund b  on a.user_id=b.user_id " + "LEFT JOIN cnp_bank_list c on a.bank_sq=c.bank_sq where a.user_id=#{userId} and is_pahost=2";

		sql = "SELECT  bid_recharge.recharge_money,  bid_recharge.recharge_actualmoney,  bid_recharge.create_time,  bid_recharge.return_time, "
				+ "bid_recharge.recharge_no,  if(bid_recharge.status='05','成功','失败') status, bid_recharge.channel,   0 as server_charge, "
				+ " cnp_user.user_name,  IF (bid_recharge.recharge_type = '1','连连支付',IF (	bid_recharge.recharge_type = '2','京东支付', "
				+ " IF (bid_recharge.recharge_type = '3','贝付支付',IF (	bid_recharge.recharge_type = '4','丰付支付', "
				+ "   IF (bid_recharge.recharge_type = '5','人工充值',IF ( bid_recharge.recharge_type = '6','宝付支付', " + " IF (bid_recharge.recharge_type = '11','先锋支付', "
				+ "  IF (bid_recharge.recharge_type = '7','线下充值',IF (	bid_recharge.recharge_type = '8','其他1', " + " IF (bid_recharge.recharge_type = '9','其他2','其他')))))))))) AS recharge_channel "
				+ " from bid_recharge " + "   LEFT JOIN cnp_user on cnp_user.user_id=bid_recharge.user_id " + " where bid_recharge.user_id = #{userId} and cnp_user.user_id=#{userId}"
				+ " and is_hosting = '2'  order by create_time desc";
		SQLParser sqlParser = new SQLParser(sql);
		Map<String, Object> parameterObject = new HashMap<String, Object>();
		parameterObject.put("userId", "205256");

		QbbBoundSql qbbBoundSql = sqlParser.getBoundSql(parameterObject);
		List<Map<String, Object>> result = service.getObjList(qbbBoundSql.getSql(), qbbBoundSql.getParams());
		System.out.println(result);
		System.out.println("---------" + qbbBoundSql.getSql());
		System.out.println("**********" + qbbBoundSql.getParams());
	}

}
*/