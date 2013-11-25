package com.spring.movieticket.app;

import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import com.spring.movieticket.service.TicketBookingService;
import com.spring.movieticket.service.TicketUtils;
import com.spring.movieticket.service.Utils;

public class MainWithAnnotation {
	public static void main(String[] args) {
		ApplicationContext context = Utils.getContext();

		TicketBookingService ticketService = context.getBean(
				"ticketBookingServiceThroughAnnotation",
				TicketBookingService.class);

		JdbcTemplate template = Utils.getContext().getBean("jdbcTemplate", JdbcTemplate.class);
		System.out.printf("Balance before booking tickets :  %.2f \n",
				TicketUtils.balanceMoney(template, 1));

		ticketService.bookTicket(1, 1, 1);

		System.out.printf("Balance after booking tickets : %.2f \n",
				TicketUtils.balanceMoney(template, 1));
	}
}
