package com.qbb.scheduler.service;

import java.util.List;

import org.quartz.SchedulerException;

import com.qbb.scheduler.model.JobModel;
import com.qbb.scheduler.model.JobState;

public interface IScheduleService {

	/**
	 * 开始任务
	 * 
	 * @param job
	 * @throws ClassNotFoundException
	 * @throws SchedulerException
	 */
	void start() throws SchedulerException;
	

	/**
	 * 所有任务重新加载并运行
	 * 
	 * @throws ClassNotFoundException
	 * @throws SchedulerException
	 */
	void reload() throws ClassNotFoundException, SchedulerException;

	/**
	 * 添加任务，如果存在则替换
	 * 
	 * @param job
	 * @throws ClassNotFoundException
	 * @throws SchedulerException
	 */
	void scheduleJob(JobModel job) throws ClassNotFoundException, SchedulerException;

	/**
	 * 删除任务
	 * 
	 * @param name
	 * @param group
	 * @throws SchedulerException
	 */
	void deleteJob(String name, String group) throws SchedulerException;

	/**
	 * 暂停正在运行中的任务
	 * 
	 * @param name
	 * @param group
	 * @throws SchedulerException
	 */
	void pauseJob(String name, String group) throws SchedulerException;
	
	/**
	 * 立即开始任务
	 * 
	 * @param job
	 * @throws ClassNotFoundException
	 * @throws SchedulerException
	 */
	int runnow(String name, String group) throws SchedulerException;

	/**
	 * 暂停所有正在运行中的任务
	 * 
	 * @param name
	 * @param group
	 * @throws SchedulerException
	 */
	void pauseAll() throws SchedulerException;

	/**
	 * 将任务从暂停状态恢复至运行状态
	 * 
	 * @param name
	 * @param group
	 * @throws SchedulerException
	 */
	void resumeJob(String name, String group) throws SchedulerException;

	/**
	 * 恢复所有暂停的任务
	 * 
	 * @throws SchedulerException
	 */
	void resumeAll() throws SchedulerException;

	/**
	 * 关闭任务管理器
	 * 
	 * @throws SchedulerException
	 */
	void shutdown() throws SchedulerException;

	/**
	 * 获取任务基础信息
	 * 
	 * @param id
	 * @return
	 */
	public JobModel getJobInfo(long id);

	/**
	 * 获取所有任务基础信息
	 * 
	 * @return
	 */
	public List<JobModel> getAllJob() throws SchedulerException;

	/**
	 * 保存或修改任务信息
	 * 
	 * @param job
	 * @return
	 */
	public int saveOrUpdate(JobModel job) throws SchedulerException;

	/**
	 * 删除指定任务数据记录
	 * 
	 * @param id
	 * @return
	 */
	public int delete(long id);
	
	/**
	 * 检测任务运行状况
	 * @return
	 */
	public List<JobState> getTaskState(Long id);
	
	int execute(String s);
}
