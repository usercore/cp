package com.qbb.test.jobs;

import com.qbb.jobs.tradition.service.IGanSuK3LostService;
import com.qbb.test.QuartzJobTestCase;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class RunGanSuK3LostJobTest extends QuartzJobTestCase {
	@Autowired
	IGanSuK3LostService IGanSuK3LostService;
	@Test
	public void test() {
		try {
			IGanSuK3LostService.doCalculateLost();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}