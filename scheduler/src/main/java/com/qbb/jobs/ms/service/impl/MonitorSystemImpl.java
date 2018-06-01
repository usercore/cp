package com.qbb.jobs.ms.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qbb.jobs.ms.dao.MonitorSystemDao;
import com.qbb.jobs.ms.service.IMonitorSystemService;
import com.qbb.jobs.pub.service.ISyscService;
import com.qbb.scheduler.model.JobModel;
import com.qbb.util.client.DateUtil;
import com.qian88.message.client.MessageHelper;
import com.qian88.message.client.exception.MessageException;
import com.qian88.message.client.model.EmailEntity;
import com.qian88.message.client.model.SmsEntity;

@Service
public class MonitorSystemImpl implements IMonitorSystemService {

	Logger logger = Logger.getLogger(MonitorSystemImpl.class);

	@Autowired
	private MonitorSystemDao monitorSystemDao;
	@Autowired
	private ISyscService iSyscService;

	public void setMonitorSystemDao(MonitorSystemDao monitorSystemDao) {
		this.monitorSystemDao = monitorSystemDao;
	}

	// 控制每分钟发送任务发短信频率
	// private static long time = -1;

	// 控制每十五分钟发送任务发短信频率
	// private static long time_Moment = -1;

	@Override
	public void runMo9AotoInvestSuccess(JobModel jobModel) throws SchedulerException {
		logger.info("进入自动投标成功提醒短信发送任务~~~~~~~~~~");
		List<Map<String, Object>> list = monitorSystemDao.getAotoInvestSuccessList();
		System.out.println(list.toString());
		if (list != null && list.size() > 0) {
			logger.info("开始发送自动投标成功提醒短信，本次发送用户数：" + list.size() + ",当前时间：" + DateUtil.dateToString(new Date()));
			int i = 0;
			DateTime nowDate = new DateTime();
			String time = nowDate.getYear() + "年" + nowDate.getMonthOfYear() + "月" + nowDate.getDayOfMonth() + "日";
			for (Map<String, Object> map : list) {
				String user_phone = map.get("user_phone").toString();
				if (user_phone.length() == 11 && !monitorSystemDao.queryIsBlackUser(Long.valueOf(map.get("investor_id").toString()), "2")) {
					StringBuffer sb = new StringBuffer();
					sb.append("尊敬的用户：您好！您开启的自动投标功能于").append(time);
					sb.append("累计帮您投资成交金额为").append(map.get("total_aoto").toString());
					sb.append("元，其中已核保金额为").append(map.get("total_core").toString());
					sb.append("元，已核保标的产生的利息为").append(map.get("total_interest").toString());
					sb.append("元。详细内容请登录账户查看，开启自动投标，轻松理财。回Ｎ退订");
					iSyscService.sendMessage(user_phone, sb.toString());
					i++;
				} else {
					logger.info("发送MO9自动投标短信失败，用户手机号长度异常或为黑名单用户！手机号为：" + user_phone);
				}
			}
			logger.info("开始发送MO9自动投标成功提醒短信，本次发送用户数：" + i + ",当前时间：" + DateUtil.dateToString(new Date()));
		}
	}

	@Override
	public void runMo9Repay1130Msg(JobModel jobModel) throws SchedulerException {
		sendMo9repay();
	}

	@Override
	public void runMo9Repay1700Msg(JobModel jobModel) throws SchedulerException {
		sendMo9repay();
	}

	public void sendMo9repay() {
		logger.info("进入周周赢回款提醒短信发送任务~~~~~~~~~~");
		List<Map<String, Object>> repaylist = monitorSystemDao.runMo9RepayMsg();
		if (repaylist != null && repaylist.size() > 0) {
			logger.info("开始发送周周赢回款提醒短信，本次发送用户数：" + repaylist.size() + ",当前时间：" + DateUtil.dateToString(new Date()));
			int i = 0;
			for (Map<String, Object> map : repaylist) {
				String user_phone = map.get("user_phone").toString();
				if (user_phone.length() == 11) {
					StringBuffer sb = new StringBuffer();
					sb.append("亲爱的用户，您投资的周周赢项目回款啦，回款收益是");
					sb.append(map.get("total_interest").toString());
					sb.append("元，本金是");
					sb.append(map.get("total_repay_detail").toString());
					sb.append("元，当前您账户总额是");
					sb.append(map.get("user_total").toString());
					sb.append("元。详情请登录账户查询。如有疑问请致电客服：4007888088");
					iSyscService.sendMessage(map.get("user_phone").toString(), sb.toString());
					if (null != map.get("user_email")) {
						iSyscService.addMessage("1", map.get("user_email").toString(), sb.toString(), "0", "0", "6");
					}
					i++;
				} else {
					logger.info("发送周周赢回款短信失败，用户手机号长度异常！手机号为：" + user_phone);
				}
			}
			logger.info("结束发送周周赢回款提醒短信，本次发送用户数：" + i + ",当前时间：" + DateUtil.dateToString(new Date()));
		}

	}

