package com.techelevator.npgeek;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

import com.techelevator.DAO.JDBCWeatherDAO;
import com.techelevator.model.Weather;

public class WeatherDAOTest {

	private static SingleConnectionDataSource dataSource;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		dataSource = new SingleConnectionDataSource();
		dataSource.setUrl("jdbc:postgresql://localhost:5432/npgeek");
		dataSource.setUsername("postgres");
		dataSource.setPassword("postgres1");
		/* The following line disables autocommit for connections
		 * returned by this DataSource. This allows us to rollback
		 * any changes after each test */
		dataSource.setAutoCommit(false);
	}

	@Test
	public void canFindWeatherForecastTest() {
		JDBCWeatherDAO weatherDAO = new JDBCWeatherDAO(dataSource);
		String weatherTest = "CVNP";
		List<Weather> weatherList = weatherDAO.getWeatherForecast(weatherTest);
		assertEquals(5, weatherList.size());
	}
	
	// Plan to use on future renditions of website
//	@Test
//	public void canFindCurrentWeather() {
//		JDBCWeatherDAO weatherDAO = new JDBCWeatherDAO(dataSource);
//		String weatherTest = "GNP";
//		Double currentWeather = weatherDAO.getCurrentWeather(weatherTest).getHigh();
//		assertEquals(40, currentWeather);
//	}

}
