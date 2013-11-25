package com.spring.movieticket.service.tx;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;

import com.spring.movieticket.service.TicketUtils;

public class BookingTransactionCallback extends
		TransactionCallbackWithoutResult {
	private JdbcDaoSupport daoSupport;
	private int userId;
	private int ticketId;
	private int ticketsCount;

	public BookingTransactionCallback(JdbcDaoSupport daoSupport, int userId,
			int ticketId, int ticketsCount) {
		this.daoSupport = daoSupport;
		this.userId = userId;
		this.ticketId = ticketId;
		this.ticketsCount = ticketsCount;
	}

	@Override
	public void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
		JdbcTemplate template = daoSupport.getJdbcTemplate();
		int accountId = TicketUtils.getAccountId(template, userId);
		float ticketCost = TicketUtils.findTicketCost(template, ticketId);
		float totalCost = ticketCost * ticketsCount;

		TicketUtils.deductMoneyFromAccount(template, accountId, totalCost);
		TicketUtils.reduceTicketCount(template, accountId, accountId);
	}
}
