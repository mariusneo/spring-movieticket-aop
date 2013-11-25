package com.spring.movieticket.app;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import com.spring.movieticket.service.TicketBookingService;
import com.spring.movieticket.service.TicketUtils;
import com.spring.movieticket.service.Utils;
import com.spring.movieticket.spring.AppConfig;

public class MainWithAopJavaConfiguration {
	public static void main(String[] args) {
		ApplicationContext context = new AnnotationConfigApplicationContext(
				AppConfig.class);

		TicketBookingService ticketService = context.getBean(
				"ticketBookingProxyService",
				TicketBookingService.class);

		JdbcTemplate template = Utils.getContext().getBean("jdbcTemplate", JdbcTemplate.class);
		System.out.printf("Balance before booking tickets :  %.2f \n",
				TicketUtils.balanceMoney(template, 1));

		ticketService.bookTicket(1, 1, 1);

		System.out.printf("Balance after booking tickets : %.2f \n",
				TicketUtils.balanceMoney(template, 1));
	}
}
