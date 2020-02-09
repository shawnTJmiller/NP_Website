package com.techelevator.DAO;

import java.util.List;

import com.techelevator.model.Weather;

public interface WeatherDAO {

	public List<Weather> getWeatherForecast(String parkCode);
	
	// Plan to use on future renditions of website
//	public Weather getCurrentWeather(String parkCode);
	
}
