package com.spring.movieticket.service.tx;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.spring.movieticket.service.TicketBookingService;
import com.spring.movieticket.service.TicketUtils;

public class TicketBookingServiceThroughAop extends JdbcDaoSupport implements
		TicketBookingService {

	public void bookTicket(int userId, int ticketId, int ticketsCount) {
		int accountId = TicketUtils.getAccountId(getJdbcTemplate(), userId);
		float ticketCost = TicketUtils.findTicketCost(getJdbcTemplate(), ticketId);
		float totalCost = ticketCost * ticketsCount;
		TicketUtils.deductMoneyFromAccount(getJdbcTemplate(), accountId, totalCost);
		TicketUtils.reduceTicketCount(getJdbcTemplate(), ticketId, ticketsCount);

	}
}
