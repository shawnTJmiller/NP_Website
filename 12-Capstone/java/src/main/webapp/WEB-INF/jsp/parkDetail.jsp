<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@include file="Common/header.jsp"%>

<div id="tempChange">
	<c:url var="formAction" value="/parkDetail" />
	<form method="POST" action="${formAction}">
		<div class="btn-f">
			<input type="hidden" name="tempType" value="F" /> <input
				type="hidden" name="parkCode" value="${park.parkCode}" /> <input
				type="submit" name="false" value="Fahrenheit" />
		</div>
	</form>
	<form method="POST" action="${formAction }">
		<div class="btn-c">
			<input type="hidden" name="tempType" value="C" /> <input
				type="hidden" name="parkCode" value="${park.parkCode }" /> <input
				type="submit" name="true" value="Celcius" />
		</div>
	</form>
</div>

<h2>
	<c:out value="${park.parkName}" />
</h2>

<div>
	<c:url var="imageLink"
		value="/img/parks/${park.parkCode.toLowerCase() }.jpg" />
	<img src="${imageLink }" />
</div>

<div>
	<p>
		<em>"<c:out value="${park.inspirationalQuote}" />" -<c:out
				value="${park.inspirationalQuoteSource}" /></em>
	</p>
</div>

<div>
	<p>
		<c:out value="${park.parkDescription}" />
	</p>
	<c:choose>
		<c:when test="${park.entryFee == '0.00'}">
			<p>Entry Fee: Free!</p>
		</c:when>
		<c:otherwise>
			<p>
				Entry Fee: $
				<c:out value="${park.entryFee }" />
			</p>
		</c:otherwise>
	</c:choose>
	<p>
		State:
		<c:out value="${park.state }" />
	</p>
	<p>
		Acreage:
		<c:out value="${park.acreage}" />

	</p>
	<p>
		Established:
		<c:out value="${park.yearFounded }" />
	</p>
	<p>
		Number of Visitors:
		<c:out value="${park.annualVisitorCount }" />
	</p>
	<p>
		Elevation:
		<c:out value="${park.elevationInFeet }" />

	</p>
	<p>
		Total Miles of Trails:
		<c:out value="${park.milesOfTrail }" />
	</p>
	<c:choose>
		<c:when test="${park.numberOfCampsites == 0 }">
			<p>Total Number of Campsites: No Campsites Available</p>
		</c:when>
		<c:otherwise>
			<p>
				Total Number of Campsites:
				<c:out value="${park.numberOfCampsites}" />
			</p>
		</c:otherwise>
	</c:choose>
	<p>
		Total Number of Animal Species:
		<c:out value="${park.numberOfAnimalSpecies}" />
	</p>
</div>


<div class="weatherContainer">

	<div class="currentWeatherContainer">

		<c:forEach items="${parkWeather}" var="forecastWeather">

			<c:choose>
				<c:when test="${forecastWeather.forecast == 'partly cloudy'}">
					<c:set value="partlyCloudy" var="image" />
				</c:when>
				<c:otherwise>
					<c:set value="${forecastWeather.forecast}" var="image" />
				</c:otherwise>
			</c:choose>
			<div id="weatherImage">
				<c:url var="weatherImage" value="/img/weather/${image}.png" />
				<img src="${weatherImage}" />
			</div>
			<!-- ^divTag weatherImage -->

			<div id="tempNums">

				High:
				<c:choose>
					<c:when test="${celcius}">
						<fmt:formatNumber maxFractionDigits="0">
							<c:out value="${(forecastWeather.high - 32) * 5 / 9}" />
						</fmt:formatNumber>°C
				</c:when>
					<c:otherwise>
						<c:out value="${forecastWeather.high}" />°F
				</c:otherwise>
				</c:choose>
				Low:
				<c:choose>
					<c:when test="${celcius}">
						<fmt:formatNumber maxFractionDigits="0">
							<c:out value="${(forecastWeather.low - 32) * 5 / 9}" />
						</fmt:formatNumber>°C
				</c:when>
					<c:otherwise>
						<c:out value="${forecastWeather.low}" />°F
				</c:otherwise>
				</c:choose>
			</div>
			<!-- ^divTag tempNums -->

			<div id="messageOutput">
				<c:choose>
					<c:when test="${ forecastWeather.forecast == 'sunny' }">
						<c:out value="Pack sunblock, a minimum of SPF50" />
						<br>
					</c:when>
					<c:when test="${ forecastWeather.forecast == 'rain' }">
						<c:out value="Pack rain gear and waterproof shoes." />
						<br>
					</c:when>
					<c:when test="${ forecastWeather.forecast == 'thunderstorms' }">
						<c:out value="Seek shelter and avoid hiking on exposed ridges" />
						<br>
					</c:when>
					<c:when test="${ forecastWeather.forecast == 'snow' }">
						<c:out value="Pack snowshoes" />
						<br>
					</c:when>
					<c:otherwise>
						<c:out value="" />
					</c:otherwise>
				</c:choose>
				<c:if test="${ forecastWeather.high > 75 }">
					<c:out value="Bring extra fluids to stay hydrated." />
					<br>
				</c:if>
				<c:if test="${ forecastWeather.low < 20 }">
					<c:out value="CAUTION - prolonged exposure may cause frostbite." />
					<br>
				</c:if>
				<c:if test="${ (forecastWeather.high - forecastWeather.low) > 20 }">
					<c:out value="Wear breathable layers." />
					<br>
				</c:if>
			</div>
		</c:forEach>
	</div>
</div>