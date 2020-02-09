package com.techelevator.DAO;

import java.util.List;

import com.techelevator.model.Park;
import com.techelevator.model.TopSurvey;

public interface ParkDAO {
	
	public List<Park> getAllParks();
	

	public Park getParkByParkCode(String code);

	
	public List<TopSurvey> getPopularParksFirst();

}