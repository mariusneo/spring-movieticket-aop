package com.spring.movieticket.service;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;

public class TicketUtils {
	public static int getAccountId(JdbcTemplate template, int userId) {
		String sql = "SELECT * FROM user WHERE id = " + userId;
		List<Map<String, Object>> userList = template.queryForList(sql);

		int accountId = -1;
		if (!userList.isEmpty()) {
			Map<String, Object> userData = userList.iterator().next();
			accountId = Integer.parseInt(userData.get("account_id").toString());
		}

		return accountId;
	}

	public static float findTicketCost(JdbcTemplate template, int ticketId) {
		String sql = "SELECT * FROM movie_ticket WHERE id= " + ticketId;
		List<Map<String, Object>> movieTicketList = template.queryForList(sql);

		float ticketCost = 0;

		if (!movieTicketList.isEmpty()) {
			Map<String, Object> movieTicketData = movieTicketList.iterator()
					.next();
			ticketCost = Float.parseFloat(movieTicketData.get("price")
					.toString());
		}

		return ticketCost;
	}

	public static void deductMoneyFromAccount(JdbcTemplate template,
			int accountId, float amount) {
		String sql = "UPDATE account set amount = (amount - " + amount
				+ ") WHERE id=" + accountId;
		template.execute(sql);
	}

	public static void reduceTicketCount(JdbcTemplate template, int ticketId,
			int ticketsCount) {
		String sql = "UPDATE movie_ticket SET tickets_count = (tickets_count - "
				+ ticketsCount + ") WHERE id =" + ticketId;
		template.execute(sql);
	}

	public static float balanceMoney(JdbcTemplate template, int accountId) {
		String sql = "SELECT amount FROM account WHERE id = " + accountId;
		Number number = template.queryForObject(sql, Float.class);
		return (number != null ? number.floatValue() : 0);
	}
}
