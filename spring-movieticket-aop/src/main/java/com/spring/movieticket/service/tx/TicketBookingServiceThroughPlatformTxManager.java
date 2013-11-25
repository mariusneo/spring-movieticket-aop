package com.spring.movieticket.service.tx;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.spring.movieticket.service.TicketBookingService;
import com.spring.movieticket.service.TicketUtils;

public class TicketBookingServiceThroughPlatformTxManager extends JdbcDaoSupport
		implements TicketBookingService {
	private PlatformTransactionManager txManager;

	
	public void setTransactionManager(
			PlatformTransactionManager transactionManager) {
		this.txManager = transactionManager;
	}
	

	public void bookTicket(int userId, int ticketId, int ticketsCount) {
		TransactionDefinition txDef = new DefaultTransactionDefinition();
		TransactionStatus txStatus = txManager.getTransaction(txDef);

		try {
			int accountId = TicketUtils.getAccountId(getJdbcTemplate(), userId);
			float ticketCost = TicketUtils.findTicketCost(getJdbcTemplate(), ticketId);
			float totalCost = ticketCost * ticketsCount;
			TicketUtils.deductMoneyFromAccount(getJdbcTemplate(), accountId, totalCost);
			TicketUtils.reduceTicketCount(getJdbcTemplate(), ticketId, ticketsCount);
			
			txManager.commit(txStatus);
		} catch (Exception e) {
			txManager.rollback(txStatus);
		}
	}

}
