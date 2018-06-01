package com.qbb.scheduler;

import org.quartz.Job;

import com.qbb.scheduler.model.JobModel;

/**
 * 允许并发执行多个相同定义的JobDetail
 */
public abstract class AbstractJob implements Job {

	private JobModel job;

	public JobModel getJobInfo() {
		return job;
	}

	protected void setJobInfo(JobModel job) {
		this.job = job;
	}
}
