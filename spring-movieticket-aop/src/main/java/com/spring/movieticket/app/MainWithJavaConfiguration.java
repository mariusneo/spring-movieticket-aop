package com.spring.movieticket.app;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import com.spring.movieticket.service.TicketBookingServiceImpl;
import com.spring.movieticket.service.TicketUtils;
import com.spring.movieticket.spring.AppConfig;

public class MainWithJavaConfiguration {
	public static void main(String[] args) {
		ApplicationContext context = new AnnotationConfigApplicationContext(
				AppConfig.class);

		JdbcTemplate template = context.getBean("jdbcTemplate", JdbcTemplate.class);
		TicketBookingServiceImpl ticketService = new TicketBookingServiceImpl();
		ticketService.setTemplate(template);
		
		System.out.printf("Balance before booking tickets :  %.2f \n",
				TicketUtils.balanceMoney(template, 1));

		ticketService.bookTicket(1, 1, 2);

		System.out.printf("Balance after booking tickets : %.2f \n",
				TicketUtils.balanceMoney(template, 1));
		
	}
}
