package com.techelevator.npgeek;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

import com.techelevator.DAO.JDBCParkDAO;
import com.techelevator.model.Park;
import com.techelevator.model.TopSurvey;

public class ParkDAOTest {

	private static SingleConnectionDataSource dataSource;
	
	@BeforeClass
	public static void setupDataSource() {
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
	public void canFindAllParksTest() {
		JDBCParkDAO parkDAO = new JDBCParkDAO(dataSource);
		List<Park> parkList = parkDAO.getAllParks();
		assertEquals(10, parkList.size());
	}
	
	@Test
	public void getParkByParkCodeTest() {
		JDBCParkDAO parkDAO = new JDBCParkDAO(dataSource);
		String testPark = "CVNP";
		String parkName = parkDAO.getParkByParkCode(testPark).getParkName();
		assertEquals("Cuyahoga Valley National Park", parkName);
	}
	
	@Test
	public void getPopularParkTest() {
		JDBCParkDAO parkDAO = new JDBCParkDAO(dataSource);
		List<TopSurvey> parkList = parkDAO.getPopularParksFirst();
		assertEquals(7, parkList.size());
	}

}
