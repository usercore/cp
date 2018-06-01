package com.qbb.jobs.ms.service;

import org.quartz.SchedulerException;

import com.qbb.jdbc.transaction.annotation.ReadOnlyDataSource;
import com.qbb.scheduler.model.JobModel;

@ReadOnlyDataSource
public interface IMonitorSystemService {

	// 每天检查任务

	void runEveryDay(JobModel jobModel) throws SchedulerException;

	// 每时每刻检查任务
	void runEveryMoment(JobModel jobModel) throws SchedulerException;

	// 每分钟检查任务
	void runEveryMinute(JobModel jobModel) throws SchedulerException;

	/**
	 * MO9自动投标成功短信每天16:00执行一次 发送
	 * 
	 * @param jobModel
	 * @throws SchedulerException
	 */
	void runMo9AotoInvestSuccess(JobModel jobModel) throws SchedulerException;

	/**
	 * MO9回款提醒短信 11.30执行
	 * 
	 * @param jobModel
	 * @throws SchedulerException
	 */
	void runMo9Repay1130Msg(JobModel jobModel) throws SchedulerException;

	/**
	 * MO9回款提醒短信 17.30执行
	 * 
	 * @param jobModel
	 * @throws SchedulerException
	 */
	void runMo9Repay1700Msg(JobModel jobModel) throws SchedulerException;

	/**
	 * 月月赢回款提醒短信 17.00执行
	 * 
	 * @param jobModel
	 * @throws SchedulerException
	 */
	void runMonthWinRepay1700Msg(JobModel jobModel) throws SchedulerException;

	/**
	 * 星期5查询周末核保且需要还款的标的数据
	 */
	void runReekendCoreBorrowAndRepayment();

	/**
	 * 发送每日报告
	 */
	void sendDayReport(JobModel job);
}
