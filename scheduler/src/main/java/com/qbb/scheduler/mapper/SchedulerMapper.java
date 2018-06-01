package com.qbb.scheduler.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.qbb.scheduler.model.JobModel;
import com.qbb.scheduler.model.JobState;

public interface SchedulerMapper {

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
	public List<JobModel> getAllJob(String schedName);

	public int addJob(JobModel job);

	public int updateJob(JobModel job);

	public int deleteJob(long id);

	/**
	 * 检测任务运行状况
	 * 
	 * @return
	 */
	public List<JobState> getTaskState(@Param("id") Long id);

}
