package com.qbb.test;

import org.apache.log4j.PropertyConfigurator;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-xml/*.xml" })
public abstract class SpringTestCase {

	@Autowired
	private ApplicationContext context;

	@Ignore
	public ApplicationContext getApplicationContext() {
		return context;
	}

	@Ignore
	public void setContext(ApplicationContext context) {
		this.context = context;
	}

	static {
		System.setProperty("log4j.home", SpringTestCase.class.getResource("/").toExternalForm());
		PropertyConfigurator.configure(SpringTestCase.class.getResourceAsStream("/conf/log4j.properties"));
	}

}
