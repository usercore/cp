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
	 * 发送每日报告
	 */
	void sendDayReport(JobModel job);
}
