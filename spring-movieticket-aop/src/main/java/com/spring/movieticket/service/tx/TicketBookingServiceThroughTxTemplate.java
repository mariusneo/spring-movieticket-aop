package com.spring.movieticket.service.tx;

import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import com.spring.movieticket.service.TicketBookingService;

public class TicketBookingServiceThroughTxTemplate extends JdbcDaoSupport implements TicketBookingService{

	private PlatformTransactionManager txManager;
	
	public void setTransactionManager(PlatformTransactionManager  transactionManager){
		this.txManager = transactionManager;
	}
	
	public void bookTicket(int userId, int ticketId, int ticketsCount) {
		TransactionTemplate txTemplate = new TransactionTemplate(txManager);
		
		BookingTransactionCallback callback = new BookingTransactionCallback(this, userId, ticketId, ticketsCount);
		txTemplate.execute(callback);
	}

}
