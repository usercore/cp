package com.qbb.test.jobs;

import com.qbb.jobs.tradition.GetSSQAwardJob;
import org.junit.Test;
import org.quartz.JobExecutionException;

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