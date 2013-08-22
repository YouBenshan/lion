<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<label>总共收到消息：{{lastMessages.page.totalElements}}</label>
<label>最后联系的客户wechat id为: {{lastMessages.content[0].other}}，时间： {{lastMessages.content[0].createTime}}</label>
<form>
	<fieldset>
		<legend>设置查找条件</legend>
		<label>wechat 账号:  <input type="text" name="other"/></label>
		<label>日期: <input type="date" name="createTime"/></label>
		<button type="submit" class="btn">查找</button>
	</fieldset>
</form>

<table class="table table-striped .table-bordered table-hover">
	<caption>接收到的信息</caption>
	<thead>
		<tr>
			<th>links</th>
			<th>email</th>
			
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
