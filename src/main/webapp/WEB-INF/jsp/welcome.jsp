<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>
<div class="container">

	<div class="panel panel-primary">
		<div class="panel-heading">
			<h3>Welcome to Rates API !!</h3>
		</div>
		<div class="panel-body">
			<table class="table table-striped">
				<thead>
					<tr>
						<th width="50%">${RatesBeanObject.base}</th>
						<th width="50%"><fmt:formatDate
								value="${RatesBeanObject.date}" pattern="dd/MM/yyyy" /></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${RatesBeanObject.rates.entrySet()}" var="entry1">
						<tr>
							<td>${entry1.key}</td>
							<td>${entry1.value}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<a type="button" class="btn btn-success"
				href="/rates-details/getHistoricalData">Get Historical Data</a>
		</div>
	</div>
</div>
<%@ include file="common/footer.jspf"%>