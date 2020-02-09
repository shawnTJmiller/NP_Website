package com.techelevator.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.techelevator.DAO.ParkDAO;
import com.techelevator.DAO.WeatherDAO;
import com.techelevator.model.Park;
import com.techelevator.model.Weather;

@Controller
public class SiteController {

	@Autowired
	ParkDAO parkDao;
	@Autowired
	WeatherDAO weatherDao;

	@RequestMapping(path = "/", method = RequestMethod.GET)
	public String showHomePage(ModelMap modelHolder, @ModelAttribute Park newNationalPark) {
		List<Park> parks = parkDao.getAllParks();
		modelHolder.put("parks", parks);

		return "index";

	}

  @RequestMapping(path="/parkDetail/{parkCode}",method=RequestMethod.GET)
	public String showParkDetail(@PathVariable String parkCode, HttpSession session, ModelMap modelHolder){
		
		List<Weather> newWeather = weatherDao.getWeatherForecast(parkCode);
		Park newPark = parkDao.getParkByParkCode(parkCode);		
		modelHolder.put("park", newPark);
		modelHolder.put("parkWeather", newWeather);
		
		return "parkDetail";
	}	
	/*
	@RequestMapping(path="/parkDetail/{parkCode}",method=RequestMethod.POST)
	public String showParkDetailWithConversion(@PathVariable String parkCode,
			@RequestParam String convert, HttpSession session, ModelMap modelHolder){
		session.setAttribute("convert", convert);
		
		return "redirect:/parkDetail/" + parkCode;
	} */
	
	// details page based on if they picked celcius or fahrenheit
	@RequestMapping(path = "/parkDetail", method = RequestMethod.POST)
	public String changeTempValue(HttpSession session, @RequestParam String tempType, @RequestParam String parkCode) {
	if (tempType.equals("C")) {
	Boolean celcius = true;
	session.setAttribute("celcius", celcius);
	} else {
	Boolean celcius = false;
	session.setAttribute("celcius", celcius);
	}
	return "redirect:/parkDetail/" + parkCode;
	}
	
	

}
