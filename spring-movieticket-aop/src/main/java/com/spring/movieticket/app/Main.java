package com.spring.movieticket.app;

import org.springframework.jdbc.core.JdbcTemplate;

import com.spring.movieticket.service.TicketBookingServiceImpl;
import com.spring.movieticket.service.Utils;

public class Main {
	public static void main(String[] args){
		JdbcTemplate template = Utils.getContext().getBean("jdbcTemplate", JdbcTemplate.class);
		TicketBookingServiceImpl ticketService = new TicketBookingServiceImpl();
		ticketService.setTemplate(template);
		ticketService.bookTicket(1, 1, 2);
	}
}
