package com.spring.movieticket.spring;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.interceptor.TransactionInterceptor;

import com.spring.movieticket.interceptor.CustomLoggingInterceptor;
import com.spring.movieticket.service.TicketBookingService;
import com.spring.movieticket.service.tx.TicketBookingServiceThroughAnnotation;
import com.spring.movieticket.service.tx.TicketBookingServiceThroughAop;

@Configuration
@PropertySource("classpath:/jdbc.properties")
@EnableTransactionManagement()
public class AppConfig {
	@Autowired
	Environment env;

	
	
	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(env.getProperty("jdbc.driverClassName"));
		dataSource.setUrl(env.getProperty("jdbc.url"));
		dataSource.setUsername(env.getProperty("jdbc.username"));
		dataSource.setPassword(env.getProperty("jdbc.password"));

		return dataSource;
	}

	@Bean
	public JdbcTemplate jdbcTemplate() {
		return new JdbcTemplate(dataSource());
	}

	@Bean
	public PlatformTransactionManager transactionManager() {
		return new DataSourceTransactionManager(dataSource());
	}

	@Bean
	public TransactionInterceptor bookingTransactionInterceptor() {
		Properties attributes = new Properties();
		attributes.put("bookTicket", "PROPAGATION_REQUIRED");
		return new TransactionInterceptor(transactionManager(), attributes);
	}

	@Bean
	public CustomLoggingInterceptor customLoggingInterceptor() {
		return new CustomLoggingInterceptor();
	}

	@Bean
	public ProxyFactoryBean ticketBookingProxyService() {
		ProxyFactoryBean bean = new ProxyFactoryBean();
		bean.setTarget(ticketBookingServiceThroughAop());
		bean.setInterceptorNames(new String[] {
				"bookingTransactionInterceptor", "customLoggingInterceptor" });
		return bean;
	}

	@Bean
	public TicketBookingService ticketBookingServiceThroughAop() {
		TicketBookingServiceThroughAop service = new TicketBookingServiceThroughAop();
		service.setDataSource(dataSource());
		return service;
	}

	@Bean
	TicketBookingService ticketBookingServiceThroughAnnotation() {
		TicketBookingServiceThroughAnnotation service = new TicketBookingServiceThroughAnnotation();
		service.setDataSource(dataSource());
		return service;
	}

}