	/**
	 * 每天检查任务
	 */
	@Override
	public void runEveryDay(JobModel jobModel) throws SchedulerException {
		// 获取核保失败的标的
		int hebaoFails = monitorSystemDao.getUnderwritingFailed();

		// 检查当天财务审批未通过的数据
		// int NotRepcount = monitorSystemDao.getNotRepcount();
		// 检查当天系统未还款数据
		int NotRepjobcount = monitorSystemDao.getNotRepjobcount();

		// 检查还款记录与资金流水流水匹配数据，还款后未给投资人款项的
		// BigDecimal FundMoney1 = monitorSystemDao.getNotFundMoney1();
		//
		// BigDecimal FundMoney2 = monitorSystemDao.getNotFundMoney2();
		//
		// BigDecimal FundMoney3 = monitorSystemDao.getNotFundMoney3();
		//
		// // 正常还款资金匹配检查
		// BigDecimal FundBackMoney1 = monitorSystemDao.getNotFundBackMoney1();
		//
		// BigDecimal FundBackMoney2 = monitorSystemDao.getNotFundBackMoney2();
		//
		// BigDecimal FundBackMoney3 = monitorSystemDao.getNotFundBackMoney3();
		//
		// BigDecimal FundBackMoney4 = monitorSystemDao.getNotFundBackMoney4();

		List<String> messageList = new ArrayList<String>();
		// 发送短信内容
		// StringBuffer context = new StringBuffer();

		if (hebaoFails > 0) {
			logger.info("检查到有核保失败记录");

			messageList.add("检查到有核保失败记录，请及时处理");

		} else {
			logger.info("核保服务正常.....");
		}

		// if (NotRepcount > 0) {
		// logger.info("当天财务审批异常......" + NotRepcount);
		// // 当天财务审批未通过的数据数量：NotRepcount
		// messageList.add("财务审批异常");
		// } else {
		// logger.info("当天财务审批正常......");
		// }

		if (NotRepjobcount > 0) {
			logger.info("检查到当天有未还款记录" + NotRepjobcount);
			// 检查当天系统未还款数据 ：NotRepjobcount
			messageList.add("检查到当天有未还款记录");
		} else {
			logger.info("当天系统还款正常......");
		}

		// // 短信未发送监控
		// // 检查还款异常
		// int getSysSendmessAge = monitorSystemDao.getNotJobrepcountFalse();
		//
		// if (getSysSendmessAge > 0) {
		// // 用户微信支付失败
		// logger.info(" 有超过90分钟 未发送的短信记录，请检查sys_send_message表");
		// messageList.add("有超过90分钟 未发送的短信记录，请检查sys_send_message表");
		// } else {
		// logger.info("短信未发送异常情况-正常......");
		// }

		//
		// BigDecimal FundMoney =
		// FundMoney1.add(FundMoney2).subtract(FundMoney3);
		//
		// if (FundMoney.compareTo(new BigDecimal("0")) != 0) {
		// // 还款记录与资金流水流水匹配数据异常
		// logger.info("当天系统还款资金异常......" + FundMoney);
		// messageList.add("系统还款资金异常");
		// } else {
		// logger.info("当天还款资金正常......");
		// }
		//
		// logger.info("FundBackMoney1:" + FundBackMoney1);

		// BigDecimal FundBackMoney = FundBackMoney1.subtract(FundBackMoney2);

		// if (FundBackMoney.compareTo(new BigDecimal("0")) != 0) {
		//
		// // 还款表与资金流水表资金不匹配
		// logger.info("当天正常还款（非提前还款）异常......FundBackMoney1:" + FundBackMoney1 +
		// "+FundBackMoney2:" + FundBackMoney2 + "=FundBackMoney"
		// + FundBackMoney);
		//
		// messageList.add("正常还款（非提前还款）异常");
		// } else {
		// logger.info("当天正常还款（非提前还款）正常......");
		// }

		// BigDecimal FundBackMoney0 = FundBackMoney3.subtract(FundBackMoney4);
		//
		// if (FundBackMoney0.compareTo(new BigDecimal("0")) != 0) {
		//
		// // 还款历史表与资金历史表 资金不匹配
		// logger.info("当天当天历史表正常还款（非提前还款）异常......" + FundBackMoney0);
		// messageList.add("当天历史表正常还款（非提前还款）异常");
		// } else {
		// logger.info("当天当天历史表正常还款（非提前还款）正常......");
		// }
		if (!messageList.isEmpty()) {
			StringBuffer message = new StringBuffer("异常信息如下:\r\n");
			for (int i = 0; i < messageList.size(); i++) {
				message.append(i + 1).append("、").append(messageList.get(i)).append("\r\n");
			}

			try {
				SmsEntity entity = new SmsEntity("999", jobModel.getPhone(), message.toString());
				MessageHelper.OTHER().sendSMS(entity, true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * 实时监控任务
	 */
	@Override
	public void runEveryMoment(JobModel jobModel) throws SchedulerException {
		List<String> messageList = new ArrayList<String>();

		// 检查用户注册后缺少资金记录
		int notCnpUserFund = monitorSystemDao.getNotCnpUserFund();
		int isGetSchedulerStop = monitorSystemDao.getSchedulerStop();
		System.out.println("~~~~~~~~~~~~~~~~~");
		if (isGetSchedulerStop > 0) {
			// 有停止的服务，请检查-异常
			logger.info("有停止的服务，请检查-异常......" + isGetSchedulerStop);
			messageList.add("有停止的服务");
		} else {
			logger.info(" 服务正常运行-正常......");
		}

		if (notCnpUserFund > 0) {
			// 用户注册后缺少资金记录
			logger.info("用户注册后缺少资金记录-异常......" + notCnpUserFund);
			messageList.add("用户注册后缺少资金记录");
		} else {
			logger.info("无用户注册后缺少资金记录-正常......");
		}

		int hebaoFails = monitorSystemDao.getPaUnderwritingFailed();
		if (hebaoFails > 0) {
			logger.info("检查到有平安放款失败记录");
			messageList.add("检查到存管标的放款失败记录，请及时处理");
		} else {
			logger.info("平安放款服务正常.....");
		}

		hebaoFails = monitorSystemDao.getUnderwritingFailed();
		if (hebaoFails > 0) {
			logger.info("检查到有核保失败记录");

			messageList.add("检查到有核保失败记录，请及时处理");

		} else {
			logger.info("核保服务正常.....");
		}
		// 获取超过40分钟未核保的任务
		int notCore = monitorSystemDao.getUnderwritingNoStart();
		if (notCore > 0) {
			logger.info("检查到有未核保记录");
			messageList.add("检查到有未核保标的，请检查服务是否运行正常");
		} else {
			logger.info("核保服务正常......");
		}

		// 检查电商体验币账户是否充足开始
		BigDecimal sumExpMoney = monitorSystemDao.getSumExpMoney();

		BigDecimal orgUsable = monitorSystemDao.getOrgUsable();

		if (orgUsable.compareTo(sumExpMoney) < 0) {
			// 电商体验币账户余额不足
			logger.info("电商体验币账户余额不足-异常......电商余额：" + orgUsable);
			messageList.add("电商体验币账户余额不足~");
		} else {
			logger.info("电商体验币账户余额充足-正常......");
		}

		// 判断每天的注册短信数量是否存在被机刷
		int msgCount = monitorSystemDao.getMsgCount();

		if (msgCount > 2000) {
			// 判断每天的注册短信数量是否存在被机刷
			logger.info("每天的注册短信数量已超过2000-异常......" + msgCount);
			messageList.add("当天注册短信数量已超过2000");
		} else {
			logger.info(" 当天的注册短信数量不存在被机刷-正常......");
		}

		// 有用户账户可用金额为负数
		int userUsableMinus = monitorSystemDao.getUserUsableMinus();

		if (userUsableMinus > 0) {
			// 用户账户可用金额为负数 ,用户数量 userUsableMinus
			logger.info("用户账户可用金额为负数 ,用户数量-异常......" + userUsableMinus);
			messageList.add("有用户可用金额为负数~");
		} else {
			logger.info("无用户账户可用金额为负数-正常......");
		}

		// 有移动端充值成功后添加的银行卡信息为空
		int notRepyayCount = monitorSystemDao.getNotRepyayCount();
		if (notRepyayCount > 0) {
			// 有移动端充值成功后添加的银行卡信息为空
			logger.info("有移动端充值成功后添加的银行卡信息为空-异常......数量：" + notRepyayCount);
			messageList.add("有移动端充值成功后添加的银行卡信息为空~");
		} else {
			logger.info("无移动端充值成功后添加的银行卡信息为空-正常......");
		}

		// 用户资金总额不等于可用+冻结+待收本金+待收利息
		int wrongTotalCapital = monitorSystemDao.getWrongTotalCapital();
		if (wrongTotalCapital > 0) {
			// 用户资金总额不等于可用+冻结+待收本金+待收利息
			logger.info("用户资金总额不等于可用+冻结+待收本金+待收利息-异常......数量：" + wrongTotalCapital);
			messageList.add("用户资金总额计算异常~");
		} else {
			logger.info("所有用户资金总额等于可用+冻结+待收本金+待收利息-正常......");
		}

		// 检查平安还款异常
		int getNotJobrepcountFalse = monitorSystemDao.getPaRepaymentFail();
		if (getNotJobrepcountFalse > 0) {
			logger.info("检查到有平安还款失败记录，请及时处理");
			messageList.add("检查到存管标的还款失败记录，请及时处理");
		} else {
			logger.info("无平安还款异常情况-正常......");
		}
		// 检查还款异常
		getNotJobrepcountFalse = monitorSystemDao.getNotJobrepcountFalse();

		if (getNotJobrepcountFalse > 0) {
			// 用户微信支付失败
			logger.info("检查到有还款失败记录，请及时处理");
			messageList.add("检查到有还款失败记录，请及时处理");
		} else {
			logger.info("无还款异常情况-正常......");
		}

		int rs = monitorSystemDao.getPaBatchFail();
		if (rs > 0) {
			logger.info("检查到有平安批量处理失败记录，请及时处理");
			messageList.add("检查到有平安批量处理失败记录，请及时处理");
		}

		if (!messageList.isEmpty()) {
			StringBuffer message = new StringBuffer("异常信息如下:");
			for (int i = 0; i < messageList.size(); i++) {
				message.append(i + 1).append("、").append(messageList.get(i)).append("\r\n");
			}

			try {
				SmsEntity entity = new SmsEntity("999", jobModel.getPhone(), message.toString());
				MessageHelper.OTHER().sendSMS(entity, true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 每分钟发送任务
	 */
	@Override
	public void runEveryMinute(JobModel jobModel) throws SchedulerException {
		List<String> messageList = new ArrayList<String>();

		if (!messageList.isEmpty()) {
			StringBuffer message = new StringBuffer("异常信息如下: ");
			for (int i = 0; i < messageList.size(); i++) {
				message.append(i + 1).append("、").append(messageList.get(i)).append("\r\n");
			}

			try {
				SmsEntity entity = new SmsEntity("999", jobModel.getPhone(), message.toString());
				MessageHelper.OTHER().sendSMS(entity);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void runMonthWinRepay1700Msg(JobModel jobModel) throws SchedulerException {
		logger.info("进入月月赢回款提醒短信发送任务~~~~~~~~~~");
		List<Map<String, Object>> repaylist = monitorSystemDao.runMonthRepayMsg();
		if (repaylist != null && repaylist.size() > 0) {
			// System.out.println("Mo9repaylist" + repaylist);
			logger.info("开始发送月月赢回款提醒短信，本次发送用户数：" + repaylist.size() + ",当前时间：" + DateUtil.dateToString(new Date()));
			int i = 0;
			for (Map<String, Object> map : repaylist) {
				String user_phone = map.get("user_phone").toString();
				if (user_phone.length() == 11) {
					StringBuffer sb = new StringBuffer();
					sb.append("亲爱的用户，您投资的月月赢项目回款啦，回款收益是");
					sb.append(map.get("total_interest").toString());
					sb.append("元，本金是");
					sb.append(map.get("total_repay_detail").toString());
					sb.append("元，当前您账户总额是");
					sb.append(map.get("user_total").toString());
					sb.append("元。详情请登录账户查询。如有疑问请致电客服：4007888088");
					iSyscService.sendMessage(map.get("user_phone").toString(), sb.toString());
					if (map.get("user_email") != null) {
						iSyscService.addMessage("1", map.get("user_email").toString(), sb.toString(), "0", "0", "6");
					}
					i++;
				} else {
					logger.info("发送月月赢回款短信失败，用户手机号长度异常！手机号为：" + user_phone);
				}
			}
			logger.info("结束发送月月赢回款提醒短信，本次发送用户数：" + i + ",当前时间：" + DateUtil.dateToString(new Date()));
		}

	}

	/**
	 * 星期5查询周末核保且需要还款的标的数据
	 */
	@Override
	public void runReekendCoreBorrowAndRepayment() {
		// TODO Auto-generated method stub
		logger.info("每周5下午3点开始检查当前未核保，但在周末需要还款的数据:");

		List<Map<String, Object>> mapList = monitorSystemDao.queryReekendCoreBorrowAndRepayment();

		logger.info("查到符合规则的数据为:" + mapList.size());
		List<SmsEntity> list = new ArrayList<>();

		logger.info("循环发送短信");
		for (int i = 0; i < mapList.size(); i++) {
			Map<String, Object> map = mapList.get(i);
			StringBuffer strtext = new StringBuffer("");
			strtext.append("供应商名称：");
			strtext.append(map.get("com_name").toString());
			strtext.append("供应商代码：");
			strtext.append(map.get("com_code").toString());
			strtext.append("发标时间：");
			strtext.append(map.get("bidding_time").toString());
			strtext.append("满标进度：");
			strtext.append(map.get("jd").toString());
			strtext.append("借款人姓名：");
			strtext.append(map.get("vip_name").toString());
			strtext.append("用户名：");
			strtext.append(map.get("borrow_username").toString());
			strtext.append("借款编号：");
			strtext.append(map.get("borrow_title").toString());
			strtext.append("借款期限：");
			strtext.append(map.get("limit_name").toString());
			strtext.append("第一期还款时间：");
			strtext.append(map.get("back_date_first").toString());

			// 普通短信
			SmsEntity sms2 = new SmsEntity("999", "18718527246", strtext.toString());

			list.add(sms2);

		}

		// 立即发送短信
		try {
			if (list.size() == 0) {
				logger.info("没有发送提醒数据");
			} else {
				MessageHelper.WEB().sendSMS(list);
			}
		} catch (MessageException e) {
			// TODO Auto-generated catch block
			logger.info("发送失败");
			e.printStackTrace();
		}
	}

	@Override
	public void sendDayReport(JobModel job) {
		if (job == null || StringUtils.isEmpty(job.getPhone()) || StringUtils.isEmpty(job.getPersonLiable())) {
			return;
		}

		String html = "<html>" + "  <head>" + "	<style type=\"text/css\">" + "		* {font-family: \"微软雅黑\";font-size:14px;}" + "	</style>"
				+ "  </head>" + "  <body>" + "  	<div>" + "  	#person#：" + "  	</div>" + "  	<br/>" + "  	<div>日期：#date#</div>"
				+ "  	<br/>" + "  	<div>未核保数据：</div>" + "  	#data1#" + "   	<br/>" + "   	<div>未还款数据：</div>" + "   	#data2#" + "  </body>"
				+ "</html>";

		List<Map<String, Object>> data = monitorSystemDao.getNoUnderwritingData();
		String[] fields = { "borrow_sq", "borrow_title", "full_time", "is_hosting" };
		StringBuffer data1 = new StringBuffer();
		if (data != null && data.size() > 1) {
			data1.append("<table border=\"1\" style=\"border-spacing: 0;border-collapse: collapse;\" cellpadding=\"3\">");
			for (Map<String, Object> map : data) {
				data1.append("<tr>");
				for (int i = 0; i < fields.length; i++) {
					data1.append("<td style=\"width:180px;\">").append(map.get(fields[i])).append("</td>");
				}
				data1.append("</tr>");
			}
			data1.append("</table>");
		} else {
			data1.append("无");
		}

		data = monitorSystemDao.getNoRepaymentData();
		fields = new String[] { "borrow_sq", "borrow_title", "repayment_time", "repayment_period" };
		StringBuffer data2 = new StringBuffer();
		if (data != null && data.size() > 1) {
			data2.append("<table border=\"1\" style=\"border-spacing: 0;border-collapse: collapse;\" cellpadding=\"3\"><tr>");
			for (Map<String, Object> map : data) {
				data2.append("<tr>");
				for (int i = 0; i < fields.length; i++) {
					data2.append("<td style=\"width:180px;\">").append(map.get(fields[i])).append("</td>");
				}
				data2.append("</tr>");
			}
			data2.append("</table>");
		} else {
			data2.append("无");
		}
		String date = DateUtil.dateFormat(new Date(), "yyyy-MM-dd");

		html = html.replace("#person#", job.getPersonLiable());
		html = html.replace("#date#", date);
		html = html.replace("#data1#", data1);
		html = html.replace("#data2#", data2);

		EmailEntity entity = new EmailEntity("999", job.getPhone(), date + "核保还款情况", html);
		try {
			MessageHelper.OTHER().sendEmail(entity, true);
		} catch (MessageException e) {
			e.printStackTrace();
		}

		if (StringUtils.isNotEmpty(job.getRemark())) {
			entity = new EmailEntity("999", job.getRemark(), date + "核保还款情况", html);
			try {
				MessageHelper.OTHER().sendEmail(entity, true);
			} catch (MessageException e) {
				e.printStackTrace();
			}
		}

	}

}