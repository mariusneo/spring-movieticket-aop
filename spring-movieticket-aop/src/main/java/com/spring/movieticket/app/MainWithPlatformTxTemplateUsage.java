package com.spring.movieticket.app;

import javax.sql.DataSource;

import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.PlatformTransactionManager;

import com.spring.movieticket.service.TicketUtils;
import com.spring.movieticket.service.Utils;
import com.spring.movieticket.service.tx.TicketBookingServiceThroughTxTemplate;

public class MainWithPlatformTxTemplateUsage {
	public static void main(String[] args) {
		ApplicationContext context = Utils.getContext();
		TicketBookingServiceThroughTxTemplate ticketService = new TicketBookingServiceThroughTxTemplate();
		ticketService.setDataSource(context.getBean("dataSource",
				DataSource.class));
		ticketService.setTransactionManager(context.getBean(
				"transactionManager", PlatformTransactionManager.class));

		JdbcTemplate template = Utils.getContext().getBean("jdbcTemplate",
				JdbcTemplate.class);
		System.out.printf("Balance before booking tickets :  %.2f \n",
				TicketUtils.balanceMoney(template, 1));

		ticketService.bookTicket(1, 1, 2);

		System.out.printf("Balance after booking tickets : %.2f \n",
				TicketUtils.balanceMoney(template, 1));
	}
}
