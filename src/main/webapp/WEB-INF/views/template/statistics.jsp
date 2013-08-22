<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<table class="table table-striped .table-bordered table-hover">
	<caption>accounts</caption>
	<thead>
		<tr>
			<th>links</th>
			<th>email</th>
			<th><spring:message code="username" /></th>
			<th><spring:message code="password" /></th>
			<th><spring:message code="edit" /></th>
		</tr>
	</thead>
	<tbody>
		<tr ng-repeat="account in accounts">
			<td>{{account.links}}</td>
			<td>{{account.email}}</td>
			<td>{{account.username}}</td>
			<td>{{account.password}}</td>
			<td><a href="#/profile/account/edit/{{account.id}}">edit</a></td>
		</tr>
	</tbody>
</table>
