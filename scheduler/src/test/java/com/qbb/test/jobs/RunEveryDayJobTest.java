package com.qbb.test.jobs;

import org.junit.Test;
import org.quartz.JobExecutionException;

import com.qbb.jobs.award.GetSSQAwardJob;
import com.qbb.jobs.ms.RunEveryDayJob;
import com.qbb.test.QuartzJobTestCase;

public class RunEveryDayJobTest extends QuartzJobTestCase {

	@Test
	public void test() {
		try {
			testJob(GetSSQAwardJob.class);
		} catch (JobExecutionException e) {
			e.printStackTrace();
		}
	}

}