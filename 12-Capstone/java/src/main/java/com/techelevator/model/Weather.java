package com.techelevator.model;

public class Weather {
	
	private String forecast;
	private double low;
	private double high;
	private int fiveDayForecastValue;
	

	public String getForecast() {
		return forecast;
	}
	
	public void setForecast(String forecast) {
		this.forecast = forecast;
	}

	public double getLow() {
		return low;
	}
	
	public void setLow(double low) {
		this.low = low;
	}
	
	public double getHigh() {
		return high;
	}
	
	public void setHigh(double high) {
		this.high = high;
	}
	
	public int getFiveDayForecastValue() {
		return fiveDayForecastValue;
	}
	
	public void setFiveDayForecastValue(int fiveDayForecastValue) {
		this.fiveDayForecastValue = fiveDayForecastValue;
	}

}