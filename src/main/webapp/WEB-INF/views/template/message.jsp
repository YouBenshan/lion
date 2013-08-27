<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<label>总共收到消息：{{lastMessages.page.totalElements}}</label>
<label>最后收到的消息</label>
<label>wechat id: {{lastMessages.content[0].other}}</label>
<label>时间： {{getDate(lastMessages.content[0].createTime)}}

<div class="pagination pagination-right">
  <ul>
    <li ng-class="{disabled: current== 1}" ><a ng-click="updateMessages(current-1);">Prev</a></li>
    <li ng-repeat="n in [] | pageRange:{'total':messages.page.totalPages,'current':current}" ng-class="{active: n+1 == current}">
            <a ng-click="updateMessages(n+1);">{{n+1}}</a>
    </li>
    <li ng-class="{disabled: current== messages.page.totalPages}"><a ng-click="updateMessages(current+1);">Next</a></li>
  </ul>
</div>
<table class="table table-striped .table-bordered table-hover">
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
			<td>{{getDate(message.createTime)}}</td>
			<td>{{message.other}}</td>
			<td><a href="#detail" role="button" class="btn" data-toggle="modal" ng-click="modal.message=message">详细</a></td>
		</tr>
	</tbody>
</table>

<div id="detail" class="modal hide fade"aria-hidden="true">
  <div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
    <h3>详细信息</h3>
  </div>
  <div class="modal-body">
  	<p ng-bind="modal.message.content"></p>
  </div>
  <div class="modal-footer">
    <button class="btn" data-dismiss="modal" aria-hidden="true">Close</button>
  </div>
</div>
