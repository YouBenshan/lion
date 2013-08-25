<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<form>
	<fieldset>
		<legend>设置查找条件</legend>
		<label>wechat 账号:  <input type="text" name="EQ[other]" ng-model="newFilter.EQ.other"/></label>
		<!-- <label>日期: <input type="date" name="createTime" ng-model="newFilter.createTime"/></label>  -->
		<label>日期: <input type="number" name="EQ[createTime]" ng-model="newFilter.EQ.createTime"/></label> 
		<button type="submit" class="btn" ng-click="find();">查找</button>
	</fieldset>
</form>
{{newFilter}}

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
