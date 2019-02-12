package com.qbb.test.jobs;

import com.qbb.jobs.tradition.RunGanSuK3Job;
import com.qbb.test.QuartzJobTestCase;
import org.junit.Test;
import org.quartz.JobExecutionException;

public class RunGanSuK3JobTest extends QuartzJobTestCase {

	@Test
	public void test() {
		try {
			testJob(RunGanSuK3Job.class);
		} catch (JobExecutionException e) {
			e.printStackTrace();
		}
	}

}