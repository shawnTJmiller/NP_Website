package com.techelevator.DAO;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.model.Park;
import com.techelevator.model.TopSurvey;

@Component
public class JDBCParkDAO implements ParkDAO {

	private JdbcTemplate jdbcTemplate;

	public JDBCParkDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<Park> getAllParks() {
		List<Park> parkList = new ArrayList<>();
		String sqlParks = "SELECT parkname, state, parkdescription FROM park ORDER BY parkname asc";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlParks);

		while (results.next()) {
			Park park = new Park();
			park.setParkName(results.getString("parkname"));
			park.setState(results.getString("state"));
			park.setParkDescription(results.getString("parkdescription"));
			parkList.add(park);
		}

		return parkList;
	}

	@Override
	public Park getParkByParkCode(String code) {
		String sqlGetParkByCode = "SELECT * FROM park WHERE parkcode = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetParkByCode, code);
		results.next();
		Park park = new Park();
		park.setParkCode(results.getString("parkcode"));
		park.setParkName(results.getString("parkname"));
		park.setState(results.getString("state"));
		park.setAcreage(results.getInt("acreage"));
		park.setElevationInFeet(results.getInt("elevationinfeet"));
		park.setMilesOfTrail(results.getDouble("milesoftrail"));
		park.setClimate(results.getString("climate"));
		park.setYearFounded(results.getInt("yearfounded"));
		park.setAnnualVisitorCount(results.getInt("annualvisitorcount"));
		park.setInspirationalQuote(results.getString("inspirationalquote"));
		park.setInspirationalQuoteSource(results.getString("inspirationalquotesource"));
		park.setParkDescription(results.getString("parkdescription"));
		park.setEntryFee(results.getBigDecimal("entryfee"));
		park.setNumberOfAnimalSpecies(results.getInt("numberofanimalspecies"));
		
		return park;

	}

	@Override
	public List<TopSurvey> getPopularParksFirst() {
		List<TopSurvey> popularParkList = new ArrayList<>();
		String sqlParks = "SELECT park.*, surveycount " +
				"FROM park INNER JOIN " +
				"(SELECT park.parkcode, " +
				"COUNT(surveyid) surveycount " +
				"FROM park INNER JOIN " +
				"survey_result survey " +
				"ON park.parkcode = survey.parkcode " +
				"GROUP BY park.parkcode) t " +
				"ON park.parkcode = t.parkcode " +
				"ORDER BY surveycount DESC, parkcode ";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlParks);

		while (results.next()) {
			TopSurvey surveyResults = new TopSurvey();
			surveyResults.setParkNameSurvey(results.getString("parkname"));
			surveyResults.setParkCodeSurvey(results.getString("parkcode"));
			surveyResults.setParkSurveyCount(results.getInt("surveycount"));
			
			popularParkList.add(surveyResults);
		}

		return popularParkList;

	}

}