package com.qbb.jobs.ms.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qbb.jobs.ms.dao.MonitorSystemDao;
import com.qbb.jobs.ms.service.IMonitorSystemService;
import com.qbb.jobs.pub.service.ISyscService;
import com.qbb.scheduler.model.JobModel;
import com.qian88.message.client.MessageHelper;
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

	/**
	 * 每天检查任务
	 */
	@Override
	public void runEveryDay(JobModel jobModel) throws SchedulerException {
		// 获取核保失败的标的
		int hebaoFails = monitorSystemDao.getUnderwritingFailed();

		// 检查当天财务审批未通过的数据
		// 检查当天系统未还款数据
		int NotRepjobcount = monitorSystemDao.getNotRepjobcount();


		List<String> messageList = new ArrayList<String>();
		// 发送短信内容
		// StringBuffer context = new StringBuffer();

		if (hebaoFails > 0) {
			logger.info("检查到有核保失败记录");

			messageList.add("检查到有核保失败记录，请及时处理");

		} else {
			logger.info("核保服务正常.....");
		}


		if (NotRepjobcount > 0) {
			logger.info("检查到当天有未还款记录" + NotRepjobcount);
			// 检查当天系统未还款数据 ：NotRepjobcount
			messageList.add("检查到当天有未还款记录");
		} else {
			logger.info("当天系统还款正常......");
		}

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
	public void sendDayReport(JobModel job) {
		// TODO Auto-generated method stub
		
	}
}