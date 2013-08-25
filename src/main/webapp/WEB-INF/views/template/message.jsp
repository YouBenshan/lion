<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<label>总共收到消息：{{lastMessages.page.totalElements}}</label>
<label>最后收到的消息</label>
<label>wechat id: {{lastMessages.content[0].other}}</label>
<label>时间： {{lastMessages.content[0].createTime}}
<form>
	<fieldset>
		<legend>设置查找条件</legend>
		<label>wechat 账号:  <input type="text" name="EQ[other]"/></label>
		<label>日期: <input type="date" name="createTime"/></label>
		<button type="submit" class="btn">查找</button>
	</fieldset>
</form>

<table class="table table-striped .table-bordered table-hover">
<div class="pagination pagination-right">
  <ul>
    <li><a ng-click="pageable.page=1;updateMessages()">Prev</a></li>
    <li><a ng-click="pageable.page=1;updateMessages()">1</a></li>
    <li><a ng-click="pageable.page=2;updateMessages()">2</a></li>
    <li><a ng-click="pageable.page=3;updateMessages()">3</a></li>
    <li><a ng-click="pageable.page=4;updateMessages()">4</a></li>
    <li><a ng-click="pageable.page=5;updateMessages()">5</a></li>
    <li><a ng-click="pageable.page=1;updateMessages()">Next</a></li>
  </ul>
</div>
{{pageable}}
	<caption>接收到的信息</caption>
	<thead>
		<tr>
			<th>时间</th>
			<th>wechat Id</th>
			<th>详细</th>
		</tr>
	</thead>
	<tbody>
		<tr ng-repeat="message in messages.content">
			<td>{{message.createTime}}</td>
			<td>{{message.other}}</td>
			<td><a href="#/profile/account/edit/{{account.id}}">edit</a></td>
		</tr>
	</tbody>
</table>
