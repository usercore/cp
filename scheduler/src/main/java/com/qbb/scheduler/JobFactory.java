package com.qbb.scheduler;

import org.quartz.JobDataMap;
import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.scheduling.quartz.AdaptableJobFactory;

import com.qbb.scheduler.model.JobModel;

public class JobFactory extends AdaptableJobFactory {

	@Autowired
	private AutowireCapableBeanFactory capableBeanFactory;

	@Override
	protected Object createJobInstance(TriggerFiredBundle bundle) throws Exception {
		// 调用父类的方法
		Object jobInstance = super.createJobInstance(bundle);
		// 进行注入
		capableBeanFactory.autowireBean(jobInstance);
		if(jobInstance instanceof AbstractJob) {
			JobDataMap data = bundle.getJobDetail().getJobDataMap();
			if (data.containsKey("job_info")) {
				JobModel job = (JobModel) data.get("job_info");
				((AbstractJob) jobInstance).setJobInfo(job);
			}
		}
		return jobInstance;
	}

}