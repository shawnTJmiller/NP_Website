<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@include file="Common/header.jsp" %>

<h1>Thank you for your survey!</h1>

<table class="topRankedParksTable">
		<tr> 
			<th>&nbsp;</th> <th>&nbsp;</th> <th>Number of responses</th>
		</tr>
	<c:forEach items="${topRankedParks}" var="park">

		<tr> 
			<td > 
				<c:url var="imageLink" value="/img/parks/${park.key.toLowerCase() }.jpg" />
				<img src="${imageLink }" class="congratsImage"/>
			</td>
			<td>
				<c:out value="${parkList[park.key].parkName}"/>
			</td>
			<td>
				<c:out value="${park.value }" />
			</td>
		</tr>	

	</c:forEach>
</table>