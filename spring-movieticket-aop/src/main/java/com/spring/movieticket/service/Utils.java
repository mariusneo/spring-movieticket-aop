package com.spring.movieticket.service;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Utils {
	private static ApplicationContext context = null;

	public static ApplicationContext getContext() {
		initContext();
		return context;
	}

	private static synchronized void initContext() {
		if (context == null) {
			context = new ClassPathXmlApplicationContext("beans.xml");
		}
	}
}
