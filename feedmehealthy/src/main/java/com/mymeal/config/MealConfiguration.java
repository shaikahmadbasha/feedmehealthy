/**
 * 
 */
package com.mymeal.config;

import javax.naming.NamingException;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jndi.JndiTemplate;

import com.mymeal.dao.MealDAO;
import com.mymeal.dao.MealDAOImpl;

/**
 * @author basha
 *
 */
@Configuration
public class MealConfiguration {

	@Bean
	public MealDAO getMyMealDAO() throws NamingException {

		JndiTemplate jndiTemplate = new JndiTemplate();
		DataSource dataSource = (DataSource) jndiTemplate.lookup("java:jboss/datasources/PostgreSQLDS");
		// DataSource dataSource = (DataSource) jndiTemplate.lookup("java:/MyMealDS");

		return new MealDAOImpl(dataSource);
	}
}
