package com.techelevator.model;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;

public class Survey {

	private Long surveyId;
	
	@NotNull
	@Email(message = "Not a valid email address")
	private String emailAddress;

	private String state;

	private String activityLevel;

	private String parkCode;
	
	
	public Long getSurveyId() {
		return surveyId;
	}

	public void setSurveyId(Long surveyId) {
		this.surveyId = surveyId;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getActivityLevel() {
		return activityLevel;
	}

	public void setActivityLevel(String physical) {
		this.activityLevel = physical;
	}

	public String getParkCode() {
		return parkCode;
	}

	public void setParkCode(String favoritePark) {
		this.parkCode = favoritePark;
	}

}