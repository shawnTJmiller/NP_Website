package com.techelevator.DAO;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.model.Park;
import com.techelevator.model.Weather;

@Component
public class JDBCWeatherDAO implements WeatherDAO {

	private JdbcTemplate jdbcTemplate;

	public JDBCWeatherDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<Weather> getWeatherForecast(String parkCode) {
		Park park = new Park();
		List<Weather> forecast = new ArrayList<>();
		String sqlWeather = "SELECT * FROM weather where parkcode = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlWeather, parkCode);

		while (results.next()) {
			Weather weather = new Weather();
			park.setParkCode(results.getString("parkcode"));
			weather.setFiveDayForecastValue(results.getInt("fivedayforecastvalue"));
			weather.setLow(results.getDouble("low"));
			weather.setHigh(results.getDouble("high"));
			weather.setForecast(results.getString("forecast"));
			forecast.add(weather);
		}

		return forecast;
	}

	// Plan to use on future renditions of website
//	@Override
//	public Weather getCurrentWeather(String parkCode) {
//		Park park = new Park();
//		String sqlWeather = "SELECT * " + 
//							"FROM weather " + 
//							"WHERE parkcode = ? " +
//							"AND fivedayforecastvalue = 1";
//		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlWeather, parkCode);
//			Weather weather = new Weather();
//			park.setParkCode(results.getString("parkcode"));
//			weather.setFiveDayForecastValue(results.getInt("fivedayforecastvalue"));
//			weather.setLow(results.getDouble("low"));
//			weather.setHigh(results.getDouble("high"));
//			weather.setForecast(results.getString("forecast"));
//
//		return weather;
//	}

}