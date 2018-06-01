package com.qbb.test;

import org.junit.Ignore;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public abstract class QuartzJobTestCase extends SpringTestCase {

	@Ignore
	public Job createJobDetail(Class<? extends Job> clazz) {
		Job job = null;
		if (getApplicationContext().containsBean(clazz.getName())) {
			job = getApplicationContext().getBean(clazz);
		} else {
			job = getApplicationContext().getAutowireCapableBeanFactory().createBean(clazz);
		}
		return job;
	}

	@Ignore
	public void testJob(Class<? extends Job> clazz) throws JobExecutionException {
		Job job = createJobDetail(clazz);
		JobExecutionContext context = null;
		job.execute(context);
	}
}
