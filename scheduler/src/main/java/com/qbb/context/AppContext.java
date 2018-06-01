/*
 * Copyright 2014-2015 the original author hm.
 *
 */
package com.qbb.context;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class AppContext implements ApplicationContextAware {

	private static AppContext app;
	private ApplicationContext context;

	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.context = applicationContext;
		AppContext.app = this;
	}

	public static ApplicationContext getApplicationContext() {
		return app.context;
	}

	@SuppressWarnings("unchecked")
	public static <T> T getBean(Class<?> clazz) {
		ApplicationContext context = getApplicationContext();
		return (T) context.getBean(clazz);
	}

	@SuppressWarnings("unchecked")
	public static <T> T getBean(String name) {
		ApplicationContext context = getApplicationContext();
		return (T) context.getBean(name);
	}

}
