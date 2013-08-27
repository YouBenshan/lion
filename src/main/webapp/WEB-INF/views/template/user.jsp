<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<label>24小时内，新被关注的次数：{{subscribeToday.totalElements}}</label>
<label>24小时内，发消息最多的20人：</label>
<table class="table table-striped .table-bordered table-hover">
	<thead>
		<tr>
			<th>wechat ID</th>
			<th>数量</th>
		</tr>
	</thead>
	<tbody>
		<tr ng-repeat="t in topActive.content">
			<td>{{t.other}}</td>
			<td>{{t.mount}}</td>
		</tr>
	</tbody>
</table>