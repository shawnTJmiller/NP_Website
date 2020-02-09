<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@include file="Common/header.jsp"%>

<c:forEach var="parks" items="${ modelHolder }">

	<div class="parkTile">
		<div class="parkImg">
			<c:url var="parkDetailURL" value="/parkDetail" />
			<a href="${ parkDetailURL }/${ parks.parkCode }"><img
				src="img/parks/${ parks.parkCode.toLowerCase }.jpg" /></a>
		</div>
		<!-- ^divTag = parkImg -->
		<div class="parkInfo">
			<div class="parkName">
				<h2>
					<a href="${ parkDetailURL }/${ parks.parkCode }">
					<c:out value="${ parks.parkName }" /></a>
				</h2>
			</div>
			<!-- ^divTag parkName -->
			<div class="parkDescription">
				<p>
					<c:out value="${ parks.parkDescription }" />
				</p>
			</div>
			<!-- ^divTag parkDescription -->
		</div>
		<!-- ^divTag parkInfo -->
	</div>
	<!-- ^divTag parkTile -->

</c:forEach>


<!-- Replace this line and below with footer tag -->
</body>
</html>