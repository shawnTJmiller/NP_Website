
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@include file="Common/header.jsp" %>

<table class="topParksTable">
		<tr> 
			<th>&nbsp;</th> <th>&nbsp;</th> <th>Number of responses</th>
		</tr>
		<br>
		<br>
		
		
		
	<c:forEach items="${topRankedParks}" var="park">

		<tr class="topParksRow"> 
			<td class="congratsImage"> 
				<c:url var="imageLink" value="img/parks/${park.parkCodeSurvey.toLowerCase() }.jpg" />
				<img src="${imageLink}" class="congratsImage"/>
			</td>
			
			<td class="parkName">
			
				<c:out value="${park.parkNameSurvey}"/> 
			</td>
			<td>
			<td>
				<c:out value="${park.parkSurveyCount}"/> 
			</td>
		</tr>	

	</c:forEach>
</table>


