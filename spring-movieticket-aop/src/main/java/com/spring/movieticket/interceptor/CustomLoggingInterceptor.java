package com.spring.movieticket.interceptor;

import java.lang.reflect.Method;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class CustomLoggingInterceptor implements MethodInterceptor {

	public Object invoke(MethodInvocation invocation) throws Throwable {
		Method method = invocation.getMethod();
		System.out.printf("Before method %s invocation\n", method.getName());
		try {
			return invocation.proceed();
		} finally {
			System.out.printf("After method %s invocation\n", method.getName());
		}
	}

}
