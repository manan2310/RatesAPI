<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>
<div class="container">


	<div class="panel panel-primary">
		<div class="panel-heading">
			<h3>Welcome to Rates API - Historical Data!!</h3>
		</div>
		<div class="panel-body table-responsive">
			<table class="table table-striped ">
				<thead>
					<tr>
						<th>${base}</th>
						<c:forEach items="${dateList}" var="dates">
							<th><fmt:formatDate value="${dates}" pattern="dd/MM/yyyy" /></th>
						</c:forEach>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${rateMap.entrySet()}" var="entry1">
						<tr>
							<td>${entry1.key}</td>
							<c:forEach items="${entry1.value}" var="rates">
								<td>${rates}</td>
							</c:forEach>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<a type="button" class="btn btn-success" href="/rates-details">Dashboard</a>
		</div>
	</div>
</div>
<%@ include file="common/footer.jspf"%>