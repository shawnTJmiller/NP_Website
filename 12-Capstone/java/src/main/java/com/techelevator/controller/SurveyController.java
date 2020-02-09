package com.techelevator.controller;

import java.util.List;
import javax.sql.DataSource;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.techelevator.DAO.ParkDAO;
import com.techelevator.DAO.SurveyDAO;
import com.techelevator.model.Survey;
import com.techelevator.model.TopSurvey;

@Controller
public class SurveyController {

	DataSource dataSource;

	@Autowired
	SurveyDAO surveyDao;

	@Autowired
	ParkDAO parkDao;

	@RequestMapping(path = "/Survey", method = RequestMethod.GET)
	public String showSurvey(ModelMap modelHolder) {

		if (!modelHolder.containsAttribute("survey")) {
			modelHolder.put("survey", new Survey());
		}

		return "survey";
	}

	@RequestMapping(path = "/Survey", method = RequestMethod.POST)
	public String processSurvey(@Valid @ModelAttribute Survey newSurvey, BindingResult result,
			RedirectAttributes flash) {
		flash.addFlashAttribute("survey", newSurvey);

		if (result.hasErrors()) {
			flash.addFlashAttribute(BindingResult.MODEL_KEY_PREFIX + "survey", result);
			flash.addFlashAttribute("survey", newSurvey);

			return "redirect:/Survey";
		}

		surveyDao.saveSurvey(newSurvey);

		return "redirect:/topRankedParks";
	}

	@RequestMapping(path = "/topRankedParks", method = RequestMethod.GET)
	public String showTopParks(ModelMap modelHolder) {

		List<TopSurvey> popularParks = parkDao.getPopularParksFirst();
		modelHolder.put("topRankedParks", popularParks);

		return "topRankedParks";
	}
}