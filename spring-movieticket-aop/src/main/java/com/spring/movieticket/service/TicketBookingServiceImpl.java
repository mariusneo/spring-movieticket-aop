package com.spring.movieticket.service;

import java.sql.Connection;
import java.sql.SQLException;

import org.springframework.jdbc.core.JdbcTemplate;

public class TicketBookingServiceImpl implements TicketBookingService{

	private JdbcTemplate template;
	
	public void setTemplate(JdbcTemplate template){
		this.template = template;
	}
	
	public void bookTicket(int userId, int ticketId, int ticketsCount) {
		Connection connection = null;
		
		try{
			connection = template.getDataSource().getConnection();
			connection.setAutoCommit(false);
			
			int accountId = TicketUtils.getAccountId(template, userId);
			float ticketCost = TicketUtils.findTicketCost(template, ticketId);
			float totalCost= ticketCost * ticketsCount;
			
			TicketUtils.deductMoneyFromAccount(template, accountId, totalCost);
			TicketUtils.reduceTicketCount(template, ticketId, ticketsCount);
		}catch(Exception e){
			e.printStackTrace();
			if (connection != null){
				try{
					connection.rollback();
				}catch(SQLException e1){
					e1.printStackTrace();
				}
			}
		}
	}

}
